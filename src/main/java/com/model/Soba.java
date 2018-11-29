package com.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Soba {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="soba_id")
	private Long id;
	@Column
	private String oznakaSobe;
	@Column
	private Integer brojOsoba;
	@Column
	private Boolean zauzeta;
	@Column
	private Double cena;
	
	public Soba() {
		super();
	}
	public String getOznakaSobe() {
		return oznakaSobe;
	}
	public void setOznakaSobe(String oznakaSobe) {
		this.oznakaSobe = oznakaSobe;
	}
	public Integer getBrojOsoba() {
		return brojOsoba;
	}
	public void setBrojOsoba(Integer brojOsoba) {
		this.brojOsoba = brojOsoba;
	}
	public Boolean getZauzeta() {
		return zauzeta;
	}
	public void setZauzeta(Boolean zauzeta) {
		this.zauzeta = zauzeta;
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
