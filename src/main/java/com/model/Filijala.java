package com.model;

import java.util.HashSet;
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
	
	@Column(nullable = false,unique=true)
	private String adresa;
	
	@Column
	private Double prosecnaOcena;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name = "rentacar_id",nullable=false)
	private RentACar filijala;
	
	@OneToMany(mappedBy = "vozilo",fetch = FetchType.LAZY)
	private Set<Vozilo> vozila = new HashSet<Vozilo>();
	
	@OneToMany(mappedBy = "rezervacija",fetch = FetchType.LAZY)
	private Set<RezervacijaRentACar> rezervacije = new HashSet<RezervacijaRentACar>();


	public Filijala() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getAdresa() {
		return adresa;
	}

	public void setAdresa(String adresa) {
		this.adresa = adresa;
	}

	public Double getProsecnaOcena() {
		return prosecnaOcena;
	}

	public void setProsecnaOcena(Double prosecnaOcena) {
		this.prosecnaOcena = prosecnaOcena;
	}

	

	public RentACar getFilijala() {
		return filijala;
	}

	public void setFilijala(RentACar filijala) {
		this.filijala = filijala;
	}

	public Set<Vozilo> getVozila() {
		return vozila;
	}

	public void setVozila(Set<Vozilo> vozila) {
		this.vozila = vozila;
	}

	/**
	 * @return the rezervacije
	 */
	public Set<RezervacijaRentACar> getRezervacije() {
		return rezervacije;
	}

	/**
	 * @param rezervacije the rezervacije to set
	 */
	public void setRezervacije(Set<RezervacijaRentACar> rezervacije) {
		this.rezervacije = rezervacije;
	}


	

}
