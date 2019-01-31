package com.dto;

import java.sql.Date;

import com.model.Rezervacija;

public class RezervacijaDTO {
	private Long id;
	private Date datumVremeP;
	private Date datumVremeS;
	
	public RezervacijaDTO() {
	}
	
	public RezervacijaDTO(Rezervacija r) {
		this(r.getId(), r.getDatumVremeP(), r.getDatumVremeS());
	}
	
	public RezervacijaDTO(Long id, Date datumVremeP, Date datumVremeS) {
		this.id = id;
		this.datumVremeP = datumVremeP;
		this.datumVremeS = datumVremeS;
	}



	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Date getDatumVremeP() {
		return datumVremeP;
	}
	public void setDatumVremeP(Date datumVremeP) {
		this.datumVremeP = datumVremeP;
	}
	public Date getDatumVremeS() {
		return datumVremeS;
	}
	public void setDatumVremeS(Date datumVremeS) {
		this.datumVremeS = datumVremeS;
	}
	
	
}
