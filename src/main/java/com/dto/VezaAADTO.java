package com.dto;

import com.model.Aerodrom;
import com.model.Aviokompanija;
import com.model.VezaAA;

public class VezaAADTO {
	private Long id;
	private Aerodrom aerodrom;
	private Aviokompanija aviokompanija;
	
	public VezaAADTO() {
	}
	
	public VezaAADTO(VezaAA v) {
		this(v.getId(), v.getAerodrom(), v.getAviokompanija());
	}
	
	public VezaAADTO(Long id, Aerodrom aerodrom, Aviokompanija aviokompanija) {
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

	public Aerodrom getAerodrom() {
		return aerodrom;
	}

	public void setAerodrom(Aerodrom aerodrom) {
		this.aerodrom = aerodrom;
	}

	public Aviokompanija getAviokompanija() {
		return aviokompanija;
	}

	public void setAviokompanija(Aviokompanija aviokompanija) {
		this.aviokompanija = aviokompanija;
	}

	
	
	
	
}
