//package com.example.appservice.service;
//
//import java.security.Key;
//import java.util.Date;
//import java.util.HashMap;
//import java.util.Map;
//
//import org.springframework.stereotype.Service;
//
//import com.sun.org.apache.xml.internal.security.algorithms.SignatureAlgorithm;
//
//import io.jsonwebtoken.Jwts;
//import io.jsonwebtoken.io.Decoders;
//import io.jsonwebtoken.security.Keys;
//
//@Service
//public class JwtService {
//	
//	private String SECRET = "secrete";
//	public String generateToken(String username) {
//		Map<String,Object> claims = new HashMap<>();
//		return createToken(claims, username);
//	}
//
//	private String createToken(Map<String, Object> claims, String username) {
//		
//		return Jwts.builder().setClaims(claims).setSubject(username).setIssuedAt(new Date(System.currentTimeMillis()))
//				.setExpiration(new Date(System.currentTimeMillis() + 1000 *60 * 30)).signWith(getSign(), SignatureAlgorithm.ES256.HS256).compact();
//	}
//	
//	private Key getSign() {
//		byte[] keyBytes = Decoders.BASE64.decode(SECRET);
//		return Keys.hmacShaKeyFor(keyBytes);
//	}
//}
