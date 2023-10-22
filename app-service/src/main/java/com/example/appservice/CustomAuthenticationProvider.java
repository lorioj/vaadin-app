//package com.example.appservice;
//
//import java.util.ArrayList;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.authentication.AuthenticationProvider;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.AuthenticationException;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.stereotype.Component;
//
//import com.example.appservice.model.User;
//import com.example.appservice.service.UserService;
//
//
//@Component
//public class CustomAuthenticationProvider implements AuthenticationProvider {
//	
//
//	@Autowired
//	private PasswordEncoder passwordEncoder;
//	
//	@Autowired
//	private UserService userService;
//
//	
//	@Override
//	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
//		System.err.println("Custome authentication provider");
//		String pass = authentication.getCredentials().toString();
//		User user = userService.findByMobile("");
//		
//		if(passwordEncoder.matches(pass, user.getPassword())) {
//			return new UsernamePasswordAuthenticationToken(user, pass, new ArrayList<>());
//		}else {
//			return null;
//		}
//		
//	}
//
//	@Override
//	public boolean supports(Class<?> authentication) {
//		return authentication.equals(UsernamePasswordAuthenticationToken.class);
//	}
//
//}
