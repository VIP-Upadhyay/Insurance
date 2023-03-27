package com.example.insurance.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.insurance.dao.UserDao;
import com.example.insurance.repository.UserRepository;
@Service
public class MyUserDetailService implements org.springframework.security.core.userdetails.UserDetailsService {

	@Autowired
	UserRepository repository;
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		 UserDao user = repository.findUserDaoByEmail(username);
		 if (user==null) {		
				throw new UsernameNotFoundException("not found");		
			}
	        return user;
	    }
}
