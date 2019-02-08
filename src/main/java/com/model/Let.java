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
import javax.persistence.JoinTable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
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
	private Integer brojSegmenata;
	@Column 
	private Integer brojKolona;
	@Column 
	private Integer brojRedova;
	@Column
	private String vremePutovanja;
	@Column
	private String opis;
	@Column
	private Double duzinaPutovanja;

	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private Aviokompanija aviokompanija;
	
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private Aerodrom aerodromP;
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private Aerodrom aerodromS;
	
	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinTable(name = "presedanje",
    joinColumns = { @JoinColumn(name = "let_id") },
    inverseJoinColumns = { @JoinColumn(name = "aero_id") })
	private Set<Aerodrom> presedanje=new HashSet<Aerodrom>();
	
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

	public Set<Aerodrom> getPresedanje() {
		return presedanje;
	}

	public void setPresedanje(Set<Aerodrom> presedanje) {
		this.presedanje = presedanje;
	}

	public Integer getBrojSegmenata() {
		return brojSegmenata;
	}

	public void setBrojSegmenata(Integer brojSegmenata) {
		this.brojSegmenata = brojSegmenata;
	}

	public Integer getBrojKolona() {
		return brojKolona;
	}

	public void setBrojKolona(Integer brojKolona) {
		this.brojKolona = brojKolona;
	}

	public Integer getBrojRedova() {
		return brojRedova;
	}

	public void setBrojRedova(Integer brojRedova) {
		this.brojRedova = brojRedova;
	}

	public String getOpis() {
		return opis;
	}

	public void setOpis(String opis) {
		this.opis = opis;
	}

	
	
	

}
