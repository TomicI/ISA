package com.controller;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.model.LoginForm;
import com.model.SignUpForm;
import com.model.user.Role;
import com.model.user.User;
import com.model.user.VerificationToken;
import com.repository.RoleRepository;
import com.repository.UserRepository;
import com.repository.VerificationTokenRepository;
import com.security.JWToken;
import com.security.JwtResponse;
import com.security.ResponseMessage;
import com.service.UserService;

@CrossOrigin(origins ="http://localhost:4200")
@RestController
@RequestMapping("/api/auth")
public class AuthRestAPIs {
 
	@Autowired
	AuthenticationManager authenticationManager;
 
	@Autowired
	UserRepository userRepository;
 
	@Autowired
	RoleRepository roleRepository;
 
	@Autowired
	PasswordEncoder encoder;
 
	@Autowired
	JWToken jwtProvider;
	
	@Autowired
	UserService userService;
	
	@Autowired
    private JavaMailSender mailSender;
	
	@PostMapping("/signin")
	public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginForm loginRequest) {
 
		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
 
		SecurityContextHolder.getContext().setAuthentication(authentication);
 
		String jwt = jwtProvider.generateJWToken(authentication);
		UserDetails userDetails = (UserDetails) authentication.getPrincipal();
 
		return ResponseEntity.ok(new JwtResponse(jwt, userDetails.getUsername(), userDetails.getAuthorities()));
	}
 
	@PostMapping("/signup")
	public ResponseEntity<?> registerUser(@Valid @RequestBody SignUpForm signUpRequest) {
		if (userRepository.existsByUsername(signUpRequest.getUsername())) {
			return new ResponseEntity<>(new ResponseMessage("Username is already taken!"),
					HttpStatus.BAD_REQUEST);
		}
 
		if (userRepository.existsByEmail(signUpRequest.getEmail())) {
			return new ResponseEntity<>(new ResponseMessage("Email is already in use!"),
					HttpStatus.BAD_REQUEST);
		}
		
 
		// Creating user's account
		User user = new User(signUpRequest.getUsername(), 
				encoder.encode(signUpRequest.getPassword())
				,signUpRequest.getFirstName(),
				signUpRequest.getLastName(),
				signUpRequest.getEmail());
		
		Set<Role> tempRoles = new HashSet<>();
 
		Role role = roleRepository.findByName("ROLE_USER_REG").orElseThrow(() 
				-> new RuntimeException("Role can't be found!"));
				
		
		tempRoles.add(role);
		user.setRoles(tempRoles);
		
		String token = UUID.randomUUID().toString();
        String recipientAddress = user.getEmail();
        
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(recipientAddress);
        message.setSubject("Complete Registration!");
        message.setText("To confirm your account, please click here : "
        +"http://localhost:8080/api/auth/confirmReg?token="+token);
        

		userRepository.save(user);
		userService.createVerificationToken(user, token);
		
		mailSender.send(message);
 
		return new ResponseEntity<>(new ResponseMessage("User registered successfully!"), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/confirmReg")
	public ResponseEntity<?> confirmReg(@RequestParam("token") String token) {
	  
	     
	    VerificationToken verificationToken = userService.getToken(token);
	    if (verificationToken == null) {
	    	return new ResponseEntity<>(new ResponseMessage("INVALID Token!"),
					HttpStatus.BAD_REQUEST);
	    }
	     
	    User user = verificationToken.getUser();
	    Calendar cal = Calendar.getInstance();
	    if ((verificationToken.getExpiryDate().getTime() - cal.getTime().getTime()) <= 0) {
	    	return new ResponseEntity<>(new ResponseMessage("Token Expired!"),
					HttpStatus.BAD_REQUEST);
	    } 
	     
	    user.setEnabled(true); 
	    userRepository.save(user); 
	    
	    return new ResponseEntity<>(new ResponseMessage("Account activated successfully!"), HttpStatus.OK);
	    
	}

	
	
}