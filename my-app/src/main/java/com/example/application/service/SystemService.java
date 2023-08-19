package com.example.application.service;

import org.springframework.stereotype.Service;

import com.example.application.model.User;


@Service
public class SystemService {
	public User authenticate(String username, String password) {
		User u = new User();
		u.setUsername(username);
		u.setPassword(password);
	
//		User u = userRepo.findByUsername(username);
//		if (u == null) {
//			log.warn("User not found: " + username);
//			createAuditLog(Action.LOGIN_FAILED, "Login - " + username, "N/A", username);
//			throw new RuntimeException("Invalid username or password.");
//		}
//		String hash = DigestUtils.sha512Hex(password + u.getSalt());
//		if (!hash.equals(u.getPasswordHash())) {
//			log.warn("Invalid password.");
//			createAuditLog(Action.LOGIN_FAILED, "Login - " + username, "N/A", username);
//			throw new RuntimeException("Invalid username or password.");
//		}
//		if (!u.isActive()) {
//			log.warn("User is not active.");
//			createAuditLog(Action.LOGIN_FAILED, "Login - " + username, "N/A", username);
//			throw new RuntimeException("Invalid username or password.");
//		}

		return u;
	}
}
