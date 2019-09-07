package com.dto.aviokompanija;

import com.model.aviokompanija.Lokacija;

public class LokacijaDTO {

    private Long id;
    private double geoSirina;
    private double geoVisina;
    private String adresa;
    private String grad;
    private String drzava;

    public LokacijaDTO(){

    }

    public LokacijaDTO(Lokacija lokacija){

        this.geoSirina = lokacija.getGeoSirina();
        this.geoVisina = lokacija.getGeoVisina();
        this.adresa = lokacija.getAdresa();
        this.grad = lokacija.getGrad();
        this.drzava = lokacija.getDrzava();

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

    public String getAdresa() {
        return adresa;
    }

    public void setAdresa(String adresa) {
        this.adresa = adresa;
    }

    public String getGrad() {
        return grad;
    }

    public void setGrad(String grad) {
        this.grad = grad;
    }

    public String getDrzava() {
        return drzava;
    }

    public void setDrzava(String drzava) {
        this.drzava = drzava;
    }
}
