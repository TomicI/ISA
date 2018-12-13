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
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.JoinColumn;

import org.springframework.beans.factory.annotation.Autowired;
@Entity
public class Aviokompanija {
	@Column
	private String naziv;
	@Column
	private String adresa;
	@Column
	private String opis;
	@Column
	private Double prosecnaOcena;
	
	@OneToMany(mappedBy = "letovi",fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<Let> letovi = new HashSet<Let>();
	
	
	@ManyToMany
	@JoinTable(name="avioDest", joinColumns= { @JoinColumn(name="aviokom_id") }, 
	inverseJoinColumns= {@JoinColumn(name="dest_id")})
	private Set<Destinacija> avioDest = new HashSet<Destinacija>();
	
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="aviokom_id")
	private Long id;
	
	public Aviokompanija() {
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

	public Set<Let> getVozila() {
		return letovi;
	}

	public void setVozila(Set<Let> letovi) {
		this.letovi = letovi;
	}
	
	
	
}
