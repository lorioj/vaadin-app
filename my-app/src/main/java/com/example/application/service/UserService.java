package com.example.application.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.example.application.model.User;
import com.example.application.repository.UserRepository;

@Service
public class UserService {
	@Autowired
	private UserRepository userRepo;

	public List<User> findAll() {
		return userRepo.findAll();
	}
	
	public List<User> findByName(String text){
		return userRepo.findByName(text, Sort.by("name"));
	}
	public User findByUsername(String username) {
		return userRepo.findByUsername(username);
	}
	
	public void save(User entity) {
		userRepo.save(entity);
	}
	
	public void delete(User user) {
		userRepo.delete(user);
	}

}
