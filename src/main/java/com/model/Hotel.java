package com.model;

import java.util.ArrayList;
import java.util.Map;

public class Hotel {
	private String naziv;
	private String adresa;
	private String opis;
	private Double prosecnaOcena;
	private ArrayList<Soba> sobe;
	private Map<String, Double> cenovnik;
	private ArrayList<String> dodatneUsluge;
	
	
	public Hotel() {
		super();
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

	public ArrayList<String> getDodatneUsluge() {
		return dodatneUsluge;
	}
	public void setDodatneUsluge(ArrayList<String> dodatneUsluge) {
		this.dodatneUsluge = dodatneUsluge;
	}
	public ArrayList<Soba> getSobe() {
		return sobe;
	}
	public void setSobe(ArrayList<Soba> sobe) {
		this.sobe = sobe;
	}
	public Map<String, Double> getCenovnik() {
		return cenovnik;
	}
	public void setCenovnik(Map<String, Double> cenovnik) {
		this.cenovnik = cenovnik;
	}
	
}
