package com.model.aviokompanija;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.model.Rezervacija;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Karta {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column
	private double cena;
	
	@JsonIgnore
	@OneToMany(mappedBy = "karta")
	private Set<Sediste> sedista;
	
	@JsonIgnore
	@OneToMany(mappedBy = "karta")
	private Set<DodatnaUslugaAviokompanija> dodatneUsluge;

	@OneToOne
	private Rezervacija rezervacija;

	@ManyToOne
	private Let let;
	
	public Karta() {
		super();
		setSedista(new HashSet<>());
		setDodatneUsluge(new HashSet<>());
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

	public Set<Sediste> getSedista() {
		return sedista;
	}

	public void setSedista(Set<Sediste> sedista) {
		this.sedista = sedista;
	}

	public Set<DodatnaUslugaAviokompanija> getDodatneUsluge() {
		return dodatneUsluge;
	}

	public void setDodatneUsluge(Set<DodatnaUslugaAviokompanija> dodatneUsluge) {
		this.dodatneUsluge = dodatneUsluge;
	}

	public Rezervacija getRezervacija() {
		return rezervacija;
	}

	public void setRezervacija(Rezervacija rezervacija) {
		this.rezervacija = rezervacija;
	}

	public Let getLet() {
		return let;
	}

	public void setLet(Let let) {
		this.let = let;
	}
}
