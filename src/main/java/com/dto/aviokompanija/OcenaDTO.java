package com.dto.aviokompanija;

import com.dto.UserDTO;
import com.model.aviokompanija.Aviokompanija;
import com.model.aviokompanija.Let;
import com.model.aviokompanija.Ocena;
import com.model.user.User;

public class OcenaDTO {

    private Long id;
    private int ocena;
    private AviokompanijaDTO aviokompanija;
    private LetDTO let;
    private UserDTO user;

    public OcenaDTO(Ocena ocena){
        this(ocena.getId(),ocena.getOcena(),ocena.getAviokompanija(),ocena.getLet(),ocena.getUser());
    }

    public OcenaDTO(Long id, int ocena, Aviokompanija aviokompanija, Let let, User user){
        this.setId(id);
        this.setOcena(ocena);
        this.setAviokompanija(new AviokompanijaDTO(aviokompanija));
        this.setLet(new LetDTO(let));
        this.setUser(new UserDTO(user));
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getOcena() {
        return ocena;
    }

    public void setOcena(int ocena) {
        this.ocena = ocena;
    }

    public AviokompanijaDTO getAviokompanija() {
        return aviokompanija;
    }

    public void setAviokompanija(AviokompanijaDTO aviokompanija) {
        this.aviokompanija = aviokompanija;
    }

    public LetDTO getLet() {
        return let;
    }

    public void setLet(LetDTO let) {
        this.let = let;
    }

    public UserDTO getUser() {
        return user;
    }

    public void setUser(UserDTO user) {
        this.user = user;
    }
}
