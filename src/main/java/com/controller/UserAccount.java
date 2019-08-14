package com.controller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import com.dto.RentACarDTO;
import com.dto.RezervacijaRentACarDTO;
import com.model.RentACar;
import com.model.RezervacijaRentACar;
import com.model.SignUpForm;
import com.model.user.User;
import com.repository.UserRepository;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping(value = "api/useraccount")
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

    @RequestMapping(value = "/getUser", method = RequestMethod.GET)
    public ResponseEntity<SignUpForm> getUser(Principal user) {


        Optional<User> optionalUser = userRepository.findByUsername(user.getName());

        if (!optionalUser.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        SignUpForm temp = new SignUpForm();

        User tempu = optionalUser.get();

        temp.setUsername(tempu.getUsername());
        temp.setFirstName(tempu.getFirstName());
        temp.setLastName(tempu.getLastName());
        temp.setEmail(tempu.getEmail());

        return new ResponseEntity<>(temp, HttpStatus.OK);

    }

    @RequestMapping(value = "/getResVeh",method = RequestMethod.GET)
    @PreAuthorize("hasRole('USER_REG')")
    public ResponseEntity<?> getResVeh(Principal user) {
        System.out.println("Preuzima REZ");

        Optional<User> optionalUser = userRepository.findByUsername(user.getName());

        if (!optionalUser.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        Set<RezervacijaRentACar> rez = optionalUser.get().getRezervacije();

        List<RezervacijaRentACarDTO> rezDTO = new ArrayList<RezervacijaRentACarDTO>();

        for (RezervacijaRentACar r : rez) {

            Date dropOff=r.getDatumVracanja();

            if (dropOff.after(new Date())){
                if (!r.getOtkazana()){
                    RezervacijaRentACarDTO rtemp = new RezervacijaRentACarDTO(r);
                    rezDTO.add(rtemp);
                }
                
            }
            
        }

        return new ResponseEntity<>(rezDTO, HttpStatus.OK);
    }

    @RequestMapping(value = "/getResVehHist",method = RequestMethod.GET)
    @PreAuthorize("hasRole('USER_REG')")
    public ResponseEntity<?> getResVehHist(Principal user) {
        System.out.println("Preuzima REZ");

        int broj = 0;
        int drugi = 0;

        for (int i =0;i<24;i++){
            System.out.println(i+":"+drugi);
            drugi+=30;
            System.out.println(i+":"+drugi);
            drugi-=30;
        }

        Optional<User> optionalUser = userRepository.findByUsername(user.getName());

        if (!optionalUser.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        Set<RezervacijaRentACar> rez = optionalUser.get().getRezervacije();

        List<RezervacijaRentACarDTO> rezDTO = new ArrayList<RezervacijaRentACarDTO>();

        for (RezervacijaRentACar r : rez) {

            Date dropOff=r.getDatumVracanja();

            if (dropOff.before(new Date())){
                
                RezervacijaRentACarDTO rtemp = new RezervacijaRentACarDTO(r);
                rezDTO.add(rtemp);
            }

            if (r.getOtkazana()){
                RezervacijaRentACarDTO rtemp = new RezervacijaRentACarDTO(r);
                rezDTO.add(rtemp);
            }
            
        }

        return new ResponseEntity<>(rezDTO, HttpStatus.OK);
    }

}
