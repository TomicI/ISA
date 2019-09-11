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

import com.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.access.prepost.PreAuthorize;
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
	private UserDetailsServiceImpl userDetails;

	@Autowired
	private AuthService authService;

	@PostMapping("/signin")
	public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginForm loginRequest) {

		return ResponseEntity.ok(authService.authenticateUser(loginRequest));
	}

	@PostMapping("/signup")
	public ResponseEntity<?> registerUser(@Valid @RequestBody SignUpForm signUpRequest) {

		return new ResponseEntity<>(authService.signUp(signUpRequest), HttpStatus.OK);
	}

	@RequestMapping(value = "/confirmReg")
	public ResponseEntity<?> confirmReg(@RequestParam("token") String token) {

		return new ResponseEntity<>(authService.confirmReg(token), HttpStatus.OK);

	}

	@RequestMapping(value = "/changePass", method = RequestMethod.POST)
	public ResponseEntity<?> changePass(@RequestBody PasswordChange passwordChange) {

		userDetails.changePass(passwordChange.getOldPass(), passwordChange.getNewPass());

		return new ResponseEntity<>(new ResponseMessage("Password changed!"), HttpStatus.OK);

	}

	@RequestMapping(value = "/changeAdminPass", method = RequestMethod.POST)
	@PreAuthorize("hasRole('ADMIN_RENT') or hasRole('ADMIN_HOTEL') or hasRole('ADMIN_AVIO')")
	public ResponseEntity<?> changeAdminPass(@RequestBody PasswordChange passwordChange) {

		boolean status = userDetails.changeAdminPass(passwordChange.getNewPass());

		if(!status){
			return new ResponseEntity<>(new ResponseMessage("Password already reset!"), HttpStatus.BAD_REQUEST);
		}else
			return new ResponseEntity<>(new ResponseMessage("Password changed!"), HttpStatus.OK);
	}


	@RequestMapping(value = "/changeUsername", method = RequestMethod.POST)
	public ResponseEntity<?> changeUsername(@RequestBody SignUpForm signUpRequest) {


		return ResponseEntity.ok(userDetails.changeUsername(signUpRequest));
	}

	@RequestMapping(value = "/changeName", method = RequestMethod.POST)
	public ResponseEntity<?> changeName(@RequestBody SignUpForm signUpRequest) {

		userDetails.changeName(signUpRequest.getFirstName(),signUpRequest.getLastName());
		return new ResponseEntity<>(new ResponseMessage("Name changed!"), HttpStatus.OK);
	}

	@RequestMapping(value = "/changeEmail", method = RequestMethod.POST)
	public ResponseEntity<?> changeEmail(@RequestBody SignUpForm signUpRequest) {
		userDetails.changeEmail(signUpRequest);
		return new ResponseEntity<>(new ResponseMessage("Email changed!"), HttpStatus.OK);
	}

}
