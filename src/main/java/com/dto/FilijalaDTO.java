package com.dto;

import com.dto.aviokompanija.LokacijaDTO;
import com.model.Filijala;

public class FilijalaDTO {
	
	private Long id;
	private String adresa;
	private Double prosecnaOcena;
	private RentACarDTO rentACarDTO;
	private LokacijaDTO lokacijaDTO;
	
	
	public FilijalaDTO(Long id, String adresa, Double prosecnaOcena, RentACarDTO rentACarDTO,LokacijaDTO lokacijaDTO) {
		super();
		this.id = id;
		this.adresa = adresa;
		this.prosecnaOcena = prosecnaOcena;
		this.rentACarDTO = rentACarDTO;
		this.lokacijaDTO = lokacijaDTO;
	}

	public FilijalaDTO(Filijala f) {
		
		this.id=f.getId();
		this.adresa = f.getAdresa();
		this.rentACarDTO = new RentACarDTO(f.getRentACar());
		this.lokacijaDTO = new LokacijaDTO(f.getLokacija());
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getAdresa() {
		return adresa;
	}
	public void setAdresa(String adresa) {
		this.adresa = adresa;
	}
	public Double getProsecnaOcena() {
		return prosecnaOcena;
	}
	public void setProsecnaOcena(Double prosecnaOcena) {
		this.prosecnaOcena = prosecnaOcena;
	}
	public RentACarDTO getRentACarDTO() {
		return rentACarDTO;
	}
	public void setRentACarDTO(RentACarDTO rentACarDTO) {
		this.rentACarDTO = rentACarDTO;
	}

	public LokacijaDTO getLokacijaDTO() {
		return lokacijaDTO;
	}

	public void setLokacijaDTO(LokacijaDTO lokacijaDTO) {
		this.lokacijaDTO = lokacijaDTO;
	}
}
