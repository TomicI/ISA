package com.dto.aviokompanija;

import com.model.aviokompanija.Lokacija;

public class LokacijaDTO {

    private Long id;
    private double geoSirina;
    private double geoVisina;
    private String naziv;

    public LokacijaDTO(){

    }

    public LokacijaDTO(Lokacija lokacija){
        super();
        if(lokacija != null) {
            this.setGeoSirina(lokacija.getGeoSirina());
            this.setGeoVisina(lokacija.getGeoVisina());
            this.setId(lokacija.getId());
            this.setNaziv(lokacija.getNaziv());
        }
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public double getGeoSirina() {
        return geoSirina;
    }

    public void setGeoSirina(double geoSirina) {
        this.geoSirina = geoSirina;
    }

    public double getGeoVisina() {
        return geoVisina;
    }

    public void setGeoVisina(double geoVisina) {
        this.geoVisina = geoVisina;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }
}
