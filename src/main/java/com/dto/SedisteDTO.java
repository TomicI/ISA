package com.dto;

import com.model.Sediste;

public class SedisteDTO {
	private Long id;
	private Integer kolona;
	private Integer red;
	private Boolean zauzeto;
	
	public SedisteDTO() {
	}

	public SedisteDTO(Sediste s) {
		this(s.getId(), s.getKolona(), s.getRed(), s.getZauzeto());
	}
	
	public SedisteDTO(Long id, Integer kolona, Integer red, Boolean zauzeto) {
		super();
		this.id = id;
		this.kolona = kolona;
		this.red = red;
		this.zauzeto = zauzeto;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getKolona() {
		return kolona;
	}

	public void setKolona(Integer kolona) {
		this.kolona = kolona;
	}

	public Integer getRed() {
		return red;
	}

	public void setRed(Integer red) {
		this.red = red;
	}

	public Boolean getZauzeto() {
		return zauzeto;
	}

	public void setZauzeto(Boolean zauzeto) {
		this.zauzeto = zauzeto;
	}
	
	
	
}
