package com.model.aviokompanija;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Aviokompanija {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column
	private String naziv;
	@Column
	private String adresa;
	@Column
	private String opis;
	@Column
	private Double prosecnaOcena;
	
	@ManyToOne
	private Lokacija lokacija;
	
	@JsonIgnore
	@OneToMany(mappedBy = "aviokompanija", orphanRemoval=true)
	private Set<DodatnaUslugaAviokompanija> dodatneUsluge;
	
	@JsonIgnore
	@OneToMany(mappedBy = "aviokompanija", orphanRemoval=true)
	private Set<Ocena> ocene;
	
	@JsonIgnore
	@OneToMany(mappedBy = "aviokompanija", orphanRemoval=true)
	private Set<Prtljag> prtljag;
	
	@JsonIgnore
	@OneToMany(mappedBy = "aviokompanija", orphanRemoval=true)
	private Set<KategorijaSedista> kategorijaSedista;
	
	@JsonIgnore
	@OneToMany(mappedBy = "aviokompanija", orphanRemoval=true)
	private Set<KonfiguracijaLeta> konfiguracijaLeta;

	@JsonIgnore
	@OneToMany(mappedBy = "aviokompanija", orphanRemoval=true)
	private Set<Aerodrom> aerodromi;
	
	public Aviokompanija() {
		super();
		dodatneUsluge = new HashSet<>();
		aerodromi = new HashSet<>();
		kategorijaSedista = new HashSet<>();
		ocene = new HashSet<>();
		prtljag = new HashSet<>();
		konfiguracijaLeta = new HashSet<>();
	}

	public Aviokompanija(Long id, String naziv, String opis) {
		this.id=id;
		this.naziv = naziv;
		this.opis = opis;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public Lokacija getLokacija() {
		return lokacija;
	}

	public void setLokacija(Lokacija lokacija) {
		this.lokacija = lokacija;
	}

	public Set<DodatnaUslugaAviokompanija> getDodatneUsluge() {
		return dodatneUsluge;
	}

	public void setDodatneUsluge(Set<DodatnaUslugaAviokompanija> dodatneUsluge) {
		this.dodatneUsluge = dodatneUsluge;
	}

	public Set<Ocena> getOcene() {
		return ocene;
	}

	public void setOcene(Set<Ocena> ocene) {
		this.ocene = ocene;
	}

	public Set<Prtljag> getPrtljag() {
		return prtljag;
	}

	public void setPrtljag(Set<Prtljag> prtljag) {
		this.prtljag = prtljag;
	}

	public Set<KategorijaSedista> getKategorijaSedista() {
		return kategorijaSedista;
	}

	public void setKategorijaSedista(Set<KategorijaSedista> kategorijaSedista) {
		this.kategorijaSedista = kategorijaSedista;
	}

	public Set<KonfiguracijaLeta> getKonfiguracijaLeta() {
		return konfiguracijaLeta;
	}

	public void setKonfiguracijaLeta(Set<KonfiguracijaLeta> konfiguracijaLeta) {
		this.konfiguracijaLeta = konfiguracijaLeta;
	}

	public Set<Aerodrom> getAerodromi() {
		return aerodromi;
	}

	public void setAerodromi(Set<Aerodrom> aerodromi) {
		this.aerodromi = aerodromi;
	}
	
	
}
