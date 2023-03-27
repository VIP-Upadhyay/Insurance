package com.example.insurance.controller;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.insurance.dao.ResponseModel;
import com.example.insurance.dao.UserDao;
import com.example.insurance.exception.CustomeException;
import com.example.insurance.services.UserServices;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/user/")
public class UserController {

	@Autowired
	UserServices services;
	
	@GetMapping({ "hello" })
    public String firstPage() {
        return "Hello World";
    }
	
	@PostMapping("createUser")
	@ExceptionHandler(CustomeException.class)
	public String addUser(@Valid@RequestBody UserDao userDao) {
		services.createUser(userDao);
		return "[\"Success\"]";
	}
	
	@PutMapping("updateuser")
	@ExceptionHandler(CustomeException.class)
	public void updateUser(@Valid@RequestBody UserDao updateUser) {
		services.updateUser(updateUser);
	}
	
	@GetMapping("searchUser/{userId}")
	@ExceptionHandler(CustomeException.class)
	public ResponseEntity<?> searchUserById(@Valid@PathVariable("id") long userId){
		return services.findUserById(userId);
	}
	
	@DeleteMapping("deleteUser/{userId}")
	@ExceptionHandler(CustomeException.class)
	public void deleteUserById(@PathVariable long userId) {
		services.deletUser(userId);
	}
	
	@GetMapping("getUser/{page}/{row}")
	@ExceptionHandler(CustomeException.class)
	public Object getAllUsers (@PathVariable int page,@PathVariable int row) {
		System.out.println(page);
//		ResponseModel responseModel = new ResponseModel();
//		responseModel.data=Collections.singletonList(services.getAllUser(page));
		return services.getAllUser(page,row);
	}
	
	
	
	
}
