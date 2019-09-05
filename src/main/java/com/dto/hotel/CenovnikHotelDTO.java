package com.dto.hotel;

import com.model.hotel.CenovnikHotel;

public class CenovnikHotelDTO {

    private Long id;
    private String odDatuma;
    private String doDatuma;
    private Double cena;

     public CenovnikHotelDTO() {

    }

    public CenovnikHotelDTO(CenovnikHotel ch) {
    		this(ch.getId(),ch.getOdDatuma(),ch.getDoDatuma(),ch.getCena());
    	}

    	public CenovnikHotelDTO(Long id, String odDatuma, String doDatuma, Double cena) {
    		super();
    		this.id = id;
            this.odDatuma=odDatuma;
    		this.doDatuma=doDatuma;
            this.cena=cena;
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

    public Double getCena() {
        return cena;
    }

    public void setCena(Double cena) {
        this.cena = cena;
    }

}
