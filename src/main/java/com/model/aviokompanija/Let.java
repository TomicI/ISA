package com.model.aviokompanija;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Let {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column
	private Date vremePolaska;
	
	@Column
	private Date vremeDolaska;
	
	@Column
	private Double prosecnaOcena;
	
	@Column
	private Double duzinaPutovanja;
	
	@Column
	private String vremePutovanja;
	
	@Column
	private String opis;
	
	@Column
	private String presedanja;
	
	@Column
	private int brojPresedanja;
	
	@Column
	private VrstaLeta vrstaLeta;
	
	@ManyToOne
	private Aerodrom aerodrom;
	
	@ManyToOne
	private Lokacija destinacija;

	@ManyToOne
	private KonfiguracijaLeta konfiguracija;
	
	@JsonIgnore
	@OneToMany(mappedBy = "let")
	private Set<Sediste> sedista;
	
	@JsonIgnore
	@OneToMany(mappedBy = "let")
	private Set<Ocena> ocene;
	
	public Let() {
		super();
		sedista = new HashSet<>();
		ocene = new HashSet<>();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getVremePolaska() {
		return vremePolaska;
	}

	public void setVremePolaska(Date vremePolaska) {
		this.vremePolaska = vremePolaska;
	}

	public Date getVremeDolaska() {
		return vremeDolaska;
	}

	public void setVremeDolaska(Date vremeDolaska) {
		this.vremeDolaska = vremeDolaska;
	}

	public Double getProsecnaOcena() {
		return prosecnaOcena;
	}

	public void setProsecnaOcena(Double prosecnaOcena) {
		this.prosecnaOcena = prosecnaOcena;
	}

	public Double getDuzinaPutovanja() {
		return duzinaPutovanja;
	}

	public void setDuzinaPutovanja(Double duzinaPutovanja) {
		this.duzinaPutovanja = duzinaPutovanja;
	}

	public String getVremePutovanja() {
		return vremePutovanja;
	}

	public void setVremePutovanja(String vremePutovanja) {
		this.vremePutovanja = vremePutovanja;
	}

	public String getOpis() {
		return opis;
	}

	public void setOpis(String opis) {
		this.opis = opis;
	}

	public String getPresedanja() {
		return presedanja;
	}

	public void setPresedanja(String presedanja) {
		this.presedanja = presedanja;
	}

	public int getBrojPresedanja() {
		return brojPresedanja;
	}

	public void setBrojPresedanja(int brojPresedanja) {
		this.brojPresedanja = brojPresedanja;
	}

	public VrstaLeta getVrstaLeta() {
		return vrstaLeta;
	}

	public void setVrstaLeta(VrstaLeta vrstaLeta) {
		this.vrstaLeta = vrstaLeta;
	}

	public Aerodrom getAerodrom() {
		return aerodrom;
	}

	public void setAerodrom(Aerodrom aerodrom) {
		this.aerodrom = aerodrom;
	}

	public Lokacija getDestinacija() {
		return destinacija;
	}

	public void setDestinacija(Lokacija destinacija) {
		this.destinacija = destinacija;
	}

	public Set<Sediste> getSedista() {
		return sedista;
	}

	public void setSedista(Set<Sediste> sedista) {
		this.sedista = sedista;
	}

	public Set<Ocena> getOcene() {
		return ocene;
	}

	public void setOcene(Set<Ocena> ocene) {
		this.ocene = ocene;
	}


	public KonfiguracijaLeta getKonfiguracija() {
		return konfiguracija;
	}

	public void setKonfiguracija(KonfiguracijaLeta konfiguracija) {
		this.konfiguracija = konfiguracija;
	}
}
