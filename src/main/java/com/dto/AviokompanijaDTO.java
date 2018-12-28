package com.dto;

import com.model.Aviokompanija;

public class AviokompanijaDTO {
	
	private Long id;
	private String naziv;
	private String adresa;
	private String opis;
	private Double prosecnaOcena;
	
	public AviokompanijaDTO() {
		
	}
	
	public AviokompanijaDTO(Aviokompanija a) {
		this(a.getId(), a.getNaziv(), a.getAdresa(), a.getOpis(), a.getProsecnaOcena());
	}
	
	public AviokompanijaDTO(Long id, String naziv, String adresa, String opis, Double prosecnaOcena) {
		this.id = id;
		this.naziv = naziv;
		this.adresa = adresa;
		this.opis = opis;
		this.prosecnaOcena = prosecnaOcena;
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
