package com.dto;

import com.model.Aerodrom;

public class AerodromDTO {

	private Long id;
	private String nazivAerodroma;
	
	public AerodromDTO() {
	}
	
	public AerodromDTO(Aerodrom a) {
		this(a.getId(), a.getNazivAerodroma());
	} 
	
	public AerodromDTO(Long id, String nazivAerodroma) {
		super();
		this.id = id;
		this.nazivAerodroma = nazivAerodroma;
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
	
	
}
