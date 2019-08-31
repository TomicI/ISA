package com.dto.aviokompanija;

import com.model.aviokompanija.Aviokompanija;
import com.model.aviokompanija.KategorijaSedista;

public class KategorijaSedistaDTO {

    private Long id;
    private String naziv;
    private double cena;
    private AviokompanijaDTO aviokompanija;

    public KategorijaSedistaDTO(KategorijaSedista kategorijaSedista){
        this(kategorijaSedista.getId(),kategorijaSedista.getNaziv(),kategorijaSedista.getCena(),kategorijaSedista.getAviokompanija());
    }

    public KategorijaSedistaDTO(Long id, String naziv, double cena, Aviokompanija aviokompanija){
        this.setId(id);
        this.setNaziv(naziv);
        this.setCena(cena);
        if(aviokompanija!=null)
            this.setAviokompanija(new AviokompanijaDTO(aviokompanija));
        else
            this.setAviokompanija(null);
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

    public double getCena() {
        return cena;
    }

    public void setCena(double cena) {
        this.cena = cena;
    }

    public AviokompanijaDTO getAviokompanija() {
        return aviokompanija;
    }

    public void setAviokompanija(AviokompanijaDTO aviokompanija) {
        this.aviokompanija = aviokompanija;
    }
}
