package com.model.aviokompanija;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
public class KonfiguracijaLeta {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column
	private String naziv;
	
	@ManyToOne
	private Aviokompanija aviokompanija;

	@JsonIgnore
	@OneToMany(mappedBy = "konfiguracija")
	private Set<Let> letovi;

	@JsonIgnore
	@OneToMany(mappedBy = "konfiguracija")
	private List<Segment> segmenti;

	public KonfiguracijaLeta() {
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

	public Aviokompanija getAviokompanija() {
		return aviokompanija;
	}

	public void setAviokompanija(Aviokompanija aviokompanija) {
		this.aviokompanija = aviokompanija;
	}

	public Set<Let> getLetovi() {
		return letovi;
	}

	public void setLetovi(Set<Let> letovi) {
		this.letovi = letovi;
	}

	public List<Segment> getSegmenti() {
		return segmenti;
	}

	public void setSegmenti(List<Segment> segmenti) {
		this.segmenti = segmenti;
	}
}
