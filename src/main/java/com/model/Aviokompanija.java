package com.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
@Entity
public class Aviokompanija {
	@Column
	private String naziv;
	@Column
	private String adresa;
	@Column
	private String opis;
	@Column
	private Double prosecnaOcena;
	@OneToMany(mappedBy = "sediste")
	private Set<Let> vozila = new HashSet<Let>();
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="aviokom_id")
	private Long id;
	
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
	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Set<Let> getVozila() {
		return vozila;
	}

	public void setVozila(Set<Let> vozila) {
		this.vozila = vozila;
	}
	
	
	
}
