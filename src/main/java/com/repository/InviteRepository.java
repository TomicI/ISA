package com.repository;

import com.model.Invite;
import com.model.Rezervacija;
import com.model.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface InviteRepository extends JpaRepository<Invite,Long> {
    Optional<List<Invite>> findByUserReceiveAndReservation(User userReceive, Rezervacija reservation);
    Optional<List<Invite>> findByUserReceive(User userReceive);
    Optional<List<Invite>> findByUserSent(User userSent);
    Optional<Invite> findByUserSentAndUserReceiveAndReservation(User userSent, User userReceive, Rezervacija reservation);
    List<Invite> findByUserReceiveIdAndAccepted(Long id,boolean accepted);
    boolean existsByReservationIdAndUserReceiveIdAndAccepted(Long res_id,Long user_id,boolean acc);

}
