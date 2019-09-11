package com.service;

import com.model.LoginForm;
import com.model.SignUpForm;
import com.model.user.Role;
import com.model.user.User;
import com.model.user.VerificationToken;
import com.repository.RoleRepository;
import com.repository.UserRepository;
import com.security.JWToken;
import com.security.JwtResponse;
import com.security.ResponseMessage;
import org.springframework.beans.factory.annotation.Autowired;
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
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.transaction.Transactional;
import java.util.Calendar;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Service
@Transactional
public class AuthService {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder encoder;

    @Autowired
    private JWToken jwtProvider;

    @Autowired
    private UserService userService;

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private UserDetailsServiceImpl userDetails;


    public JwtResponse authenticateUser(LoginForm loginRequest){

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = jwtProvider.generateJWToken(authentication);
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();

        return new JwtResponse(jwt, userDetails.getUsername(), userDetails.getAuthorities());
    }

    public ResponseMessage signUp(SignUpForm signUpRequest){

        if (userRepository.existsByUsername(signUpRequest.getUsername())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Username is already taken!");
        }

        if (userRepository.existsByEmail(signUpRequest.getEmail())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Email is registered!");
        }

        // Creating user's account
        User user = new User(signUpRequest.getUsername(), encoder.encode(signUpRequest.getPassword()),
                signUpRequest.getFirstName(), signUpRequest.getLastName(), signUpRequest.getEmail(),signUpRequest.getCity(),signUpRequest.getPhone());

        user.setCity(signUpRequest.getCity());
        user.setPhone(signUpRequest.getPhone());
        user.setBrojPasosa(signUpRequest.getPassport());

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

       return new ResponseMessage("Account activated successfully!");

    }

    public ResponseMessage confirmReg(String token){

        VerificationToken verificationToken = userService.getToken(token);
        if (verificationToken == null) {
            throw new ResponseStatusException(  HttpStatus.BAD_REQUEST,  "INVALID Token!" );
        }

        User user = verificationToken.getUser();
        Calendar cal = Calendar.getInstance();
        if ((verificationToken.getExpiryDate().getTime() - cal.getTime().getTime()) <= 0) {
            throw new ResponseStatusException(  HttpStatus.BAD_REQUEST,  "Token Expired!" );
        }

        user.setEnabled(true);
        userRepository.save(user);


        return new ResponseMessage("Account activated successfully!");

    }








}
