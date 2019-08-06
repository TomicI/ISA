package com.dto;

import com.model.Rezervacija;

import java.sql.Date;

public class RezervacijaDTO {
	private Long id;
	private Date datumVremeP;
	private Date datumVremeS;
	
	public RezervacijaDTO() {
	}
	
	public RezervacijaDTO(Rezervacija r) {
		this();
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
