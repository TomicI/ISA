package com.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class VezaAA {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="vezaAA_id")
	private Long id;
	
	
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private Aerodrom aerodrom;
	
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private Aviokompanija aviokompanija;

	public VezaAA() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Aerodrom getAerodrom() {
		return aerodrom;
	}

	public void setAerodrom(Aerodrom aerodrom) {
		this.aerodrom = aerodrom;
	}

	public Aviokompanija getAviokompanija() {
		return aviokompanija;
	}

	public void setAviokompanija(Aviokompanija aviokompanija) {
		this.aviokompanija = aviokompanija;
	}
	
	
}
