package com.service;

import com.model.user.Friend;
import com.repository.FriendRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class FriendService {

    @Autowired
    private FriendRepository friendRepository;

    public Optional<Friend> findById(Long userId){
        return this.friendRepository.findById(userId);

    }

    public Friend save(Friend friend){
        return this.friendRepository.save(friend);

    }


}
