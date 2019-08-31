package com.dto.aviokompanija;

import com.model.aviokompanija.Aviokompanija;
import com.model.aviokompanija.KonfiguracijaLeta;

public class KonfiguracijaLetaDTO {

    private Long id;
    private String naziv;
    private AviokompanijaDTO aviokompanija;

    public KonfiguracijaLetaDTO(Long id, String naziv, Aviokompanija aviokompanija){
        this.setId(id);
        this.setNaziv(naziv);
        if(aviokompanija!=null)
            this.setAviokompanija(new AviokompanijaDTO(aviokompanija));
        else
            this.setAviokompanija(null);

    }

    public KonfiguracijaLetaDTO(KonfiguracijaLeta konfiguracija){
        this(konfiguracija.getId(),konfiguracija.getNaziv(),konfiguracija.getAviokompanija());
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public AviokompanijaDTO getAviokompanija() {
        return aviokompanija;
    }

    public void setAviokompanija(AviokompanijaDTO aviokompanija) {
        this.aviokompanija = aviokompanija;
    }
}
