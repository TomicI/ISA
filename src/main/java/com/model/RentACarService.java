package com.model;

import java.util.ArrayList;
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
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;


@Entity
public class RentACarService {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="rac_id")
	private Long id;
	
	@Column
	private String naziv;
	@Column
	private String adresa;
	@Column
	private String opis;
	@Column
	private Double prosecnaOcena;
	
	@OneToMany(mappedBy = "vozilo",fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<Vozilo> vozila = new HashSet<Vozilo>();
	
	@OneToMany(mappedBy = "cenovnik",fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<CenovnikRentACar> cenovnik = new HashSet<CenovnikRentACar>();
	
	@OneToMany(mappedBy = "filijala")
	private Set<RentACarService> filijale = new HashSet<RentACarService>();
	
	@ManyToOne( cascade = CascadeType.ALL)
	@JoinColumn(name="fRac_id")
	private RentACarService filijala;

	
	
	public RentACarService() {
		super();
	}
	public String getNaziv() {
		return naziv;
	}
	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}
	public String getAdresa() {
		return adresa;
	}
	public void setAdresa(String adresa) {
		this.adresa = adresa;
	}
	public String getOpis() {
		return opis;
	}
	public void setOpis(String opis) {
		this.opis = opis;
	}
	public Double getProsecnaOcena() {
		return prosecnaOcena;
	}
	public void setProsecnaOcena(Double prosecnaOcena) {
		this.prosecnaOcena = prosecnaOcena;
	}
	

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Set<Vozilo> getVozila() {
		return vozila;
	}
	public void setVozila(Set<Vozilo> vozila) {
		this.vozila = vozila;
	}
	public Set<CenovnikRentACar> getCenovnik() {
		return cenovnik;
	}
	public void setCenovnik(Set<CenovnikRentACar> cenovnik) {
		this.cenovnik = cenovnik;
	}
	
	
}
