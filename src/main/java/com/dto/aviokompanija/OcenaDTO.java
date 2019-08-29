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

    public OcenaDTO(){

    }

    public OcenaDTO(Long id, int ocena, AviokompanijaDTO aviokompanijaDTO, LetDTO letDTO, UserDTO userDTO, RezervacijaDTO rezervacijaDTO, FilijalaDTO filijalaDTO, VoziloDTO voziloDTO) {
        this.id = id;
        this.ocena = ocena;
        this.aviokompanijaDTO = aviokompanijaDTO;
        this.letDTO = letDTO;
        this.userDTO = userDTO;
        this.rezervacijaDTO = rezervacijaDTO;
        this.filijalaDTO = filijalaDTO;
        this.voziloDTO = voziloDTO;
    }

    public OcenaDTO(Ocena ocena){
        this.id = ocena.getId();
        this.ocena = ocena.getOcena();
        this.aviokompanijaDTO = new AviokompanijaDTO(ocena.getAviokompanija());
        this.letDTO = new LetDTO(ocena.getLet());
        this.userDTO = new UserDTO(ocena.getUser());
        this.rezervacijaDTO = new RezervacijaDTO(ocena.getRezervacija());
        this.filijalaDTO = new FilijalaDTO(ocena.getFilijala());
        this.voziloDTO = new VoziloDTO(ocena.getVozilo());


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
