package com.model.aviokompanija;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
public class Segment {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column
	private int duzina;

	@Column
	private int sirina;

	@Column
	private int redniBroj;

	@ManyToOne
	private KonfiguracijaLeta konfiguracija;

	@ManyToOne
	private KategorijaSedista kategorija;
	
	@JsonIgnore
	@OneToMany(mappedBy = "segment")
	private List<Sediste> sedista;

	public Segment() {
		super();
		sedista = new ArrayList<>();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getDuzina() {
		return duzina;
	}

	public void setDuzina(int duzina) {
		this.duzina = duzina;
	}

	public int getSirina() {
		return sirina;
	}

	public void setSirina(int sirina) {
		this.sirina = sirina;
	}

	public KonfiguracijaLeta getKonfiguracija() {
		return konfiguracija;
	}

	public void setKonfiguracija(KonfiguracijaLeta konfiguracija) {
		this.konfiguracija = konfiguracija;
	}

	public KategorijaSedista getKategorija() {
		return kategorija;
	}

	public void setKategorija(KategorijaSedista kategorija) {
		this.kategorija = kategorija;
	}

	public List<Sediste> getSedista() {
		return sedista;
	}

	public void setSedista(List<Sediste> sedista) {
		this.sedista = sedista;
	}

	public int getRedniBroj() {
		return redniBroj;
	}

	public void setRedniBroj(int redniBroj) {
		this.redniBroj = redniBroj;
	}
}
