package com.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.model.user.User;

public interface UserRepository extends JpaRepository<User, Long> {
	
	Optional<User> findByUsername(String username);
    Boolean existsByUsername(String username);
    Boolean existsByEmail(String email);
    User findByEmail(String email);
    Optional<User> findById(Long id);
	
    
}
