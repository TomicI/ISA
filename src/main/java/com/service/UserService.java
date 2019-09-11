package com.service;

import java.security.Principal;
import java.util.Optional;

import javax.transaction.Transactional;

import com.dto.RentACarDTO;
import com.model.user.Friend;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.model.RentACar;
import com.model.SignUpForm;
import com.model.user.User;
import com.model.user.VerificationToken;
import com.repository.UserRepository;
import com.repository.VerificationTokenRepository;
import org.springframework.web.server.ResponseStatusException;

@Service
@Transactional
public class UserService {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private VerificationTokenRepository tokenRepository;

	@Autowired
    private FriendService friendService;
	
	public boolean checkIfEmailExists(String email) {
        User user = userRepository.findByEmail(email);
        if (user != null) {
            return true;
        }
        return false;
    }
     
 
    public User getUser(String verificationToken) {
        User user = tokenRepository.findByVerificationToken(verificationToken).getUser();
        return user;
    }
     
    public VerificationToken getToken(String VerificationToken) {
        return tokenRepository.findByVerificationToken(VerificationToken);
    }
     

    public void saveUser(User user) {

    	userRepository.save(user);

    	this.friendService.save(new Friend());
    }
    
    public void createVerificationToken(User user, String token) {
        VerificationToken myToken = new VerificationToken(token, user);
        tokenRepository.save(myToken);
    }


    public Optional<User> findOne(Long id) {
       return userRepository.findById(id);
        
    }




    public User getOne(Long id){
	    Optional<User> optionalUser = findOne(id);
        if (!optionalUser.isPresent()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User doesn't exist!");
        }
        return optionalUser.get();
    }
    
    public Optional<User> findByUsername(String username){

	    return userRepository.findByUsername(username);
    }


    public User getByUsername(String username){

        Optional<User> optionalUser = userRepository.findByUsername(username);

        if (!optionalUser.isPresent()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User doesn't exist!");
        }

        return optionalUser.get();
    }


    public SignUpForm getUserP(Principal user){
        SignUpForm temp = new SignUpForm();

        User tempu = getByUsername(user.getName());

        temp.setUsername(tempu.getUsername());
        temp.setFirstName(tempu.getFirstName());
        temp.setLastName(tempu.getLastName());
        temp.setEmail(tempu.getEmail());
        temp.setReset(tempu.isReset());
        temp.setCity(tempu.getCity());
        temp.setPhone(tempu.getPhone());
        temp.setPassport(tempu.getBrojPasosa());
        temp.setLastPasswordResetDate(tempu.getLastPasswordResetDate());

        return temp;
    }

    public void checkReset(){

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = getByUsername(auth.getName());

        if (!user.isReset()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }

    }

    public RentACarDTO getRentACar(Long id){

	    User user = getOne(id);

        if(user.getRentACar()==null){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        return new RentACarDTO(user.getRentACar());

    }

    public RentACarDTO getUserRentACar(){

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = getByUsername(auth.getName());

        if(user.getRentACar()==null){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        return new RentACarDTO(user.getRentACar());

    }
    
	
}
