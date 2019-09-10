package com.dto.aviokompanija;

import com.model.aviokompanija.Aviokompanija;
import com.model.aviokompanija.Lokacija;

public class AviokompanijaDTO {
	
	private Long id;
	private String naziv;
	private String adresa;
	private String opis;
	private Double prosecnaOcena;
	private LokacijaDTO lokacija;

	public AviokompanijaDTO() { }
	
	public AviokompanijaDTO(Aviokompanija a) {

		this.id=a.getId();
		this.naziv = a.getNaziv();
		this.adresa = a.getAdresa();
		this.opis = a.getOpis();
		this.prosecnaOcena = a.getProsecnaOcena() ;
		if (a.getLokacija()!=null)
			this.lokacija  = new LokacijaDTO(a.getLokacija());
		else
			this.lokacija  = null;
	}


	public AviokompanijaDTO(Long id, String naziv, String adresa, String opis, Double prosecnaOcena, LokacijaDTO lokacija) {
		this.id = id;
		this.naziv = naziv;
		this.adresa = adresa;
		this.opis = opis;
		this.prosecnaOcena = prosecnaOcena;
		this.lokacija = lokacija;
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

	public String getAdresa() {
		return adresa;
	}

	public void setAdresa(String adresa) {
		this.adresa = adresa;
	}

	public String getOpis() {
		return opis;
	}

	public void setOpis(String opis) {
		this.opis = opis;
	}

	public Double getProsecnaOcena() {
		return prosecnaOcena;
	}

	public void setProsecnaOcena(Double prosecnaOcena) {
		this.prosecnaOcena = prosecnaOcena;
	}

	public LokacijaDTO getLokacijaDTO() {
		return lokacija;
	}

	public void setLokacijaDTO(LokacijaDTO lokacijaDTO) {
		this.lokacija = lokacijaDTO;
	}
}
