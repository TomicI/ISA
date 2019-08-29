package com.controller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import com.dto.InviteDTO;
import com.dto.RentACarDTO;
import com.dto.RezervacijaRentACarDTO;
import com.dto.UserDTO;
import com.model.Invite;
import com.model.RentACar;
import com.model.RezervacijaRentACar;
import com.model.SignUpForm;
import com.model.user.User;
import com.repository.InviteRepository;
import com.repository.UserRepository;


import com.service.InviteService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.swing.text.html.Option;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping(value = "api/useraccount")
public class UserAccount {

    @Autowired
    UserRepository userRepository;

    @Autowired
    RentACarController rentACarController;

    @Autowired
    InviteRepository inviteRepository;

    @Autowired
    InviteService inviteService;

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
        temp.setReset(tempu.isReset());
        temp.setCity(tempu.getCity());
        temp.setPhone(tempu.getPhone());
        temp.setLastPasswordResetDate(tempu.getLastPasswordResetDate());

        return new ResponseEntity<>(temp, HttpStatus.OK);

    }

    @RequestMapping(value = "/getResVeh",method = RequestMethod.GET)
    @PreAuthorize("hasRole('USER_REG')")
    public ResponseEntity<?> getResVeh(Principal user) {
        System.out.println("Preuzima REZ");

        Optional<User> optionalUser = userRepository.findByUsername(user.getName());

        /*if (!optionalUser.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        Set<RezervacijaRentACar> rez = optionalUser.get().getRezervacije();*/

        List<RezervacijaRentACarDTO> rezDTO = new ArrayList<RezervacijaRentACarDTO>();

        /*for (RezervacijaRentACar r : rez) {

            Date dropOff=r.getDatumVracanja();

            if (dropOff.after(new Date())){
                if (!r.getOtkazana()){
                    RezervacijaRentACarDTO rtemp = new RezervacijaRentACarDTO(r);
                    rezDTO.add(rtemp);
                }
                
            }
            
        }*/

        return new ResponseEntity<>(rezDTO, HttpStatus.OK);
    }

    @RequestMapping(value = "/getResVehHist",method = RequestMethod.GET)
    @PreAuthorize("hasRole('USER_REG')")
    public ResponseEntity<?> getResVehHist(Principal user) {
        System.out.println("Preuzima REZ");

        /*int broj = 0;
        int drugi = 0;

        for (int i =0;i<24;i++){
            System.out.println(i+":"+drugi);
            drugi+=30;
            System.out.println(i+":"+drugi);
            drugi-=30;
        }*/

        Optional<User> optionalUser = userRepository.findByUsername(user.getName());

        if (!optionalUser.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        //Set<RezervacijaRentACar> rez = optionalUser.get().getRezervacije();

        List<RezervacijaRentACarDTO> rezDTO = new ArrayList<RezervacijaRentACarDTO>();

        /*for (RezervacijaRentACar r : rez) {

            Date dropOff=r.getDatumVracanja();

            if (dropOff.before(new Date())){
                
                RezervacijaRentACarDTO rtemp = new RezervacijaRentACarDTO(r);
                rezDTO.add(rtemp);
            }

            if (r.getOtkazana()){
                RezervacijaRentACarDTO rtemp = new RezervacijaRentACarDTO(r);
                rezDTO.add(rtemp);
            }
            
        }*/

        return new ResponseEntity<>(rezDTO, HttpStatus.OK);
    }

    @RequestMapping(value = "/search/{s}",method = RequestMethod.GET)
    @PreAuthorize("hasRole('USER_REG')")
    public ResponseEntity<List<UserDTO>> search(@PathVariable  String s, Principal user) {
        Optional<User> u=this.userRepository.findByUsername(user.getName());
        List <UserDTO> pom=new ArrayList<>();
        Optional<List<User>> FN=Optional.of(new ArrayList<>());
        Optional<List<User>> LN=Optional.of(new ArrayList<>());

        if(s.split(" ").length==1){
            FN=userRepository.findByFirstName(s);
            LN=userRepository.findByLastName(s);
        } else if(s.split(" ").length>1){
            FN=userRepository.findByFirstNameAndLastName(s.split(" ")[0], s.split(" ")[1]);
            LN=userRepository.findByFirstNameAndLastName(s.split(" ")[1], s.split(" ")[0]);
        }

        if(FN.isPresent() && u.isPresent()) {
            for (int i = 0; i < FN.get().size(); i++) {
               if(!this.inviteRepository.findByUserSentAndUserReceiveAndReservation(u.get(), FN.get().get(i), null).isPresent() && !this.inviteRepository.findByUserSentAndUserReceiveAndReservation( FN.get().get(i), u.get(), null).isPresent() )
                    pom.add(new UserDTO(FN.get().get(i)));
            }
        }
        if(LN.isPresent() && u.isPresent()) {
            for (int i = 0; i < LN.get().size(); i++) {
                if (!pom.contains(new UserDTO(LN.get().get(i))))
                    if(!this.inviteRepository.findByUserSentAndUserReceiveAndReservation(u.get(), LN.get().get(i), null).isPresent() && !this.inviteRepository.findByUserSentAndUserReceiveAndReservation( LN.get().get(i), u.get(), null).isPresent() )
                        pom.add(new UserDTO(LN.get().get(i)));
            }
        }
        return new ResponseEntity<>(pom, HttpStatus.OK);
    }

    @RequestMapping(value = "/friendRequests",method = RequestMethod.GET)
    @PreAuthorize("hasRole('USER_REG')")
    public ResponseEntity<List<InviteDTO>> invitesForFriendship(Principal user) {
        Optional<User> u=this.userRepository.findByUsername(user.getName());
        List<InviteDTO> inviteDTOS=new ArrayList<>();
        Optional<List<Invite>> invites=this.inviteRepository.findByUserReceiveAndReservation(u.get(), null);
        if(u.isPresent() && invites.isPresent()){
            for(Invite i: invites.get()){
                inviteDTOS.add(new InviteDTO(i));
            }
        }
        return new ResponseEntity<>(inviteDTOS,HttpStatus.OK);
    }



    @RequestMapping(value = "/sendRequest", method=RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Salje poziv za prijateljstvo", httpMethod = "POST", produces = "application/json", consumes = "application/json")
    @PreAuthorize("hasRole('USER_REG')")
    public ResponseEntity<InviteDTO> sendRequest(@RequestBody InviteDTO inviteDTO, Principal user){
        Invite invite=new Invite();

        invite.setUserSent(this.userRepository.findByUsername(user.getName()).get());
        invite.setUserReceive(this.userRepository.findByUsername(inviteDTO.userReceive.getUsername()).get());
        invite.setDateSent(new Date());
        invite.setReservation(null);
        invite=this.inviteService.save(invite);


            return new ResponseEntity<>(new InviteDTO(invite), HttpStatus.CREATED);
    }
}
