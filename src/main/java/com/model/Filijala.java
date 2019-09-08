package com.model;

import com.model.aviokompanija.Lokacija;
import com.model.aviokompanija.Ocena;
import org.hibernate.engine.profile.Fetch;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Filijala {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	private Lokacija lokacija;

	@OneToMany(mappedBy = "filijala",fetch = FetchType.LAZY)
	private List<Ocena> ocene ;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name = "rentacar_id",nullable=false)
	private RentACar rentACar;
	
	@OneToMany(mappedBy = "filijala",fetch = FetchType.LAZY)
	private List<Vozilo> vozila ;
	
	@OneToMany(mappedBy = "rezervacija",fetch = FetchType.LAZY)
	private List<RezervacijaRentACar> rezervacije;


	public Filijala() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public RentACar getRentACar() {
		return rentACar;
	}

	public void setRentACar(RentACar rentACar) {
		this.rentACar = rentACar;
	}

	public List<Ocena> getOcene() {
		return ocene;
	}

	public void setOcene(List<Ocena> ocene) {
		this.ocene = ocene;
	}

	public List<Vozilo> getVozila() {
		return vozila;
	}

	public void setVozila(List<Vozilo> vozila) {
		this.vozila = vozila;
	}

	public List<RezervacijaRentACar> getRezervacije() {
		return rezervacije;
	}

	public void setRezervacije(List<RezervacijaRentACar> rezervacije) {
		this.rezervacije = rezervacije;
	}

	public Lokacija getLokacija() {
		return lokacija;
	}

	public void setLokacija(Lokacija lokacija) {
		this.lokacija = lokacija;
	}
}
