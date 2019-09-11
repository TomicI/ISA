package com.service;

import com.dto.InviteDTO;
import com.dto.UserDTO;
import com.model.Invite;
import com.model.user.Friend;
import com.model.user.User;
import com.repository.InviteRepository;
import com.repository.UserRepository;
import net.bytebuddy.asm.Advice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.*;

@Service
public class InviteService {

    @Autowired
    private InviteRepository inviteRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private FriendService friendService;

    @Autowired
    private RezervacijaService rezervacijaService;

    public List<Invite> getAll() {
        return inviteRepository.findAll();
    }

    public Invite save(Invite invite){
       return this.inviteRepository.save(invite);
    }
    public Optional<Invite> findByID(Long id){
        return this.inviteRepository.findById(id);
    }

    public List<Invite> findByUserAndAcc(Long id,boolean acc){
        return this.inviteRepository.findByUserReceiveIdAndAccepted(id,acc);
    }

    public boolean existsByResAndUser(Long res_id,Long user_id,boolean acc){
        return this.inviteRepository.existsByReservationIdAndUserReceiveIdAndAccepted(res_id,user_id,acc);
    }

    public void delete(Invite invite){
        this.inviteRepository.delete(invite);
    }

    public List<InviteDTO> invitesForFriendship(String user) {
        Optional<User> u = this.userRepository.findByUsername(user);
        List<InviteDTO> inviteDTOS = new ArrayList<>();
        Optional<List<Invite>> invites = this.inviteRepository.findByUserReceiveAndReservation(u.get(), null);
        if (u.isPresent() && invites.isPresent()) {
            for (Invite i : invites.get()) {
                inviteDTOS.add(new InviteDTO(i));
            }
        }
        return inviteDTOS;
    }

    public InviteDTO sendRequest(InviteDTO inviteDTO, String user){
        Invite invite=new Invite();

        invite.setUserSent(this.userRepository.findByUsername(user).get());
        invite.setUserReceive(this.userRepository.findByUsername(inviteDTO.userReceive.getUsername()).get());
        invite.setDateSent(new Date());
        invite.setReservation(null);
        invite=this.inviteRepository.save(invite);

        return new InviteDTO(invite);

    }

    public List<UserDTO> search(String user) {
        Optional<User> us = this.userRepository.findByUsername(user);

        List<UserDTO> friendsDTO = new ArrayList<>();
        if (us.isPresent()) {
            Optional<Friend> f = this.friendService.findById(us.get().getId());
            if (f.isPresent()) {
                for (User u : f.get().getFriends()) {
                    friendsDTO.add(new UserDTO(u));
                }

            }

        }
        return friendsDTO;
    }

    public UserDTO aRequest(InviteDTO inviteDTO, String user) {
        Optional<User> us = this.userRepository.findByUsername(user);
        if (us.isPresent()) {
            Optional<User> u = this.userRepository.findByUsername(inviteDTO.userSent.getUsername());
            Optional<Friend> f = this.friendService.findById(us.get().getId());
            Optional<Friend> f1 = this.friendService.findById(u.get().getId());

            if (f.isPresent() && u.isPresent() && f1.isPresent()) {
                f.get().getFriends().add(u.get());
                f1.get().getFriends().add(us.get());
                Optional<Invite> in = this.inviteRepository.findById(inviteDTO.getId());
                if (in.isPresent()) {
                    this.inviteRepository.delete(in.get());
                }

            }

        }
        return new UserDTO(us.get());
    }

    public UserDTO eRequest(InviteDTO inviteDTO, String user) {
        Optional<User> us = this.userRepository.findByUsername(user);
        if (us.isPresent()) {
            Optional<Invite> in = this.inviteRepository.findById(inviteDTO.getId());
            if (in.isPresent()) {
                this.inviteRepository.delete(in.get());

            }
        }
        return new UserDTO(us.get());
    }

    public UserDTO deleteFriend(UserDTO userDTO, String user) {
        Optional<User> us = this.userRepository.findByUsername(user);
        if (us.isPresent()) {
            Optional<Friend> f = this.friendService.findById(us.get().getId());
            Optional<User> u = this.userRepository.findByUsername(userDTO.getUsername());
            Optional<Friend> f1 = this.friendService.findById(u.get().getId());
            if (f.isPresent() && u.isPresent()) {
                f.get().getFriends().remove(u.get());
                f1.get().getFriends().remove(us.get());
                this.friendService.save(f.get());
                this.friendService.save(f1.get());

            }

        }
        return new UserDTO(us.get());
    }

    public List<InviteDTO> inviteRequests(String user) {
        Calendar calendar = Calendar.getInstance();
        Optional<User> u = this.userRepository.findByUsername(user);
        List<InviteDTO> inviteDTOS = new ArrayList<>();
        Optional<List<Invite>> invites = this.inviteRepository.findByUserReceive(u.get());
        if (u.isPresent() && invites.isPresent()) {
            for (Invite i : invites.get()) {
                if(i.getReservation()!= null && !i.isAccepted()){

                    long timeRes = i.getReservation().getDatumVremeP().getTime()-calendar.getTime().getTime();
                    long timeSent = calendar.getTime().getTime() - i.getDateSent().getTime();

                    System.out.println(timeRes/(3600*1000));
                    System.out.println(timeSent/(24*3600*1000));

                    if ( (timeSent/(24*3600*1000)) >= 3 || (timeRes/(3600*1000)) <= 3 ){

                        rezervacijaService.eFriend(new InviteDTO(i),user);

                    }else{
                        inviteDTOS.add(new InviteDTO(i));
                    }

                }

            }
        }
        return inviteDTOS;
    }


}
