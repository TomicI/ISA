package com.model.aviokompanija;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Lokacija {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column
	private double geoSirina;
	
	@Column
	private double geoVisina;

	@Column
	private String naziv;
	
	@JsonIgnore
	@OneToMany(mappedBy = "lokacija", orphanRemoval=true)
	private Set<Aviokompanija> aviokompanije;

	@JsonIgnore
	@OneToMany(mappedBy = "lokacija", orphanRemoval=true)
	private Set<Aerodrom> aerodrom;

	@OneToMany(mappedBy = "destinacija", orphanRemoval=true)
	private Set<Let> letovi;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public double getGeoSirina() {
		return geoSirina;
	}

	public void setGeoSirina(double geoSirina) {
		this.geoSirina = geoSirina;
	}

	public double getGeoVisina() {
		return geoVisina;
	}

	public void setGeoVisina(double geoVisina) {
		this.geoVisina = geoVisina;
	}

	public String getNaziv() {
		return naziv;
	}

	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}

	public Set<Aviokompanija> getAviokompanije() {
		return aviokompanije;
	}

	public void setAviokompanije(Set<Aviokompanija> aviokompanije) {
		this.aviokompanije = aviokompanije;
	}

	public Set<Aerodrom> getAerodrom() {
		return aerodrom;
	}

	public void setAerodrom(Set<Aerodrom> aerodrom) {
		this.aerodrom = aerodrom;
	}

	public Set<Let> getLetovi() {
		return letovi;
	}

	public void setLetovi(Set<Let> letovi) {
		this.letovi = letovi;
	}
}
