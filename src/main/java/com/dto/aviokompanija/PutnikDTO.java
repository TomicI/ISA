package com.dto.aviokompanija;

import com.dto.UserDTO;
import com.model.aviokompanija.Putnik;

public class PutnikDTO {

    private Long id;
    private String ime;
    private String prezime;
    private String brojPasosa;
    private UserDTO user;

    public PutnikDTO(Putnik putnik){
        this.setBrojPasosa(putnik.getBrojPasosa());
        this.setIme(putnik.getIme());
        this.setId(putnik.getId());
        this.setPrezime(putnik.getPrezime());
        this.setUser(new UserDTO(putnik.getUser()));

    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public String getPrezime() {
        return prezime;
    }

    public void setPrezime(String prezime) {
        this.prezime = prezime;
    }

    public String getBrojPasosa() {
        return brojPasosa;
    }

    public void setBrojPasosa(String brojPasosa) {
        this.brojPasosa = brojPasosa;
    }

    public UserDTO getUser() {
        return user;
    }

    public void setUser(UserDTO user) {
        this.user = user;
    }

}
