package com.dto.hotel;

import com.model.hotel.Hotel;

public class HotelDTO {

    private Long id;
    private String naziv;
    private String adresa;
    private String opis;
    private Double prosecnaOcena;

    public HotelDTO() {

    }

    public HotelDTO(Hotel h) {
    		this(h.getId(), h.getNaziv(), h.getOpis(), h.getAdresa(), h.getProsecnaOcena());
    	}

    	public HotelDTO(Long id, String naziv, String adresa, String opis, Double prosecnaOcena) {
    		super();
    		this.id=id;
    		this.naziv = naziv;
            this.adresa=adresa;
    		this.opis=opis;
            this.prosecnaOcena=prosecnaOcena;
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

    public String getAdresa() {
        return adresa;
    }

    public void setAdresa(String adresa) {
        this.adresa = adresa;
    }

    public String getOpis() {
        return opis;
    }

    public void setOpis(String opis) {
        this.opis = opis;
    }

    public Double getProsecnaOcena() {
        return prosecnaOcena;
    }

    public void setProsecnaOcena(Double prosecnaOcena) {
        this.prosecnaOcena = prosecnaOcena;
    }

}

