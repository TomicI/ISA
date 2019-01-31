package com.dto;

import java.util.Date;
import com.model.RezervacijaRentACar;

public class RezervacijaRentACarDTO {
	
	private Long id;
	private Date datumRez;
	private Date datumPreuz;
	private Date datumVracanja;
	private Double cena;
	private FilijalaDTO filijalaDTO;
	private VoziloDTO voziloDTO;

	public RezervacijaRentACarDTO(RezervacijaRentACar r) {
		this.id = r.getId();
		this.datumRez = r.getDatumRez();
		this.datumPreuz = r.getDatumPreuz();
		this.datumVracanja = r.getDatumVracanja();
		this.cena = r.getCena();
		this.filijalaDTO = new FilijalaDTO(r.getRezervacija());
		this.voziloDTO = new VoziloDTO(r.getVozilo());
	}

	public RezervacijaRentACarDTO(Long id, Date datumRez, Date datumPreuz, Date datumVracanja, Double cena,
			FilijalaDTO filijalaDTO, VoziloDTO voziloDTO) {
		super();
		this.id = id;
		this.datumRez = datumRez;
		this.datumPreuz = datumPreuz;
		this.datumVracanja = datumVracanja;
		this.cena = cena;
		this.filijalaDTO = filijalaDTO;
		this.voziloDTO = voziloDTO;
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Date getDatumRez() {
		return datumRez;
	}
	public void setDatumRez(Date datumRez) {
		this.datumRez = datumRez;
	}
	public Date getDatumPreuz() {
		return datumPreuz;
	}
	public void setDatumPreuz(Date datumPreuz) {
		this.datumPreuz = datumPreuz;
	}
	public Date getDatumVracanja() {
		return datumVracanja;
	}
	public void setDatumVracanja(Date datumVracanja) {
		this.datumVracanja = datumVracanja;
	}
	public Double getCena() {
		return cena;
	}
	public void setCena(Double cena) {
		this.cena = cena;
	}
	public FilijalaDTO getFilijalaDTO() {
		return filijalaDTO;
	}
	public void setFilijalaDTO(FilijalaDTO filijalaDTO) {
		this.filijalaDTO = filijalaDTO;
	}
	public VoziloDTO getVoziloDTO() {
		return voziloDTO;
	}
	public void setVoziloDTO(VoziloDTO voziloDTO) {
		this.voziloDTO = voziloDTO;
	}
	
	
	
	
}
