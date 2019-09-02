package com.dto.aviokompanija;

import com.model.aviokompanija.Aerodrom;
import com.model.aviokompanija.Aviokompanija;
import com.model.aviokompanija.Lokacija;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class AerodromDTO {

	private Long id;
	private String naziv;
	private AviokompanijaDTO aviokompanija;
	private LokacijaDTO lokacija;
	
	public AerodromDTO(Aerodrom aerodrom) {
		this(aerodrom.getId(), aerodrom.getNaziv(),aerodrom.getAviokompanija(),aerodrom.getLokacija());
	} 
	
	public AerodromDTO(Long id, String naziv, Aviokompanija aviokompanija, Lokacija lokacija) {
		super();
		this.setId(id);
		this.setNaziv(naziv);
		if(aviokompanija!=null)
			this.setAviokompanija(new AviokompanijaDTO(aviokompanija));
		else
			this.setAviokompanija(null);

		if(lokacija!=null)
			this.setLokacija(new LokacijaDTO(lokacija));
		else
			this.setLokacija(null);
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

	public LokacijaDTO getLokacija() {
		return lokacija;
	}

	public void setLokacija(LokacijaDTO lokacija) {
		this.lokacija = lokacija;
	}

	public AviokompanijaDTO getAviokompanija() {
		return aviokompanija;
	}

	public void setAviokompanija(AviokompanijaDTO aviokompanija) {
		this.aviokompanija = aviokompanija;
	}
}
