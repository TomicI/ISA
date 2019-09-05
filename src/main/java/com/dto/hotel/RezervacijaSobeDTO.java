package com.dto.hotel;

import com.model.hotel.RezervacijaSobe;

public class RezervacijaSobeDTO {

    private Long id;
    private String odDatuma;
    private String doDatuma;
    private Boolean zauzeta;

    public RezervacijaSobeDTO() {

    }

    public RezervacijaSobeDTO(RezervacijaSobe rs) {
        this(rs.getId(),rs.getOdDatuma(),rs.getDoDatuma(),rs.getZauzeta());
    }

    public RezervacijaSobeDTO(Long id, String odDatuma, String doDatuma, Boolean zauzeta) {
        super();
        this.id = id;
        this.odDatuma=odDatuma;
        this.doDatuma=doDatuma;
        this.zauzeta=zauzeta;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOdDatuma() {
        return odDatuma;
    }

    public void setOdDatuma(String odDatuma) {
        this.odDatuma = odDatuma;
    }

    public String getDoDatuma() {
        return doDatuma;
    }

    public void setDoDatuma(String doDatuma) {
        this.doDatuma = doDatuma;
    }

    public Boolean getZauzeta() {
        return zauzeta;
    }

    public void setZauzeta(Boolean zauzeta) {
        this.zauzeta = zauzeta;
    }
}
