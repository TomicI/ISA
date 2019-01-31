package com.dto;

import java.sql.Date;


import com.model.Karta;

public class KartaDTO {

	private Long id;
	private Date datumVremeP;
	private Date datumVremeS;
	private Double prosecnaOcena;
	public KartaDTO() {
	}
	
	public KartaDTO(Karta k) {
		this(k.getId(), k.getDatumVremeP(), k.getDatumVremeS(), k.getProsecnaOcena());
	}
	
	
	public KartaDTO(Long id, Date datumVremeP, Date datumVremeS, Double prosecnaOcena) {
		this.id = id;
		this.datumVremeP = datumVremeP;
		this.datumVremeS = datumVremeS;
		this.prosecnaOcena = prosecnaOcena;
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
	public Double getProsecnaOcena() {
		return prosecnaOcena;
	}
	public void setProsecnaOcena(Double prosecnaOcena) {
		this.prosecnaOcena = prosecnaOcena;
	}
	
	
	
}
