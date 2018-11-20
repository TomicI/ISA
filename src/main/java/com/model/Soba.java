package com.model;

public class Soba {
	private String oznakaSobe;
	private Integer brojOsoba;
	private Boolean zauzeta;
	private Double cena;
	
	public Soba() {
		super();
	}
	public String getOznakaSobe() {
		return oznakaSobe;
	}
	public void setOznakaSobe(String oznakaSobe) {
		this.oznakaSobe = oznakaSobe;
	}
	public Integer getBrojOsoba() {
		return brojOsoba;
	}
	public void setBrojOsoba(Integer brojOsoba) {
		this.brojOsoba = brojOsoba;
	}
	public Boolean getZauzeta() {
		return zauzeta;
	}
	public void setZauzeta(Boolean zauzeta) {
		this.zauzeta = zauzeta;
	}
	public Double getCena() {
		return cena;
	}
	public void setCena(Double cena) {
		this.cena = cena;
	} 
}
