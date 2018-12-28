package com.dto;

import com.model.Destinacija;

public class DestinacijaDTO {

	private Long id;
	private String nazivDestinacije;
	
	public DestinacijaDTO() {
	}
	
	public DestinacijaDTO(Destinacija d) {
		this(d.getId(), d.getNazivDestinacije());
	}

	
	public DestinacijaDTO(Long id, String nazivDestinacije) {
		this.id = id;
		this.nazivDestinacije = nazivDestinacije;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNazivDestinacije() {
		return nazivDestinacije;
	}

	public void setNazivDestinacije(String nazivDestinacije) {
		this.nazivDestinacije = nazivDestinacije;
	}
	
	
}
