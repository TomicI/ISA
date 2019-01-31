package com.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Prtljag {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="prt_id")
	private Long id;
	
	@Column
	private Double duzina; 
	@Column
	private Double sirina;
	@Column
	private Double tezina;
	@Column
	private Double cena;
	
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
	
	
	
	
	
}
