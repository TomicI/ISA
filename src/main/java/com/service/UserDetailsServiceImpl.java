package com.service;

import java.util.Date;
import java.util.Optional;
import java.util.logging.Logger;

import javax.transaction.Transactional;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.model.user.User;
import com.repository.UserRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
	
	protected final Log LOGGER = LogFactory.getLog(getClass());

	@Autowired
	UserRepository userRepository;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Override
	@Transactional
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		User user = userRepository.findByUsername(username).orElseThrow(
				() -> new UsernameNotFoundException("User Not Found with -> username or email : " + username));
 
		return user;
		
	}
	
	public void changePass(String oldPass,String newPass) {
		
		Authentication current = SecurityContextHolder.getContext().getAuthentication();
		String username = current.getName();
		
		if (authenticationManager !=null) {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username,oldPass));
		}else {
			return;
		}
		
		Optional<User> userOpt = userRepository.findByUsername(username);
		
		userOpt.get().setPassword(passwordEncoder.encode(newPass));
		userOpt.get().setLastPasswordResetDate(new Date());
		userRepository.save(userOpt.get());

	}

	public boolean changeAdminPass(String newPass){

		Authentication current = SecurityContextHolder.getContext().getAuthentication();
		String username = current.getName();

		Optional<User> userOpt = userRepository.findByUsername(username);

		if (!userOpt.get().isReset()){
			userOpt.get().setPassword(passwordEncoder.encode(newPass));
			userOpt.get().setLastPasswordResetDate(new Date());
			userOpt.get().setReset(true);
			userRepository.save(userOpt.get());
			return true;
		}

		return false;

	}


	public void changeName(String first,String last){

		Authentication current = SecurityContextHolder.getContext().getAuthentication();
		String username = current.getName();

		if (authenticationManager == null){
			return;
		}

		User ud = (User) loadUserByUsername(username);
		ud.setFirstName(first);
		ud.setLastName(last);

		Optional<User> userOpt = userRepository.findByUsername(username);
		userOpt.get().setFirstName(first);
		userOpt.get().setLastName(last);

		userRepository.save(userOpt.get());
	}

	public void changeEmail(String email){

		Authentication current = SecurityContextHolder.getContext().getAuthentication();
		String username = current.getName();

		if (authenticationManager == null){
			return;
		}

		User ud = (User) loadUserByUsername(username);
		ud.setEmail(email);

		Optional<User> userOpt = userRepository.findByUsername(username);
		userOpt.get().setEmail(email);
		

		userRepository.save(userOpt.get());
	}



}
