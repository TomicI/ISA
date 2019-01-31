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
public class DodatneUslugeAvion {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="dodatneUslA_id")
	private Long id;
	@Column
	private String nazivUsluge;
	@Column
	private String opisUsluge;
	@Column
	private Double cenaUsluge;
	
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private Karta karta;
	
	public DodatneUslugeAvion() {
		super();
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNazivUsluge() {
		return nazivUsluge;
	}
	public void setNazivUsluge(String nazivUsluge) {
		this.nazivUsluge = nazivUsluge;
	}
	public String getOpisUsluge() {
		return opisUsluge;
	}
	public void setOpisUsluge(String opisUsluge) {
		this.opisUsluge = opisUsluge;
	}
	public Double getCenaUsluge() {
		return cenaUsluge;
	}
	public void setCenaUsluge(Double cenaUsluge) {
		this.cenaUsluge = cenaUsluge;
	}
	
	
}
