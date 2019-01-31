package com.model;


import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;


@Entity
public class Karta {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="karta_id")
	private Long id;
	
	@Column
	private Date datumVremeP;
	@Column
	private Date datumVremeS;
	@Column
	private Double prosecnaOcena;

	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private Aerodrom aerodromP;
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private Aerodrom aerodromS;
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private Let let;
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private Aviokompanija aviokom;
	
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private Sediste sediste;
	
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private Prtljag prtljag;
	
	@OneToMany(mappedBy = "karta", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<DodatneUslugeAvion> dodatneUsl = new HashSet<DodatneUslugeAvion>();
	@OneToMany(mappedBy = "cenaKarte", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<CenovnikAvio> cena = new HashSet<CenovnikAvio>();
	
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}



	public Karta() {
		super();
	}

	public Date getDatumVremeP() {
		return datumVremeP;
	}

	public void setDatumVremeP(Date datumVremeP) {
		this.datumVremeP = datumVremeP;
	}

	public Date getDatumVremeS() {
		return datumVremeS;
	}

	public void setDatumVremeS(Date datumVremeS) {
		this.datumVremeS = datumVremeS;
	}

	public Double getProsecnaOcena() {
		return prosecnaOcena;
	}

	public void setProsecnaOcena(Double prosecnaOcena) {
		this.prosecnaOcena = prosecnaOcena;
	}

	public Aerodrom getAerodromP() {
		return aerodromP;
	}

	public void setAerodromP(Aerodrom aerodromP) {
		this.aerodromP = aerodromP;
	}

	public Aerodrom getAerodromS() {
		return aerodromS;
	}

	public void setAerodromS(Aerodrom aerodromS) {
		this.aerodromS = aerodromS;
	}

	public Set<DodatneUslugeAvion> getDodatneUsl() {
		return dodatneUsl;
	}

	public void setDodatneUsl(Set<DodatneUslugeAvion> dodatneUsl) {
		this.dodatneUsl = dodatneUsl;
	}

	public Set<CenovnikAvio> getCena() {
		return cena;
	}

	public void setCena(Set<CenovnikAvio> cena) {
		this.cena = cena;
	}

	public Let getLet() {
		return let;
	}

	public void setLet(Let let) {
		this.let = let;
	}

	public Aviokompanija getAviokom() {
		return aviokom;
	}

	public void setAviokom(Aviokompanija aviokom) {
		this.aviokom = aviokom;
	}

	public Sediste getSediste() {
		return sediste;
	}

	public void setSediste(Sediste sediste) {
		this.sediste = sediste;
	}

	public Prtljag getPrtljag() {
		return prtljag;
	}

	public void setPrtljag(Prtljag prtljag) {
		this.prtljag = prtljag;
	}
	
	
}
