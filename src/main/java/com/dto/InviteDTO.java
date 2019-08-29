package com.dto;

import com.model.Invite;
import com.model.Rezervacija;

import java.util.Date;

public class InviteDTO {
    private Long id;


    public UserDTO userSent;

    public UserDTO userReceive;

    public Date dateSent;

    public RezervacijaDTO reservation;

    public InviteDTO(Long id, UserDTO userSent, UserDTO userReceive, Date dateSent, Rezervacija reservation) {
        this.id = id;
        this.userSent = userSent;
        this.userReceive = userReceive;
        this.dateSent = dateSent;
        if(reservation!= null)
            this.reservation = new RezervacijaDTO(reservation);
        else
            this.reservation=null;
    }

    public InviteDTO(Invite invite){
        this(invite.getId(), new UserDTO(invite.getUserSent()), new UserDTO(invite.getUserReceive()), invite.getDateSent(), invite.getReservation());
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UserDTO getUserSent() {
        return userSent;
    }

    public void setUserSent(UserDTO userSent) {
        this.userSent = userSent;
    }

    public UserDTO getUserReceive() {
        return userReceive;
    }

    public void setUserReceive(UserDTO userReceive) {
        this.userReceive = userReceive;
    }

    public Date getDateSent() {
        return dateSent;
    }

    public void setDateSent(Date dateSent) {
        this.dateSent = dateSent;
    }

    public RezervacijaDTO getReservation() {
        return reservation;
    }

    public void setReservation(RezervacijaDTO reservation) {
        this.reservation = reservation;
    }
}
