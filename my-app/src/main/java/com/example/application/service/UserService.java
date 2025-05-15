package com.example.application.service;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

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
	
	/**
	 * Way to access web API
	 */
	public List<User> find(){
		RestTemplate restTemplate = new RestTemplate();
		User[] users = restTemplate.getForObject("http://localhost:8081/api/users", User[].class);
		return Arrays.asList(users);
	}
	

}
