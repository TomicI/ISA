package com.model;

import com.model.aviokompanija.Karta;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Rezervacija {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column
	private Date datumPocetka;

	@Column
	private Date datumIsteka;

	@Column
	private double cena;

	@OneToOne
	private Karta karta;

	@OneToOne
	private RezervacijaSobe rezervacijaSobe;

	@OneToOne
	private RezervacijaRentACar rezervacijaRentACar;

	public Rezervacija(){

	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public double getCena() {
		return cena;
	}

	public void setCena(double cena) {
		this.cena = cena;
	}

	public Karta getKarta() {
		return karta;
	}

	public void setKarta(Karta karta) {
		this.karta = karta;
	}

	public RezervacijaSobe getRezervacijaSobe() {
		return rezervacijaSobe;
	}

	public void setRezervacijaSobe(RezervacijaSobe rezervacijaSobe) {
		this.rezervacijaSobe = rezervacijaSobe;
	}

	public RezervacijaRentACar getRezervacijaRentACar() {
		return rezervacijaRentACar;
	}

	public void setRezervacijaRentACar(RezervacijaRentACar rezervacijaRentACar) {
		this.rezervacijaRentACar = rezervacijaRentACar;
	}

	public Date getDatumPocetka() {
		return datumPocetka;
	}

	public void setDatumPocetka(Date datumPocetka) {
		this.datumPocetka = datumPocetka;
	}

	public Date getDatumIsteka() {
		return datumIsteka;
	}

	public void setDatumIsteka(Date datumIsteka) {
		this.datumIsteka = datumIsteka;
	}
}
