package com.dto.hotel;

import com.model.hotel.DodatneUslugeHotel;

public class DodatneUslugeHotelDTO {

    private Long id;
    private String nazivUsluge;
    private String opisUsluge;
    private Double cenaUsluge;

    public DodatneUslugeHotelDTO() {

    }

    public DodatneUslugeHotelDTO(DodatneUslugeHotel ch) {
        this(ch.getId(),ch.getNazivUsluge(),ch.getOpisUsluge(),ch.getCenaUsluge());
    }

    public DodatneUslugeHotelDTO(Long id, String nazivUsluge, String opisUsluge, Double cenaUsluge) {
        super();
        this.id = id;
        this.nazivUsluge=nazivUsluge;
        this.opisUsluge=opisUsluge;
        this.cenaUsluge=cenaUsluge;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNazivUsluge() {
        return nazivUsluge;
    }

    public void setNazivUsluge(String nazivUsluge) {
        this.nazivUsluge = nazivUsluge;
    }

    public String getOpisUsluge() {
        return opisUsluge;
    }

    public void setOpisUsluge(String opisUsluge) {
        this.opisUsluge = opisUsluge;
    }

    public Double getCenaUsluge() {
        return cenaUsluge;
    }

    public void setCenaUsluge(Double cenaUsluge) {
        this.cenaUsluge = cenaUsluge;
    }
}
