package com.example.appservice.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.appservice.model.User;
import com.example.appservice.repository.UserRepository;

@Service
public class UserService {
	
	
	@Autowired
	private UserRepository userRepo;
	
	public List<User> findAll(){
		return userRepo.findAll();
	}
	

}
