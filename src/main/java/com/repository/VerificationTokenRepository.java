package com.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.model.user.User;
import com.model.user.VerificationToken;

public interface VerificationTokenRepository extends JpaRepository<VerificationToken, Long>{
	
	VerificationToken findByVerificationToken (String token);
	
	VerificationToken findByUser (User user);

}
