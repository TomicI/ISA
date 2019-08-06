package com.dto.aviokompanija;

import com.model.aviokompanija.Aviokompanija;
import com.model.aviokompanija.Lokacija;

public class AviokompanijaDTO {
	
	private Long id;
	private String naziv;
	private String adresa;
	private String opis;
	private Double prosecnaOcena;
	private LokacijaDTO lokacijaDTO;

	public AviokompanijaDTO() { }
	
	public AviokompanijaDTO(Aviokompanija a) {

			this(a.getId(), a.getNaziv(), a.getAdresa(), a.getOpis(), a.getProsecnaOcena(), a.getLokacija());

	}
	
	public AviokompanijaDTO(Long id, String naziv, String adresa, String opis, Double prosecnaOcena, Lokacija lokacija) {
		this.setId(id);
		this.setNaziv(naziv);
		this.setAdresa(adresa);
		this.setOpis(opis);
		this.setProsecnaOcena(prosecnaOcena);
		this.setLokacijaDTO(new LokacijaDTO(lokacija));
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
		return lokacijaDTO;
	}

	public void setLokacijaDTO(LokacijaDTO lokacijaDTO) {
		this.lokacijaDTO = lokacijaDTO;
	}
}
