package com.example.insurance.services;

import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;

import com.example.insurance.dao.UserDao;

public interface IUserServies {

	ResponseEntity<?> createUser(UserDao newUser);
	
	UserDao updateUser(UserDao newUser);
	
	String deletUser(long userId);

	ResponseEntity<?> findUserById(long userId);

	
}
