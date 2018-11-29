package com.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Sediste {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="sed_id")
	private Long id;
	@Column
	private Integer kolona;
	@Column
	private Integer red;
	@Column
	private Boolean zauzeto;
	
	public Sediste() {
		super();
	}
	public Integer getKolona() {
		return kolona;
	}
	public void setKolona(Integer kolona) {
		this.kolona = kolona;
	}
	public Integer getRed() {
		return red;
	}
	public void setRed(Integer red) {
		this.red = red;
	}
	public Boolean getZauzeto() {
		return zauzeto;
	}
	public void setZauzeto(Boolean zauzeto) {
		this.zauzeto = zauzeto;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	
}
