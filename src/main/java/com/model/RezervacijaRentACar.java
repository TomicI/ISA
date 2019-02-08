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

import com.model.user.User;

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

	@Column
	private Boolean otkazana;
	
	@Column
	private StatusRes status;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name = "filijala_id",nullable=false)
	private Filijala rezervacija;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name = "filijala_drop_id",nullable=false)
	private Filijala rezervacijaDrop;
	
	@ManyToOne
	@JoinColumn(name = "vozilo_id",nullable=false)
	private Vozilo vozilo;

	@ManyToOne
	@JoinColumn(name = "user_id",nullable=false)
	private User user;

	public RezervacijaRentACar(){
		super();
	}

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

	/**
	 * @return the user
	 */
	public User getUser() {
		return user;
	}

	/**
	 * @param user the user to set
	 */
	public void setUser(User user) {
		this.user = user;
	}

	/**
	 * @return the otkazana
	 */
	public Boolean getOtkazana() {
		return otkazana;
	}

	/**
	 * @param otkazana the otkazana to set
	 */
	public void setOtkazana(Boolean otkazana) {
		this.otkazana = otkazana;
	}

	public StatusRes getStatus() {
		return status;
	}

	public void setStatus(StatusRes status) {
		this.status = status;
	}

	public Filijala getRezervacijaDrop() {
		return rezervacijaDrop;
	}

	public void setRezervacijaDrop(Filijala rezervacijaDrop) {
		this.rezervacijaDrop = rezervacijaDrop;
	}
	
	

}
