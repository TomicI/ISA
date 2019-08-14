package com.controller;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpHeaders;
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
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.dto.PasswordChange;
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
import com.service.UserDetailsServiceImpl;
import com.service.UserService;

@CrossOrigin(origins = "http://localhost:4200")
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
	UserDetailsServiceImpl userDetails;

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
			return new ResponseEntity<>(new ResponseMessage("Username is already taken!"), HttpStatus.BAD_REQUEST);
		}

		if (userRepository.existsByEmail(signUpRequest.getEmail())) {
			return new ResponseEntity<>(new ResponseMessage("Email is already in use!"), HttpStatus.BAD_REQUEST);
		}

		// Creating user's account
		User user = new User(signUpRequest.getUsername(), encoder.encode(signUpRequest.getPassword()),
				signUpRequest.getFirstName(), signUpRequest.getLastName(), signUpRequest.getEmail());

		Set<Role> tempRoles = new HashSet<>();

		Role role = roleRepository.findByName("ROLE_USER_REG")
				.orElseThrow(() -> new RuntimeException("Role can't be found!"));

		tempRoles.add(role);
		user.setRoles(tempRoles);

		String token = UUID.randomUUID().toString();
		String recipientAddress = user.getEmail();

		SimpleMailMessage message = new SimpleMailMessage();
		message.setTo(recipientAddress);
		message.setSubject("Complete Registration!");
		message.setText("To confirm your account, please click here : "
				+ "http://localhost:8080/api/auth/confirmReg?token=" + token);

		userRepository.save(user);
		userService.createVerificationToken(user, token);

		mailSender.send(message);

		return new ResponseEntity<>(new ResponseMessage("User registered successfully!"), HttpStatus.OK);
	}

	@RequestMapping(value = "/confirmReg")
	public ResponseEntity<?> confirmReg(@RequestParam("token") String token) {

		VerificationToken verificationToken = userService.getToken(token);
		if (verificationToken == null) {
			return new ResponseEntity<>(new ResponseMessage("INVALID Token!"), HttpStatus.BAD_REQUEST);
		}

		User user = verificationToken.getUser();
		Calendar cal = Calendar.getInstance();
		if ((verificationToken.getExpiryDate().getTime() - cal.getTime().getTime()) <= 0) {
			return new ResponseEntity<>(new ResponseMessage("Token Expired!"), HttpStatus.BAD_REQUEST);
		}

		user.setEnabled(true);
		userRepository.save(user);

		return new ResponseEntity<>(new ResponseMessage("Account activated successfully!"), HttpStatus.OK);

	}

	@RequestMapping(value = "/changePass", method = RequestMethod.POST)
	public ResponseEntity<?> changePass(@RequestBody PasswordChange passwordChange) {

		userDetails.changePass(passwordChange.getOldPass(), passwordChange.getNewPass());

		return new ResponseEntity<>(new ResponseMessage("Password changed!"), HttpStatus.OK);

	}

	@RequestMapping(value = "/changeUsername", method = RequestMethod.POST)
	public ResponseEntity<?> changeUsername(@RequestBody SignUpForm signUpRequest) {

		if (signUpRequest.getUsername().isEmpty() || signUpRequest.getUsername().length() < 3) {
			return new ResponseEntity<>(new ResponseMessage("Minimum 3 characters!"), HttpStatus.BAD_REQUEST);
		}

		if (userRepository.existsByUsername(signUpRequest.getUsername())) {
			return new ResponseEntity<>(new ResponseMessage("Username is already taken!"), HttpStatus.BAD_REQUEST);
		}

		Authentication current = SecurityContextHolder.getContext().getAuthentication();
		String oldUsername = current.getName();

		User userDet = (User) current.getPrincipal();
		userDet.setUsername(signUpRequest.getUsername());

		String jwt = jwtProvider.generateJWToken(current);
		UserDetails userDetails = (UserDetails) current.getPrincipal();

		Optional<User> userOpt = userRepository.findByUsername(oldUsername);
		userOpt.get().setUsername(signUpRequest.getUsername());
		userRepository.save(userOpt.get());

		return ResponseEntity.ok(new JwtResponse(jwt, userDetails.getUsername(), userDetails.getAuthorities()));
	}

	@RequestMapping(value = "/changeName", method = RequestMethod.POST)
	public ResponseEntity<?> changeName(@RequestBody SignUpForm signUpRequest) {

		String first = signUpRequest.getFirstName();
		String last = signUpRequest.getLastName();

		if (first.length() < 3 || last.length() < 3) {
			return new ResponseEntity<>(new ResponseMessage("Minimum 3 characters!"), HttpStatus.BAD_REQUEST);
		}

		if (first.length() > 20 || last.length() > 20) {
			return new ResponseEntity<>(new ResponseMessage("Max 20 characters!"), HttpStatus.BAD_REQUEST);
		}

		userDetails.changeName(first, last);

		return new ResponseEntity<>(new ResponseMessage("Name changed!"), HttpStatus.OK);
	}

	@RequestMapping(value = "/changeEmail", method = RequestMethod.POST)
	public ResponseEntity<?> changeEmail(@RequestBody SignUpForm signUpRequest) {

		if (signUpRequest.getEmail().isEmpty()) {
			return new ResponseEntity<>(new ResponseMessage("Can't be empty!"), HttpStatus.BAD_REQUEST);
		}

		if (signUpRequest.getEmail().length() > 60) {
			return new ResponseEntity<>(new ResponseMessage("Max 60 characters!"), HttpStatus.BAD_REQUEST);
		}

		if (userRepository.existsByEmail(signUpRequest.getEmail())) {
			return new ResponseEntity<>(new ResponseMessage("Email is already in use!"), HttpStatus.BAD_REQUEST);
		}

		userDetails.changeEmail(signUpRequest.getEmail());

		return new ResponseEntity<>(new ResponseMessage("Email changed!"), HttpStatus.OK);
	}

	@RequestMapping("/to-be-redirected")
	public String processForm(HttpServletRequest request) {
		String redirectUrl = ":localhost:4200/profile";
		return "redirect:" + redirectUrl;
	}

	@RequestMapping(value = "/t", method = RequestMethod.GET)
	public ResponseEntity<Object> redirectToExternalUrl() throws URISyntaxException {
		URI uri = new URI("http://www.google.com");
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setLocation(uri);
		return new ResponseEntity<>(httpHeaders, HttpStatus.SEE_OTHER);
	}
}