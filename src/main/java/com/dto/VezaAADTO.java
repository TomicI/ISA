package com.dto;

import com.model.Aerodrom;
import com.model.Aviokompanija;
import com.model.VezaAA;

public class VezaAADTO {
	private Long id;
	private Long aerodrom;
	private Long aviokompanija;
	
	public VezaAADTO() {
	}
	
	public VezaAADTO(VezaAA v) {
		this(v.getId(), v.getAerodrom().getId(), v.getAviokompanija().getId());
	}
	
	public VezaAADTO(Long id, Long aerodrom, Long aviokompanija) {
		this.id = id;
		this.aerodrom = aerodrom;
		this.aviokompanija = aviokompanija;
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}

	public Long getAerodrom() {
		return aerodrom;
	}

	public void setAerodrom(Long aerodrom) {
		this.aerodrom = aerodrom;
	}

	public Long getAviokompanija() {
		return aviokompanija;
	}

	public void setAviokompanija(Long aviokompanija) {
		this.aviokompanija = aviokompanija;
	}

	
	
	
	
}
