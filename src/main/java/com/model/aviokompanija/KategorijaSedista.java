package com.model.aviokompanija;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.HashSet;
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
	
	@JsonIgnore
	@OneToMany(mappedBy = "kategorija")
	private Set<Segment> segmenti;

	public KategorijaSedista() {
		super();
		segmenti = new HashSet<>();
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

	public Set<Segment> getSegmenti() {
		return segmenti;
	}

	public void setSegmenti(HashSet<Segment> segmenti) {
		this.segmenti = segmenti;
	}
}
