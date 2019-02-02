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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Vozilo {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(nullable = false,unique=true)
	private String naziv;
	@Column(nullable = false)
	private String marka;
	@Column(nullable = false)
	private String model;
	@Column(nullable = false)
	private Integer brojSedista;
	@Column(nullable = false)
	private Integer brojVrata;
	@Column(nullable = false)
	private Double brojTorbi;
	@Column(nullable = false)
	private Gorivo gorivo;
	@Column(nullable = false)
	private Menjac menjac;
	@Column
	private Boolean klima;
	@Column
	private Integer rezervoar;
	@Column
	private Double potrosnja;
	@Column
	private String dodatniopis;
	@Column
	private Double prosecnaOcena;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name = "filijala_id",nullable=false)
	private Filijala vozilo;
	
	@OneToMany(mappedBy = "cenovnik",fetch = FetchType.LAZY)
	private Set<CenovnikRentACar> cenovnik = new HashSet<CenovnikRentACar>();
	
	
	public Vozilo() {
		super();
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

	public String getMarka() {
		return marka;
	}

	public void setMarka(String marka) {
		this.marka = marka;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}


	public Integer getBrojSedista() {
		return brojSedista;
	}

	public void setBrojSedista(Integer brojSedista) {
		this.brojSedista = brojSedista;
	}

	public Integer getBrojVrata() {
		return brojVrata;
	}

	public void setBrojVrata(Integer brojVrata) {
		this.brojVrata = brojVrata;
	}

	public Double getBrojTorbi() {
		return brojTorbi;
	}

	public void setBrojTorbi(Double brojTorbi) {
		this.brojTorbi = brojTorbi;
	}

	public Menjac getMenjac() {
		return menjac;
	}

	public void setMenjac(Menjac menjac) {
		this.menjac = menjac;
	}

	public Boolean getKlima() {
		return klima;
	}

	public void setKlima(Boolean klima) {
		this.klima = klima;
	}

	public Double getProsecnaOcena() {
		return prosecnaOcena;
	}

	public void setProsecnaOcena(Double prosecnaOcena) {
		this.prosecnaOcena = prosecnaOcena;
	}

	public Filijala getVozilo() {
		return vozilo;
	}

	public void setVozilo(Filijala vozilo) {
		this.vozilo = vozilo;
	}

	public Set<CenovnikRentACar> getCenovnik() {
		return cenovnik;
	}

	public void setCenovnik(Set<CenovnikRentACar> cenovnik) {
		this.cenovnik = cenovnik;
	}

	public Gorivo getGorivo() {
		return gorivo;
	}

	public void setGorivo(Gorivo gorivo) {
		this.gorivo = gorivo;
	}

	public Integer getRezervoar() {
		return rezervoar;
	}

	public void setRezervoar(Integer rezervoar) {
		this.rezervoar = rezervoar;
	}

	public Double getPotrosnja() {
		return potrosnja;
	}

	public void setPotrosnja(Double potrosnja) {
		this.potrosnja = potrosnja;
	}

	public String getDodatniopis() {
		return dodatniopis;
	}

	public void setDodatniopis(String dodatniopis) {
		this.dodatniopis = dodatniopis;
	}
	

	
	
	
	
}
