package com.example.insurance.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.insurance.dao.UserDao;
@Repository
public interface UserRepository extends JpaRepository<UserDao, Long>{
	
	@Query(value = "SELECT u"
			+ " FROM UserDao u ")
	Page<UserDao> findUserPage(Pageable pageable);
	
	UserDao findUserDaoByEmail (String email);
	
}
