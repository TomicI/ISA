package com.model.aviokompanija;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
public class KategorijaSedista {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column
	private String naziv;
	
	@Column
	private double cena;
	
	@ManyToOne
	private Aviokompanija aviokompanija;

	@OneToMany(mappedBy = "kategorija", fetch = FetchType.LAZY)
	private List<Segment> segmenti;

	public KategorijaSedista() {
		super();
		segmenti = new ArrayList<>();
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

	public double getCena() {
		return cena;
	}

	public void setCena(double cena) {
		this.cena = cena;
	}

	public Aviokompanija getAviokompanija() {
		return aviokompanija;
	}

	public void setAviokompanija(Aviokompanija aviokompanija) {
		this.aviokompanija = aviokompanija;
	}

	public List<Segment> getSegmenti() {
		return segmenti;
	}

	public void setSegmenti(List<Segment> segmenti) {
		this.segmenti = segmenti;
	}
}
