package com.dto;

import java.util.Date;

import com.model.CenovnikRentACar;



public class CenovnikRentACarDTO {
	
	private Long id;	
	private Date odDatuma;
	private Date doDatuma;
	private Double cena;
	private Boolean slobodan;
	private VoziloDTO voziloDTO;
	private RentACarDTO rentACarDTO;
		
	
	public CenovnikRentACarDTO(Long id, Date odDatuma, Date doDatuma, Double cena, Boolean slobodan,
			VoziloDTO voziloDTO,RentACarDTO rentACarDTO) {
		super();
		this.id = id;
		this.odDatuma = odDatuma;
		this.doDatuma = doDatuma;
		this.cena = cena;
		this.slobodan = slobodan;
		this.voziloDTO = voziloDTO;
		this.rentACarDTO= rentACarDTO;
	}
	
	public CenovnikRentACarDTO(CenovnikRentACar c) {
		this.id = c.getId();
		this.odDatuma =c.getOdDatuma();
		this.doDatuma =c.getDoDatuma();
		this.cena =c.getCena();
		this.voziloDTO = new VoziloDTO(c.getVozilo());
		this.rentACarDTO = new RentACarDTO(c.getServis());
	
	}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public Date getOdDatuma() {
		return odDatuma;
	}


	public void setOdDatuma(Date odDatuma) {
		this.odDatuma = odDatuma;
	}


	public Date getDoDatuma() {
		return doDatuma;
	}


	public void setDoDatuma(Date doDatuma) {
		this.doDatuma = doDatuma;
	}


	public Double getCena() {
		return cena;
	}


	public void setCena(Double cena) {
		this.cena = cena;
	}


	public Boolean getSlobodan() {
		return slobodan;
	}


	public void setSlobodan(Boolean slobodan) {
		this.slobodan = slobodan;
	}


	public VoziloDTO getVoziloDTO() {
		return voziloDTO;
	}


	public void setVoziloDTO(VoziloDTO voziloDTO) {
		this.voziloDTO = voziloDTO;
	}

	/**
	 * @return the rentACarDTO
	 */
	public RentACarDTO getRentACarDTO() {
		return rentACarDTO;
	}

	/**
	 * @param rentACarDTO the rentACarDTO to set
	 */
	public void setRentACarDTO(RentACarDTO rentACarDTO) {
		this.rentACarDTO = rentACarDTO;
	}
	
	
	
	
	

}
