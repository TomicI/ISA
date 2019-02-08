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
	private Boolean otkazana;
	private StatusRes status;
	private FilijalaDTO filijalaDTO;
	private FilijalaDTO filijalaDropDTO;
	private VoziloDTO voziloDTO;
	private UserDTO userDTO;

	public RezervacijaRentACarDTO(RezervacijaRentACar r) {
		this.id = r.getId();
		this.datumRez = r.getDatumRez();
		this.datumPreuz = r.getDatumPreuz();
		this.datumVracanja = r.getDatumVracanja();
		this.cena = r.getCena();
		this.filijalaDTO = new FilijalaDTO(r.getRezervacija());
		this.filijalaDropDTO=new FilijalaDTO(r.getRezervacijaDrop());
		this.voziloDTO = new VoziloDTO(r.getVozilo());
		this.userDTO = new UserDTO(r.getUser());
		this.otkazana = r.getOtkazana();
		this.status = r.getStatus();
	}

	public RezervacijaRentACarDTO(Long id, Date datumRez, Date datumPreuz, Date datumVracanja, Double cena, Boolean otkazana ,
			StatusRes status ,FilijalaDTO filijalaDTO,FilijalaDTO filijalaDropDTO,VoziloDTO voziloDTO, UserDTO userDTO) {
		super();
		this.id = id;
		this.datumRez = datumRez;
		this.datumPreuz = datumPreuz;
		this.datumVracanja = datumVracanja;
		this.cena = cena;
		this.otkazana = otkazana;
		this.filijalaDTO = filijalaDTO;
		this.filijalaDropDTO = filijalaDropDTO;
		this.voziloDTO = voziloDTO;
		this.userDTO = userDTO;
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
	 * @return the userDTO
	 */
	public UserDTO getUserDTO() {
		return userDTO;
	}

	/**
	 * @param userDTO the userDTO to set
	 */
	public void setUserDTO(UserDTO userDTO) {
		this.userDTO = userDTO;
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

}
