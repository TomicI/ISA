package com.dto;

import java.util.ArrayList;

import com.model.Karta;
import com.model.Let;
import com.model.Sediste;

public class LetDTO {

	private Long id;
	private ArrayList<Sediste> sedista;
	private ArrayList<Karta> karte;
	
	
	
	public LetDTO() {
	}
	
	public LetDTO(Let l) {
		this(l.getId(), (ArrayList)l.getSedista(), (ArrayList)l.getLet());
	}
	
	public LetDTO(Long id, ArrayList<Sediste> sedista, ArrayList<Karta> karte) {
		super();
		this.id = id;
		this.sedista = sedista;
		this.karte = karte;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public ArrayList<Sediste> getSedista() {
		return sedista;
	}
	public void setSedista(ArrayList<Sediste> sedista) {
		this.sedista = sedista;
	}
	public ArrayList<Karta> getKarte() {
		return karte;
	}
	public void setKarte(ArrayList<Karta> karte) {
		this.karte = karte;
	}
	
}
