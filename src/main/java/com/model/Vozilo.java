package com.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Vozilo {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="voz_id")
	private Long id;
	
	@ManyToOne(cascade=CascadeType.ALL,fetch=FetchType.EAGER)
	private RentACarService vozilo;
	
	@Column
	private String regOznaka;
	@Column
	private Boolean zauzeto;
	@Column
	private Double cena;
	
	public Vozilo() {
		super();
	}
	public String getRegOznaka() {
		return regOznaka;
	}
	public void setRegOznaka(String regOznaka) {
		this.regOznaka = regOznaka;
	}
	public Boolean getZauzeto() {
		return zauzeto;
	}
	public void setZauzeto(Boolean zauzeto) {
		this.zauzeto = zauzeto;
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
	
	
}
