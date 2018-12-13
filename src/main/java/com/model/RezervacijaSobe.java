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
public class RezervacijaSobe {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="rezervacija_id")
	private Long id;
	@Column
	private String odDatuma;
	@Column
	private String doDatuma;
	@Column
	private Boolean zauzeta;
	
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private Soba sobaRez;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getOdDatuma() {
		return odDatuma;
	}

	public void setOdDatuma(String odDatuma) {
		this.odDatuma = odDatuma;
	}

	public String getDoDatuma() {
		return doDatuma;
	}

	public void setDoDatuma(String doDatuma) {
		this.doDatuma = doDatuma;
	}

	public Boolean getZauzeta() {
		return zauzeta;
	}

	public void setZauzeta(Boolean zauzeta) {
		this.zauzeta = zauzeta;
	}

	public Soba getSobaRez() {
		return sobaRez;
	}

	public void setSobaRez(Soba sobaRez) {
		this.sobaRez = sobaRez;
	}
	
}
