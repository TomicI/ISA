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
import com.model.user.Friend;
import com.model.user.User;
import com.repository.InviteRepository;
import com.repository.UserRepository;


import com.service.FriendService;
import com.service.InviteService;
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
    RentACarController rentACarController;

    @Autowired
    private UserService userService;

    @Autowired
    InviteRepository inviteRepository;

    @Autowired
    InviteService inviteService;

    @Autowired
    FriendService friendService;

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

        return new ResponseEntity<>(userService.getUserP(user), HttpStatus.OK);

    }


    @RequestMapping(value = "/checkReset",method = RequestMethod.GET)
    @PreAuthorize("hasRole('ADMIN_RENT') or hasRole('ADMIN_HOTEL') or hasRole('ADMIN_AVIO')")
    public ResponseEntity<?> checkReset(){
        userService.checkReset();
        return new ResponseEntity<>(HttpStatus.OK);
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
    @RequestMapping(value = "/friends",method = RequestMethod.GET)
    @PreAuthorize("hasRole('USER_REG')")
    public ResponseEntity<List<UserDTO>> search( Principal user) {
        Optional<User> us=this.userRepository.findByUsername(user.getName());

        List<UserDTO> friendsDTO=new ArrayList<>();
        if(us.isPresent()){
            Optional<Friend> f= this.friendService.findById(us.get().getId());
            if(f.isPresent()) {
                for (User u : f.get().getFriends()){
                    friendsDTO.add(new UserDTO(u));
                }
                return new ResponseEntity<>(friendsDTO, HttpStatus.OK);
            }

        }

        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }


    @RequestMapping(value = "/aRequest", method=RequestMethod.PUT,consumes = "application/json" )
    @PreAuthorize("hasRole('USER_REG')")
    public ResponseEntity<UserDTO> aRequest(@RequestBody InviteDTO inviteDTO, Principal user){
        Optional<User> us=this.userRepository.findByUsername(user.getName());
        if(us.isPresent()) {
            Optional<User> u=this.userRepository.findByUsername(inviteDTO.userSent.getUsername());
            Optional<Friend> f = this.friendService.findById(us.get().getId());
            Optional<Friend> f1 = this.friendService.findById(u.get().getId());

            if(f.isPresent() && u.isPresent() && f1.isPresent()){
                f.get().getFriends().add(u.get());
                f1.get().getFriends().add(us.get());
                Optional<Invite> in=this.inviteRepository.findById(inviteDTO.getId());
                if(in.isPresent()){
                    this.inviteRepository.delete(in.get());
                }
                return new ResponseEntity<>(new UserDTO(us.get()),HttpStatus.OK);
            }

        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @RequestMapping(value = "/eRequest", method=RequestMethod.PUT,consumes = "application/json" )
    @PreAuthorize("hasRole('USER_REG')")
    public ResponseEntity<UserDTO> eRequest(@RequestBody InviteDTO inviteDTO, Principal user){
        Optional<User> us=this.userRepository.findByUsername(user.getName());
        if(us.isPresent()) {
            Optional<Invite> in = this.inviteRepository.findById(inviteDTO.getId());
            if (in.isPresent()) {
                this.inviteRepository.delete(in.get());
                return new ResponseEntity<>(new UserDTO(us.get()), HttpStatus.OK);
            }
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @RequestMapping(value = "/deleteFriend", method=RequestMethod.PUT,consumes = "application/json" )
    @PreAuthorize("hasRole('USER_REG')")
    public ResponseEntity<UserDTO> deleteFriend(@RequestBody UserDTO userDTO, Principal user){
        Optional<User> us=this.userRepository.findByUsername(user.getName());
        if(us.isPresent()) {
            Optional<Friend> f = this.friendService.findById(us.get().getId());
            Optional<User> u=this.userRepository.findByUsername(userDTO.getUsername());
            Optional<Friend> f1 = this.friendService.findById(u.get().getId());
            if(f.isPresent() && u.isPresent()){
                f.get().getFriends().remove(u.get());
                f1.get().getFriends().remove(us.get());
                this.friendService.save(f.get());
                this.friendService.save(f1.get());
                return new ResponseEntity<>(new UserDTO(us.get()),HttpStatus.OK);
            }

        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
}
