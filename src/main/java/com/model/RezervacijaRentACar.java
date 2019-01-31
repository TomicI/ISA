package com.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity
public class RezervacijaRentACar {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(nullable = false)
	private Date datumRez;
	@Column(nullable = false)
	private Date datumPreuz;
	@Column(nullable = false)
	private Date datumVracanja;
	@Column(nullable = false)
	private Double cena;
	
	@ManyToOne(cascade=CascadeType.ALL,fetch=FetchType.EAGER)
	@JoinColumn(name = "filijala_id",nullable=false)
	private Filijala rezervacija;
	
	@ManyToOne
	@JoinColumn(name = "vozilo_id",nullable=false)
	private Vozilo vozilo;

	public Filijala getRezervacija() {
		return rezervacija;
	}

	public void setRezervacija(Filijala rezervacija) {
		this.rezervacija = rezervacija;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getDatumRez() {
		return datumRez;
	}

	public void setDatumRez(Date datumRez) {
		this.datumRez = datumRez;
	}

	public Date getDatumPreuz() {
		return datumPreuz;
	}

	public void setDatumPreuz(Date datumPreuz) {
		this.datumPreuz = datumPreuz;
	}

	public Date getDatumVracanja() {
		return datumVracanja;
	}

	public void setDatumVracanja(Date datumVracanja) {
		this.datumVracanja = datumVracanja;
	}

	public Double getCena() {
		return cena;
	}

	public void setCena(Double cena) {
		this.cena = cena;
	}

	public Vozilo getVozilo() {
		return vozilo;
	}

	public void setVozilo(Vozilo vozilo) {
		this.vozilo = vozilo;
	}
	
	

}
