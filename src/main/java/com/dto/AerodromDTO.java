package com.dto;

import com.model.Aerodrom;

public class AerodromDTO {

	private Long id;
	private String nazivAerodroma;
	private Long destinacijaID;
	
	public AerodromDTO() {
	}
	
	public AerodromDTO(Aerodrom a) {
		this(a.getId(), a.getNazivAerodroma(), a.getDestinacija().getId());
	} 
	
	public AerodromDTO(Long id, String nazivAerodroma, Long destinacijaID) {
		super();
		this.id = id;
		this.nazivAerodroma = nazivAerodroma;
		this.destinacijaID=destinacijaID;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNazivAerodroma() {
		return nazivAerodroma;
	}

	public void setNazivAerodroma(String nazivAerodroma) {
		this.nazivAerodroma = nazivAerodroma;
	}

	public Long getDestinacijaID() {
		return destinacijaID;
	}

	public void setDestinacijaID(Long destinacijaID) {
		this.destinacijaID = destinacijaID;
	}
	
	
}
