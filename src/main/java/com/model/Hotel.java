package com.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
@Entity
public class Hotel {
	
	@Column
	private String naziv;
	@Column
	private String adresa;
	@Column
	private String opis;
	@Column
	private Double prosecnaOcena;
	
	@OneToMany(mappedBy = "soba",fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<Soba> soba = new HashSet<Soba>();
	
	@OneToMany(mappedBy = "hotel",fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<CenovnikHotel> cenovnik = new HashSet<CenovnikHotel>();
	
	@OneToMany(mappedBy = "hotelDU",fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<DodatneUslugeHotel> dodatneUsluge = new HashSet<DodatneUslugeHotel>();
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="hot_id")
	private Long id;
	
	public Hotel() {
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
	public Set<Soba> getVozila() {
		return soba;
	}
	public void setVozila(Set<Soba> soba) {
		this.soba = soba;
	}
	
}
