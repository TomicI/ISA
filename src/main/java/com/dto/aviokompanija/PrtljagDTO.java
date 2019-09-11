package com.dto.aviokompanija;

import com.model.aviokompanija.Aviokompanija;
import com.model.aviokompanija.Prtljag;

public class PrtljagDTO {
	private Long id;
	private Double duzina; 
	private Double sirina;
	private Double tezina;
	private Double cena;
	private AviokompanijaDTO aviokompanija;
	
	public PrtljagDTO(Prtljag p) {
		this(p.getId(), p.getDuzina(), p.getSirina(), p.getTezina(), p.getCena(), p.getAviokompanija());
	}
	
	public PrtljagDTO(Long id, Double duzina, Double sirina, Double tezina, Double cena, Aviokompanija aviokompanija) {
		this.setId(id);
		this.setDuzina(duzina);
		this.setSirina(sirina);
		this.setTezina(tezina);
		this.setCena(cena);
		if(aviokompanija!=null)
			this.setAviokompanija(new AviokompanijaDTO(aviokompanija));
		else
			this.setAviokompanija(null);
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

	public AviokompanijaDTO getAviokompanija() {
		return aviokompanija;
	}

	public void setAviokompanija(AviokompanijaDTO aviokompanija) {
		this.aviokompanija = aviokompanija;
	}
}
