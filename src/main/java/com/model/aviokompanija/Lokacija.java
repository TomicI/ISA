package com.model.aviokompanija;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.model.Filijala;
import com.service.RentACarService;

import javax.persistence.*;
import java.util.List;
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

	@Column(unique = true)
	private String adresa;

	@Column
	private String grad;

	@Column
	private String drzava;
	
	@JsonIgnore
	@OneToMany(mappedBy = "lokacija", orphanRemoval=true)
	private Set<Aviokompanija> aviokompanije;

	@JsonIgnore
	@OneToMany(mappedBy = "lokacija", orphanRemoval=true)
	private Set<Aerodrom> aerodrom;
	@JsonIgnore
	@OneToMany(mappedBy = "destinacija", orphanRemoval=true)
	private Set<Let> letovi;

	@JsonIgnore
	@OneToMany(mappedBy = "lokacija", orphanRemoval=true)
	private List<Filijala> rentService;


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

	public String getAdresa() {
		return adresa;
	}

	public void setAdresa(String adresa) {
		this.adresa = adresa;
	}

	public String getGrad() {
		return grad;
	}

	public void setGrad(String grad) {
		this.grad = grad;
	}

	public String getDrzava() {
		return drzava;
	}

	public void setDrzava(String drzava) {
		this.drzava = drzava;
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

	public List<Filijala> getRentService() {
		return rentService;
	}

	public void setRentService(List<Filijala> rentService) {
		this.rentService = rentService;
	}
}
