package com.controller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import com.dto.*;
import com.model.Invite;
import com.model.RentACar;
import com.model.RezervacijaRentACar;
import com.model.SignUpForm;
import com.model.user.Friend;
import com.model.user.User;
import com.repository.InviteRepository;
import com.repository.UserRepository;


import com.service.FriendService;
import com.service.InviteService;
import com.service.RezervacijaService;
import io.swagger.annotations.ApiOperation;
import com.service.UserService;
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
    private UserService userService;

    @Autowired
    InviteRepository inviteRepository;

    @Autowired
    InviteService inviteService;

    @Autowired
    FriendService friendService;

    @Autowired
    RezervacijaService rezervacijaService;

    @RequestMapping(value = "/getRent/{id}", method = RequestMethod.GET)
    public ResponseEntity<RentACarDTO> getUserRentACar(@PathVariable Long id) {

        return new ResponseEntity<>(userService.getRentACar(id),HttpStatus.OK);
    }

    @RequestMapping("/getRentACar")
    @PreAuthorize("hasRole('ADMIN_RENT')")
    public ResponseEntity<RentACarDTO> userRentACar() {

        return new ResponseEntity<>(userService.getUserRentACar(),HttpStatus.OK);

    }

    @RequestMapping(value = "/getUser", method = RequestMethod.GET)
    public ResponseEntity<SignUpForm> getUser(Principal user) {

        return new ResponseEntity<>(userService.getUserP(user), HttpStatus.OK);

    }


    @RequestMapping(value = "/checkReset",method = RequestMethod.GET)
    @PreAuthorize("hasRole('ADMIN_RENT') or hasRole('ADMIN_HOTEL') or hasRole('ADMIN_AVIO')")
    public ResponseEntity<?> checkReset(){
        userService.checkReset();
        return new ResponseEntity<>(HttpStatus.OK);
    }


    @RequestMapping(value = "/search/{s}",method = RequestMethod.GET)
    @PreAuthorize("hasRole('USER_REG')")
    public ResponseEntity<List<UserDTO>> search(@PathVariable  String s, Principal user) {
        Optional<User> u=this.userRepository.findByUsername(user.getName());
        List <UserDTO> pom=new ArrayList<>();
        Optional<List<User>> FN=Optional.of(new ArrayList<>());
        Optional<List<User>> LN=Optional.of(new ArrayList<>());
        if(u.isPresent()) {
            Optional<Friend> f = this.friendService.findById(u.get().getId());
            if (s.split(" ").length == 1) {
                FN = userRepository.findByFirstName(s);
                LN = userRepository.findByLastName(s);
            } else if (s.split(" ").length > 1) {
                FN = userRepository.findByFirstNameAndLastName(s.split(" ")[0], s.split(" ")[1]);
                LN = userRepository.findByFirstNameAndLastName(s.split(" ")[1], s.split(" ")[0]);
            }

            if (FN.isPresent() && f.isPresent()) {
                for (int i = 0; i < FN.get().size(); i++) {
                    if (!this.inviteRepository.findByUserSentAndUserReceiveAndReservation(u.get(), FN.get().get(i), null).isPresent() && !this.inviteRepository.findByUserSentAndUserReceiveAndReservation(FN.get().get(i), u.get(), null).isPresent() && !(u.get().getUsername().equals(FN.get().get(i).getUsername())) && !(f.get().getFriends().contains(FN.get().get(i))))
                        pom.add(new UserDTO(FN.get().get(i)));
                }
            }
            if (LN.isPresent() && f.isPresent()) {
                for (int i = 0; i < LN.get().size(); i++) {
                    if (!pom.contains(new UserDTO(LN.get().get(i))))
                        if (!this.inviteRepository.findByUserSentAndUserReceiveAndReservation(u.get(), LN.get().get(i), null).isPresent() && !this.inviteRepository.findByUserSentAndUserReceiveAndReservation(LN.get().get(i), u.get(), null).isPresent() && !(u.get().getUsername().equals(LN.get().get(i).getUsername()))&& !(f.get().getFriends().contains(LN.get().get(i))))
                            pom.add(new UserDTO(LN.get().get(i)));
                }
            }
            return new ResponseEntity<>(pom, HttpStatus.OK);
        }
        return new ResponseEntity<>( HttpStatus.BAD_REQUEST);
    }

    @RequestMapping(value = "/friendRequests",method = RequestMethod.GET)
    @PreAuthorize("hasRole('USER_REG')")
    public ResponseEntity<List<InviteDTO>> invitesForFriendship(Principal user) {
        return new ResponseEntity<>(this.inviteService.invitesForFriendship(user.getName()),HttpStatus.OK);
    }



    @RequestMapping(value = "/sendRequest", method=RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Salje poziv za prijateljstvo", httpMethod = "POST", produces = "application/json", consumes = "application/json")
    @PreAuthorize("hasRole('USER_REG')")
    public ResponseEntity<InviteDTO> sendRequest(@RequestBody InviteDTO inviteDTO, Principal user){
        return new ResponseEntity<>(this.inviteService.sendRequest(inviteDTO, user.getName()), HttpStatus.CREATED);
    }
    @RequestMapping(value = "/friends",method = RequestMethod.GET)
    @PreAuthorize("hasRole('USER_REG')")
    public ResponseEntity<List<UserDTO>> search( Principal user) {
        return new ResponseEntity<>(this.inviteService.search(user.getName()), HttpStatus.OK);
    }


    @RequestMapping(value = "/aRequest", method=RequestMethod.PUT,consumes = "application/json" )
    @PreAuthorize("hasRole('USER_REG')")
    public ResponseEntity<UserDTO> aRequest(@RequestBody InviteDTO inviteDTO, Principal user){
        return new ResponseEntity<>(this.inviteService.aRequest(inviteDTO, user.getName()), HttpStatus.OK);
    }

    @RequestMapping(value = "/eRequest", method=RequestMethod.PUT,consumes = "application/json" )
    @PreAuthorize("hasRole('USER_REG')")
    public ResponseEntity<UserDTO> eRequest(@RequestBody InviteDTO inviteDTO, Principal user){
        return new ResponseEntity<>(this.inviteService.eRequest(inviteDTO, user.getName()), HttpStatus.OK);
    }

    @RequestMapping(value = "/deleteFriend", method=RequestMethod.PUT,consumes = "application/json" )
    @PreAuthorize("hasRole('USER_REG')")
    public ResponseEntity<UserDTO> deleteFriend(@RequestBody UserDTO userDTO, Principal user){
        return new ResponseEntity<>(this.inviteService.deleteFriend(userDTO, user.getName()), HttpStatus.OK);
    }

    @RequestMapping(value = "/inviteFriend", method=RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Salje poziv za put", httpMethod = "POST", produces = "application/json", consumes = "application/json")
    @PreAuthorize("hasRole('USER_REG')")
    public ResponseEntity<InviteDTO> inviteFriend(@RequestBody InviteDTO inviteDTO, Principal user){
        return new ResponseEntity<>(this.rezervacijaService.inviteFriend(inviteDTO, user.getName()), HttpStatus.CREATED);
    }

    @RequestMapping(value = "/aRequestI/{id}", method=RequestMethod.DELETE )
    @ApiOperation(value = "Odbija poziv", httpMethod = "DELETE")
    @PreAuthorize("hasRole('USER_REG')")
    public ResponseEntity<Void> aRequestI(@PathVariable Long id){
        System.out.println("id je " + id);
        this.rezervacijaService.aFriend(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value = "/eRequestI", method=RequestMethod.PUT,consumes = "application/json" )
    @PreAuthorize("hasRole('USER_REG')")
    public ResponseEntity<String> eRequestI(@RequestBody InviteDTO inviteDTO, Principal user){
        this.rezervacijaService.eFriend(inviteDTO,user.getName());
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value = "/inviteRequests",method = RequestMethod.GET)
    @PreAuthorize("hasRole('USER_REG')")
    public ResponseEntity<List<InviteDTO>> inviteRequests(Principal user) {
        return new ResponseEntity<>(this.inviteService.inviteRequests(user.getName()),HttpStatus.OK);
    }


    @RequestMapping(value = "/sendMail", method=RequestMethod.PUT,consumes = "application/json" )
    @PreAuthorize("hasRole('USER_REG')")
    public ResponseEntity<String> sendMail(@RequestBody RezervacijaDTO rezervacijaDTO){
        this.rezervacijaService.sendEMail(rezervacijaDTO);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
