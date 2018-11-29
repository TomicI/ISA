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
public class Let {
	@Column
	private String datum;
	@Column
	private Integer vreme;
	@Column
	private String polazak;
	@Column
	private String dolazak;
	@Column
	private Double cena;
	@OneToMany(mappedBy = "sediste")
	private Set<Sediste> vozila = new HashSet<Sediste>();
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="let_id")
	private Long id;
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
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Set<Sediste> getVozila() {
		return vozila;
	}
	public void setVozila(Set<Sediste> vozila) {
		this.vozila = vozila;
	}
	
	
	
}
