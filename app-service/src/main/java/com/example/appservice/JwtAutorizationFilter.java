package com.example.appservice;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.appservice.model.User;
import com.example.appservice.service.UserService;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;


@Slf4j
public class JwtAutorizationFilter extends OncePerRequestFilter{
	
	private JWTVerifier verifier;

	private UserService userService;
	
	public JwtAutorizationFilter(UserService userService, String jwtSecret) {
		log.warn("JwtAutorizationFilter constructor");
		this.userService = userService;
		
		System.err.println(jwtSecret);
		Algorithm algo = Algorithm.HMAC256(jwtSecret); // must match the algorithm used to generate the JWT
		verifier = JWT.require(algo).withClaimPresence("mobileNo").build();
	}
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		String header = request.getHeader("Authorization");
		log.info("Authorization: " + header);
		if (header == null || !header.startsWith("Bearer")) {
			chain.doFilter(request, response);
			return;
		}
		String token = header.replace("Bearer", "");
		
		try {
			// decode the JWT
			DecodedJWT jwt = verifier.verify(token.trim());
			
			String mobileNo = jwt.getClaim("mobileNo").asString();
			log.info("mobileNo: " + mobileNo);
	
			User user = userService.findByMobile(mobileNo);
			if (user != null) {
				// Currently not used but this can store the user roles or permissions
				List<GrantedAuthority> grantedAuthorities = new ArrayList<GrantedAuthority>();
				// The authentication object stores the user details
				UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(user, null,
						grantedAuthorities);
			
				// Store authentication object in security context
				System.err.println(authentication);
				SecurityContextHolder.getContext().setAuthentication(authentication);

							
			}
		} catch (JWTVerificationException e) {
			log.error(e.getMessage(), e);
		}

		chain.doFilter(request, response);
	}
	
	

}
