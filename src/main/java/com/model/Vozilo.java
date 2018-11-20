package com.model;

public class Vozilo {
	private String regOznaka;
	private Boolean zauzeto;
	private Double cena;
	
	public Vozilo() {
		super();
	}
	public String getRegOznaka() {
		return regOznaka;
	}
	public void setRegOznaka(String regOznaka) {
		this.regOznaka = regOznaka;
	}
	public Boolean getZauzeto() {
		return zauzeto;
	}
	public void setZauzeto(Boolean zauzeto) {
		this.zauzeto = zauzeto;
	}
	public Double getCena() {
		return cena;
	}
	public void setCena(Double cena) {
		this.cena = cena;
	}
	
	
}
