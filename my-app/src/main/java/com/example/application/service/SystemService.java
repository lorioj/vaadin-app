package com.example.application.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.application.model.User;
import com.example.application.repository.UserRepository;


@Service
public class SystemService {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private UserRepository userRepo;
	
	
	public User authenticate(String username, String password) {

	
		User u = userRepo.findByUsername(username);
		if (u == null) {
//			log.warn("User not found: " + username);
			throw new RuntimeException("Invalid username or password.");
		}
		
		if(!password.equals(u.getPassword())) {
//			log.warn("Invalid password.");
			throw new RuntimeException("Invalid username of password.");
		}
		
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
