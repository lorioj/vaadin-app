package com.example.appservice.controller;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.example.appservice.model.AuthResponse;
import com.example.appservice.model.User;
import com.example.appservice.service.UserService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api")
public class UserController {

	@Autowired
	private UserService userService;

	private Algorithm algorithm;

	@Value("${jwt.secret}")
	private String jwtSecret;

	@Value("${jwt.issuer}")
	private String jwtIssuer;

	@GetMapping("/hello")
	public String hello() {
		return "Hello World";
	}

	@GetMapping("/users")
	public List<User> findAll() {
		return userService.findAll();
	}

	@PostMapping("/authenticate")
	public String signIn(@RequestParam String mobileNo) {
		System.err.println("authenticate postmapping");
		AuthResponse res = AuthResponse.builder().jwtToken(generateJwt(mobileNo)).build();
		System.err.println(res.getJwtToken());
		return res.getJwtToken();
	}
	
	

	private String generateJwt(String mobileNo) {
		if (algorithm == null) {
			algorithm = Algorithm.HMAC256(jwtSecret);
		}
		try {
			String token = JWT.create().withIssuer(jwtIssuer).withClaim("mobileNo", mobileNo).sign(algorithm);
			return token;
		} catch (JWTCreationException e) {
			// Invalid Signing configuration / Couldn't convert Claims.
			log.error(e.getMessage(), e);
			throw e;
		}
	}

}
