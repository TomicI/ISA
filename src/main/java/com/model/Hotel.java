package com.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
@Entity
public class Hotel {
	@Column
	private String naziv;
	@Column
	private String adresa;
	@Column
	private String opis;
	@Column
	private Double prosecnaOcena;
	@OneToMany(mappedBy = "soba")
	private Set<Soba> vozila = new HashSet<Soba>();
	private Map<String, Double> cenovnik;
	private ArrayList<String> dodatneUsluge;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="hot_id")
	private Long id;
	
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
	
	public Map<String, Double> getCenovnik() {
		return cenovnik;
	}
	public void setCenovnik(Map<String, Double> cenovnik) {
		this.cenovnik = cenovnik;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Set<Soba> getVozila() {
		return vozila;
	}
	public void setVozila(Set<Soba> vozila) {
		this.vozila = vozila;
	}
	
}
