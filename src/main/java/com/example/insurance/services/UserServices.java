package com.example.insurance.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.insurance.dao.Roles;
import com.example.insurance.dao.UserDao;
import com.example.insurance.dao.UsersResponse;
import com.example.insurance.exception.CustomeException;
import com.example.insurance.repository.UserRepository;

@Service
public class UserServices implements IUserServies{
	
	@Autowired
	UserRepository repository;
	@Autowired
	BCryptPasswordEncoder encoder;

	@Override
	public ResponseEntity<?> createUser(UserDao newUser) {
		// TODO Auto-generated method stub
		Optional<UserDao> findUserById = repository.findById(newUser.getUserId());
		try {
			if (!findUserById.isPresent()) {
				newUser.setPassword(encoder.encode(newUser.getPassword()));
				newUser.setRole(Roles.USER);
				repository.save(newUser);
				return new ResponseEntity<UserDao>(newUser, HttpStatus.CREATED);
			}else {
				throw  new CustomeException("User With Id"+ newUser.getUserId()+"already exist");
			}
		} catch (Exception e) {
			// TODO: handle exception
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@Override
	public UserDao updateUser(UserDao newUser) {
		// TODO Auto-generated method stub
		Optional<UserDao> findUserById = repository.findById(newUser.getUserId());
		if (findUserById.isPresent()) {
			repository.save(newUser);
		}else 
			throw  new CustomeException("User With Id"+ newUser.getUserId()+"already exist");
		return newUser;
	}

	@Override
	public String deletUser(long userId) {
		// TODO Auto-generated method stub
		Optional<UserDao> findUserById = repository.findById(userId);
		if (findUserById.isPresent()) {
			repository.deleteById(userId);
			return "User Deleted";
		}else 
			throw  new CustomeException("User not found ");
	}

	@Override
	public ResponseEntity<?> findUserById(long userId) 
	{
		Optional<UserDao> findById = repository.findById(userId);
		try {
			if (!findById.isPresent()) {
				UserDao finDao = findById.get();
				return new ResponseEntity<UserDao>(finDao, HttpStatus.OK);
			}else {
				throw  new CustomeException("No reacod found for User Id"+userId);
			}
		} catch (Exception e) {
			// TODO: handle exception
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	public UsersResponse getAllUser(int page,int row){
		Pageable paging = PageRequest.of(page, row,Sort.Direction.DESC,"userId");
		UsersResponse usersResponse = new UsersResponse(repository.findUserPage(paging).toList(), repository.count());
		return usersResponse;
	}
}