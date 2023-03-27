package com.example.insurance.dao;

import java.io.Serializable;

public class JwtResponse implements Serializable {

	private static final long serialVersionUID = -8091879091924046844L;
	private final String jwttoken;



	public JwtResponse(String token) {
		// TODO Auto-generated constructor stub
		this.jwttoken = token;

	}

	public String getToken() {
		return this.jwttoken;
	}
}
