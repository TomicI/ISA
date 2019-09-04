package com.dto;

import java.util.Date;
import com.model.RezervacijaRentACar;
import com.model.StatusRes;

public class RezervacijaRentACarDTO {

	private Long id;
	private Date datumRez;
	private Date datumPreuz;
	private Date datumVracanja;
	private Double cena;
	private Double popust;
	private Boolean otkazana;
	private Boolean naPopustu;
	private StatusRes status;
	private FilijalaDTO filijalaDTO;
	private FilijalaDTO filijalaDropDTO;
	private VoziloDTO voziloDTO;

	public RezervacijaRentACarDTO(RezervacijaRentACar r) {
		this.id = r.getId();
		this.datumRez = r.getDatumRez();
		this.datumPreuz = r.getDatumPreuz();
		this.datumVracanja = r.getDatumVracanja();
		this.cena = r.getCena();
		this.popust = r.getPopust();
		this.filijalaDTO = new FilijalaDTO(r.getRezervacija());
		this.filijalaDropDTO=new FilijalaDTO(r.getRezervacijaDrop());
		this.voziloDTO = new VoziloDTO(r.getVozilo());
		this.otkazana = r.getOtkazana();
		this.naPopustu = r.getNaPopustu();
		this.status = r.getStatus();
	}

	public RezervacijaRentACarDTO(Long id,
								  Date datumRez, Date datumPreuz, Date datumVracanja,
								  Double cena,Double popust, Boolean otkazana , Boolean naPopustu,
								  StatusRes status ,FilijalaDTO filijalaDTO,
								  FilijalaDTO filijalaDropDTO,VoziloDTO voziloDTO) {
		super();
		this.id = id;
		this.datumRez = datumRez;
		this.datumPreuz = datumPreuz;
		this.datumVracanja = datumVracanja;
		this.cena = cena;
		this.popust = popust;
		this.otkazana = otkazana;
		this.naPopustu = naPopustu;
		this.filijalaDTO = filijalaDTO;
		this.filijalaDropDTO = filijalaDropDTO;
		this.voziloDTO = voziloDTO;
		this.status = status;
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


	/**
	 * @return the otkazana
	 */
	public Boolean getOtkazana() {
		return otkazana;
	}

	/**
	 * @param otkazana the otkazana to set
	 */
	public void setOtkazana(Boolean otkazana) {
		this.otkazana = otkazana;
	}

	public StatusRes getStatus() {
		return status;
	}

	public void setStatus(StatusRes status) {
		this.status = status;
	}

	public FilijalaDTO getFilijalaDropDTO() {
		return filijalaDropDTO;
	}

	public void setFilijalaDropDTO(FilijalaDTO filijalaDropDTO) {
		this.filijalaDropDTO = filijalaDropDTO;
	}

	public Double getPopust() {
		return popust;
	}

	public void setPopust(Double popust) {
		this.popust = popust;
	}

	public Boolean getNaPopustu() {
		return naPopustu;
	}

	public void setNaPopustu(Boolean naPopustu) {
		this.naPopustu = naPopustu;
	}
}
