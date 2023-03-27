package com.example.insurance.dao;

import java.util.List;

public class UsersResponse {
	List<UserDao> users;
	long totalRecords;
	
	
	public UsersResponse() {
		super();
		// TODO Auto-generated constructor stub
	}


	public UsersResponse(List<UserDao> users, long totalRecords) {
		super();
		this.users = users;
		this.totalRecords = totalRecords;
	}


	public List<UserDao> getUsers() {
		return users;
	}


	public void setUsers(List<UserDao> users) {
		this.users = users;
	}


	public long getTotalRecords() {
		return totalRecords;
	}


	public void setTotalRecords(long totalRecords) {
		this.totalRecords = totalRecords;
	}
	
	
}
