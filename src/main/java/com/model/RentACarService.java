package com.model;

import java.util.ArrayList;

public class RentACarService {
	private String naziv;
	private String adresa;
	private String opis;
	private Double prosecnaOcena;
	private ArrayList<Vozilo> vozila;
	private ArrayList<String> filijale;

	public RentACarService() {
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
	public ArrayList<String> getFilijale() {
		return filijale;
	}
	public void setFilijale(ArrayList<String> filijale) {
		this.filijale = filijale;
	}
	public ArrayList<Vozilo> getVozila() {
		return vozila;
	}
	public void setVozila(ArrayList<Vozilo> vozila) {
		this.vozila = vozila;
	}
	
	
}
