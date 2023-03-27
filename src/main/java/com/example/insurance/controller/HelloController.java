package com.example.insurance.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.insurance.config.JwtUtil;
import com.example.insurance.dao.JwtRequest;
import com.example.insurance.dao.JwtResponse;
import com.example.insurance.services.MyUserDetailService;

import io.swagger.v3.oas.annotations.parameters.RequestBody;


@RestController
@CrossOrigin
@RequestMapping("/api/")
public class HelloController {
		@Autowired
		private AuthenticationManager authenticationManager;
		
		@Autowired
		private MyUserDetailService userDetailService;
		
		@Autowired
		JwtUtil jwtUtil;
	
	@GetMapping({ "hello" })
    public String firstPage() {
        return "Hello World";
    }
	
	@GetMapping("authenticate")
	public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtRequest authenticationRequest) throws Exception {
		System.out.println(authenticationRequest.getPassword());
		authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());

		UserDetails  userDetails = userDetailService
				.loadUserByUsername(authenticationRequest.getUsername());

		final String token =jwtUtil.generateToken(userDetails);

		return ResponseEntity.ok(new JwtResponse(token));
	}
	
	private void authenticate(String username, String password) throws Exception {
		try {System.out.println("helloSUTH");
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
		} catch (DisabledException e) {
			throw new Exception("USER_DISABLED", e);
		} catch (BadCredentialsException e) {
			throw new Exception("INVALID_CREDENTIALS", e);
		}
	}
}
