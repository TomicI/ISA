package com.controller;

import java.security.Principal;
import java.util.Optional;

import com.dto.RentACarDTO;
import com.model.RentACar;
import com.model.user.User;
import com.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping(value="api/useraccount")
public class UserAccount {

    @Autowired
    UserRepository userRepository;

    @Autowired
    RentACarController rentACarController;


    @RequestMapping(value = "/getRent/{id}", method = RequestMethod.GET)
    public ResponseEntity<RentACarDTO> getUserRentACar(@PathVariable Long id) {

        Optional<User> optionalUser = userRepository.findById(id);

        if (optionalUser.isPresent()) {
            RentACar rentACar = optionalUser.get().getRentACar();
            if (rentACar != null) {
                return new ResponseEntity<>(new RentACarDTO(rentACar), HttpStatus.OK);
            } else
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @RequestMapping("/getRentACar")
    @PreAuthorize("hasRole('ADMIN_RENT')")
	public ResponseEntity<RentACarDTO> user(Principal user) {
        Optional<User> optionalUser = userRepository.findByUsername(user.getName());
        
        if (optionalUser.isPresent()) {
            RentACar rentACar = optionalUser.get().getRentACar();
            if (rentACar != null) {
                return new ResponseEntity<>(new RentACarDTO(rentACar), HttpStatus.OK);
            } else
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

	}

}
