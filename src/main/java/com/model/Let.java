package com.model;

import java.sql.Date;
import java.sql.Time;
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
	public Let() {
		super();
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="let_id")
	private Long id;
	@Column 
	private Time vremeP;
	@Column 
	private Time vremeS;
	@Column
	private Date datumP;
	@Column
	private Date datumS;
	@Column
	private Double prosecnaOcena;
	@Column 
	private Integer brojSedista;
	@Column
	private String vremePutovanja;
	@Column
	private Double duzinaPutovanja;
	@Column
	private Boolean imaPresedanje;

	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private Aviokompanija aviokompanija;
	
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private Aerodrom aerodromP;
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private Aerodrom aerodromS;
	
	@OneToMany(mappedBy = "sedista", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<Sediste> sedista=new HashSet<Sediste>();
	
	@OneToMany(mappedBy = "let", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<Karta> let=new HashSet<Karta>();

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Set<Sediste> getSedista() {
		return sedista;
	}

	public void setSedista(Set<Sediste> sedista) {
		this.sedista = sedista;
	}

	public Set<Karta> getLet() {
		return let;
	}

	public void setLet(Set<Karta> let) {
		this.let = let;
	}

	public Time getVremeP() {
		return vremeP;
	}

	public void setVremeP(Time vremeP) {
		this.vremeP = vremeP;
	}

	public Time getVremeS() {
		return vremeS;
	}

	public void setVremeS(Time vremeS) {
		this.vremeS = vremeS;
	}

	public Date getDatumP() {
		return datumP;
	}

	public void setDatumP(Date datumP) {
		this.datumP = datumP;
	}

	public Date getDatumS() {
		return datumS;
	}

	public void setDatumS(Date datumS) {
		this.datumS = datumS;
	}

	public Double getProsecnaOcena() {
		return prosecnaOcena;
	}

	public void setProsecnaOcena(Double prosecnaOcena) {
		this.prosecnaOcena = prosecnaOcena;
	}

	public Integer getBrojSedista() {
		return brojSedista;
	}

	public void setBrojSedista(Integer brojSedista) {
		this.brojSedista = brojSedista;
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

	public Aviokompanija getAviokompanija() {
		return aviokompanija;
	}

	public void setAviokompanija(Aviokompanija aviokompanija) {
		this.aviokompanija = aviokompanija;
	}

	public String getVremePutovanja() {
		return vremePutovanja;
	}

	public void setVremePutovanja(String vremePutovanja) {
		this.vremePutovanja = vremePutovanja;
	}

	public Double getDuzinaPutovanja() {
		return duzinaPutovanja;
	}

	public void setDuzinaPutovanja(Double duzinaPutovanja) {
		this.duzinaPutovanja = duzinaPutovanja;
	}

	public Boolean getImaPresedanje() {
		return imaPresedanje;
	}

	public void setImaPresedanje(Boolean imaPresedanje) {
		this.imaPresedanje = imaPresedanje;
	}

	
	
	

}
