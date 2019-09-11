package com.model;

import javax.persistence.*;

@Entity
public class RezervacijaSobe {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@Column
	private String odDatuma;
	@Column
	private String doDatuma;
	@Column
	private Boolean zauzeta;

	@OneToOne
	private Rezervacija rezervacija;
	
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private Soba sobaRez;

	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private Hotel soba;

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

	public Hotel getSoba() {
		return soba;
	}

	public void setSoba(Hotel soba) {
		this.soba = soba;
	}
}
