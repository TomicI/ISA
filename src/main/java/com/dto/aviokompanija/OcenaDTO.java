package com.dto.aviokompanija;

import com.dto.FilijalaDTO;
import com.dto.RezervacijaDTO;
import com.dto.UserDTO;
import com.dto.VoziloDTO;
import com.model.Filijala;
import com.model.Rezervacija;
import com.model.Vozilo;
import com.model.aviokompanija.Aviokompanija;
import com.model.aviokompanija.Let;
import com.model.aviokompanija.Ocena;
import com.model.user.User;

public class OcenaDTO {

    private Long id;
    private int ocena;
    private AviokompanijaDTO aviokompanijaDTO;
    private LetDTO letDTO;
    private UserDTO userDTO;
    private RezervacijaDTO rezervacijaDTO;
    private FilijalaDTO filijalaDTO;
    private VoziloDTO voziloDTO;

    public OcenaDTO(Ocena ocena){
        this(ocena.getId(),ocena.getOcena(),ocena.getAviokompanija(),ocena.getLet(),ocena.getUser(),ocena.getRezervacija(),ocena.getFilijala(),ocena.getVozilo());
    }

    public OcenaDTO(Long id, int ocena, Aviokompanija aviokompanija, Let let, User user, Rezervacija rezervacija, Filijala filijala, Vozilo vozilo) {
        this.id = id;
        this.ocena = ocena;
        this.aviokompanijaDTO = new AviokompanijaDTO(aviokompanija);
        this.letDTO =new LetDTO(let);
        this.userDTO = new UserDTO(user);
        this.rezervacijaDTO = new RezervacijaDTO(rezervacija);
        this.filijalaDTO = new FilijalaDTO(filijala);
        this.voziloDTO = new VoziloDTO(vozilo);
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

    public AviokompanijaDTO getAviokompanijaDTO() {
        return aviokompanijaDTO;
    }

    public void setAviokompanijaDTO(AviokompanijaDTO aviokompanijaDTO) {
        this.aviokompanijaDTO = aviokompanijaDTO;
    }

    public LetDTO getLetDTO() {
        return letDTO;
    }

    public void setLetDTO(LetDTO letDTO) {
        this.letDTO = letDTO;
    }

    public UserDTO getUserDTO() {
        return userDTO;
    }

    public void setUserDTO(UserDTO userDTO) {
        this.userDTO = userDTO;
    }

    public RezervacijaDTO getRezervacijaDTO() {
        return rezervacijaDTO;
    }

    public void setRezervacijaDTO(RezervacijaDTO rezervacijaDTO) {
        this.rezervacijaDTO = rezervacijaDTO;
    }

    public FilijalaDTO getFilijalaDTO() {
        return filijalaDTO;
    }

    public void setFilijalaDTO(FilijalaDTO filijalaDTO) {
        this.filijalaDTO = filijalaDTO;
    }

    public VoziloDTO getVoziloDTO() {
        return voziloDTO;
    }

    public void setVoziloDTO(VoziloDTO voziloDTO) {
        this.voziloDTO = voziloDTO;
    }
}
