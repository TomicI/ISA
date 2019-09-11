package com.model.aviokompanija;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Set;

@Entity
public class DodatnaUslugaAviokompanija {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column
	private String naziv;
	
	@Column
	private String opis;
	
	@Column
	private Double cena;

	@JsonIgnore
	@OneToMany(mappedBy = "dodatnaUslugaAviokompanija")
	private Set<Sediste> sedista;
	
	@ManyToOne
	private Aviokompanija aviokompanija;
	
	public DodatnaUslugaAviokompanija() {
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

	public String getOpis() {
		return opis;
	}

	public void setOpis(String opis) {
		this.opis = opis;
	}

	public Double getCena() {
		return cena;
	}

	public void setCena(Double cena) {
		this.cena = cena;
	}


	public Aviokompanija getAviokompanija() {
		return aviokompanija;
	}

	public void setAviokompanija(Aviokompanija aviokompanija) {
		this.aviokompanija = aviokompanija;
	}

	public Set<Sediste> getSedista() {
		return sedista;
	}

	public void setSedista(Set<Sediste> sedista) {
		this.sedista = sedista;
	}
}
