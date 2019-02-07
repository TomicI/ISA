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

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "vozilo_id",nullable=false)
	private Vozilo vozilo;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "servis_id",nullable=false)
	private RentACar servis;
	
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


	/**
	 * @return the servis
	 */
	public RentACar getServis() {
		return servis;
	}

	/**
	 * @param servis the servis to set
	 */
	public void setServis(RentACar servis) {
		this.servis = servis;
	}

	/**
	 * @return the vozilo
	 */
	public Vozilo getVozilo() {
		return vozilo;
	}

	/**
	 * @param vozilo the vozilo to set
	 */
	public void setVozilo(Vozilo vozilo) {
		this.vozilo = vozilo;
	}

	

	
	
}
