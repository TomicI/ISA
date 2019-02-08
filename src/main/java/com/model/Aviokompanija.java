package com.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.springframework.beans.factory.annotation.Autowired;
@Entity
public class Aviokompanija {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="aviokom_id")
	private Long id;
	
	@Column
	private String naziv;
	@Column
	private String adresa;
	@Column
	private String opis;
	@Column
	private Double prosecnaOcena;

	@OneToMany(mappedBy = "aviokompanija", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<VezaAA> veza = new HashSet<VezaAA>();
	
	
	
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

	public Set<VezaAA> getVeza() {
		return veza;
	}

	public void setVeza(Set<VezaAA> veza) {
		this.veza = veza;
	}

	
}
