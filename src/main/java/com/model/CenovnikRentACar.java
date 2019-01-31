package com.model;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class CenovnikRentACar {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;	
	@Column(nullable = false)
	private Date odDatuma;
	@Column(nullable = false)
	private Date doDatuma;
	@Column(nullable = false)
	private Double cena;
	@Column(nullable = false)
	private Boolean slobodan;
	
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "vozilo_id",nullable=false)
	private Vozilo cenovnik;
	
	public CenovnikRentACar() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getOdDatuma() {
		return odDatuma;
	}

	public void setOdDatuma(Date odDatuma) {
		this.odDatuma = odDatuma;
	}

	public Date getDoDatuma() {
		return doDatuma;
	}

	public void setDoDatuma(Date doDatuma) {
		this.doDatuma = doDatuma;
	}

	public Double getCena() {
		return cena;
	}

	public void setCena(Double cena) {
		this.cena = cena;
	}

	public Boolean getSlobodan() {
		return slobodan;
	}

	public void setSlobodan(Boolean slobodan) {
		this.slobodan = slobodan;
	}

	public Vozilo getCenovnik() {
		return cenovnik;
	}

	public void setCenovnik(Vozilo cenovnik) {
		this.cenovnik = cenovnik;
	}

	
	
}
