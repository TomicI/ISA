package com.dto;

import com.model.Prtljag;

public class PrtljagDTO {
	private Long id;
	private Double duzina; 
	private Double sirina;
	private Double tezina;
	private Double cena;
	
	public PrtljagDTO() {
	}
	
	public PrtljagDTO(Prtljag p) {
		this(p.getId(), p.getDuzina(), p.getSirina(), p.getTezina(), p.getCena());
	}
	
	public PrtljagDTO(Long id, Double duzina, Double sirina, Double tezina, Double cena) {
		this.id = id;
		this.duzina = duzina;
		this.sirina = sirina;
		this.tezina = tezina;
		this.cena = cena;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Double getDuzina() {
		return duzina;
	}
	public void setDuzina(Double duzina) {
		this.duzina = duzina;
	}
	public Double getSirina() {
		return sirina;
	}
	public void setSirina(Double sirina) {
		this.sirina = sirina;
	}
	public Double getTezina() {
		return tezina;
	}
	public void setTezina(Double tezina) {
		this.tezina = tezina;
	}
	public Double getCena() {
		return cena;
	}
	public void setCena(Double cena) {
		this.cena = cena;
	}
	
	
}
