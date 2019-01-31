package com.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.model.user.Role;

public interface RoleRepository extends JpaRepository<Role, Long>{
	
	Optional<Role> findByName(String name);
	
}
