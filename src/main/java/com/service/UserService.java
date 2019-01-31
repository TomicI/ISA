package com.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.model.SignUpForm;
import com.model.user.User;
import com.model.user.VerificationToken;
import com.repository.UserRepository;
import com.repository.VerificationTokenRepository;

@Service
@Transactional
public class UserService {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private VerificationTokenRepository tokenRepository;
	
	
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
    }
    
    public void createVerificationToken(User user, String token) {
        VerificationToken myToken = new VerificationToken(token, user);
        tokenRepository.save(myToken);
    }

    
     
    
	
}
