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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
@Entity
public class Let {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="let_id")
	private Long id;
	
	@Column
	private String datumP;
	@Column
	private String vremeP;
	@Column
	private String datumS;
	@Column
	private String vremeS;
	@Column
	private Double vremePutovanja;
	@Column
	private Double duzinaPutovanja;
	@Column
	private Integer brojPresedanja;
	@Column
	private Double cena;
	@Column
	private Double prosecnaOcena;
	
	@OneToMany(mappedBy = "sedista",fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<Sediste> sedista = new HashSet<Sediste>();
	
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private Aviokompanija letovi;

	@OneToMany(mappedBy = "karte",fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<Karta> karte = new HashSet<Karta>();
	
	public Let() {
		super();
	}
	
	
	public Double getCena() {
		return cena;
	}
	public void setCena(Double cena) {
		this.cena = cena;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Set<Sediste> getVozila() {
		return sedista;
	}
	public void setVozila(Set<Sediste> sedista) {
		this.sedista = sedista;
	}


	public String getDatumP() {
		return datumP;
	}


	public void setDatumP(String datumP) {
		this.datumP = datumP;
	}


	public String getVremeP() {
		return vremeP;
	}


	public void setVremeP(String vremeP) {
		this.vremeP = vremeP;
	}


	public String getDatumS() {
		return datumS;
	}


	public void setDatumS(String datumS) {
		this.datumS = datumS;
	}


	public String getVremeS() {
		return vremeS;
	}


	public void setVremeS(String vremeS) {
		this.vremeS = vremeS;
	}


	public Double getVremePutovanja() {
		return vremePutovanja;
	}


	public void setVremePutovanja(Double vremePutovanja) {
		this.vremePutovanja = vremePutovanja;
	}


	public Double getDuzinaPutovanja() {
		return duzinaPutovanja;
	}


	public void setDuzinaPutovanja(Double duzinaPutovanja) {
		this.duzinaPutovanja = duzinaPutovanja;
	}


	public Integer getBrojPresedanja() {
		return brojPresedanja;
	}


	public void setBrojPresedanja(Integer brojPresedanja) {
		this.brojPresedanja = brojPresedanja;
	}


	public Double getProsecnaOcena() {
		return prosecnaOcena;
	}


	public void setProsecnaOcena(Double prosecnaOcena) {
		this.prosecnaOcena = prosecnaOcena;
	}


	public Set<Sediste> getSedista() {
		return sedista;
	}


	public void setSedista(Set<Sediste> sedista) {
		this.sedista = sedista;
	}


	public Aviokompanija getLetovi() {
		return letovi;
	}


	public void setLetovi(Aviokompanija letovi) {
		this.letovi = letovi;
	}


	public Set<Karta> getKarte() {
		return karte;
	}


	public void setKarte(Set<Karta> karte) {
		this.karte = karte;
	}
	
	
	
}
