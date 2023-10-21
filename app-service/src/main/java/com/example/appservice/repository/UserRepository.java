package com.example.appservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import com.example.appservice.model.User;

public interface UserRepository extends JpaRepository<User, Long>{

}
