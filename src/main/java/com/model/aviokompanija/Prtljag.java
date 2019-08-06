package com.model.aviokompanija;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Prtljag {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column
	private Double duzina; 
	
	@Column
	private Double sirina;
	
	@Column
	private Double tezina;
	
	@Column
	private Double cena;
	
	@JsonIgnore
	@OneToMany(mappedBy = "prtljag")
	private Set<Sediste> sedista;
	
	@ManyToOne
	private Aviokompanija aviokompanija;
	
	public Prtljag() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Double getDuzina() {
		return duzina;
	}

	public void setDuzina(Double duzina) {
		this.duzina = duzina;
	}

	public Double getSirina() {
		return sirina;
	}

	public void setSirina(Double sirina) {
		this.sirina = sirina;
	}

	public Double getTezina() {
		return tezina;
	}

	public void setTezina(Double tezina) {
		this.tezina = tezina;
	}

	public Double getCena() {
		return cena;
	}

	public void setCena(Double cena) {
		this.cena = cena;
	}

	public Set<Sediste> getSedista() {
		return sedista;
	}

	public void setSedista(Set<Sediste> sedista) {
		this.sedista = sedista;
	}

	public Aviokompanija getAviokompanija() {
		return aviokompanija;
	}

	public void setAviokompanija(Aviokompanija aviokompanija) {
		this.aviokompanija = aviokompanija;
	}
	
}
