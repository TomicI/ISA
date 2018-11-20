package com.model;

import java.util.ArrayList;

public class Let {
	private String datum;
	private Integer vreme;
	private String polazak;
	private String dolazak;
	private Double cena;
	private ArrayList<Sediste> sedista;

	public Let() {
		super();
	}
	public String getDatum() {
		return datum;
	}
	public void setDatum(String datum) {
		this.datum = datum;
	}
	public Integer getVreme() {
		return vreme;
	}
	public void setVreme(Integer vreme) {
		this.vreme = vreme;
	}

	public ArrayList<Sediste> getSedista() {
		return sedista;
	}
	public void setSedista(ArrayList<Sediste> sedista) {
		this.sedista = sedista;
	}
	public String getPolazak() {
		return polazak;
	}
	public void setPolazak(String polazak) {
		this.polazak = polazak;
	}
	public String getDolazak() {
		return dolazak;
	}
	public void setDolazak(String dolazak) {
		this.dolazak = dolazak;
	}
	public Double getCena() {
		return cena;
	}
	public void setCena(Double cena) {
		this.cena = cena;
	}
	
	
}
