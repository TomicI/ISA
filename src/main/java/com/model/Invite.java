package com.model;

import com.model.user.User;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Invite {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private User userSent;
    @ManyToOne
    private User userReceive;
    @Column
    private Date dateSent;
    @ManyToOne
    private Rezervacija reservation;
    @Column
    private boolean accepted;

    public Invite() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUserSent() {
        return userSent;
    }

    public void setUserSent(User userSent) {
        this.userSent = userSent;
    }

    public User getUserReceive() {
        return userReceive;
    }

    public void setUserReceive(User userReceive) {
        this.userReceive = userReceive;
    }

    public Date getDateSent() {
        return dateSent;
    }

    public void setDateSent(Date dateSent) {
        this.dateSent = dateSent;
    }

    public Rezervacija getReservation() {
        return reservation;
    }

    public void setReservation(Rezervacija reservation) {
        this.reservation = reservation;
    }

    public boolean isAccepted() {
        return accepted;
    }

    public void setAccepted(boolean accepted) {
        this.accepted = accepted;
    }
}
