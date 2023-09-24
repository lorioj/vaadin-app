package com.example.application.repository;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.application.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
	User findByUsername(String username);
	
	@Query("SELECT o FROM User o WHERE o.name LIKE :text OR o.username LIKE :text")
	List<User> findByName(String text, Sort by);
}
