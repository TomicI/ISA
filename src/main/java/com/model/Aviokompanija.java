package com.model;

import java.util.ArrayList;

public class Aviokompanija {
	private String naziv;
	private String adresa;
	private String opis;
	private Double prosecnaOcena;
	private ArrayList<Let> destinacije;
	
	
	public Aviokompanija() {
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
	public ArrayList<Let> getDestinacije() {
		return destinacije;
	}
	public void setDestinacije(ArrayList<Let> destinacije) {
		this.destinacije = destinacije;
	}
	
	
	
}
