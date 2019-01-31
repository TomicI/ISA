package com.dto;

import com.model.DodatneUslugeAvion;

public class DodatneUslugeAvionDTO {

	private Long id;
	private String naziv;
	private String opis;
	private Double cena;
	
	
	
	public DodatneUslugeAvionDTO() {
	}
	
	public DodatneUslugeAvionDTO(DodatneUslugeAvion d) {
		this(d.getId(), d.getNazivUsluge(), d.getOpisUsluge(), d.getCenaUsluge());
	}
	
	public DodatneUslugeAvionDTO(Long id, String naziv, String opis, Double cena) {
		this.id = id;
		this.naziv = naziv;
		this.opis = opis;
		this.cena = cena;
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
	public String getOpis() {
		return opis;
	}
	public void setOpis(String opis) {
		this.opis = opis;
	}
	public Double getCena() {
		return cena;
	}
	public void setCena(Double cena) {
		this.cena = cena;
	}
}
