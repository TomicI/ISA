package com.service;

import com.model.Invite;
import com.repository.InviteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class InviteService {

    @Autowired
    private InviteRepository inviteRepository;

    public List<Invite> getAll() {
        return inviteRepository.findAll();
    }

    public Invite save(Invite invite){
       return this.inviteRepository.save(invite);
    }
    public Optional<Invite> findByID(Long id){
        return this.inviteRepository.findById(id);
    }

    public void delete(Invite invite){
        this.inviteRepository.delete(invite);
    }
}
