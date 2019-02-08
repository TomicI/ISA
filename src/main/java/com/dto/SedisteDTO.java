package com.dto;

import com.model.Sediste;
import com.model.user.User;

public class SedisteDTO {
	private Long id;
	private Integer kolona;
	private Integer red;
	private Boolean zauzeto;
	private Integer segment;
	private User user;
	
	public SedisteDTO() {
	}

	public SedisteDTO(Sediste s) {
		this(s.getId(), s.getKolona(), s.getRed(), s.getZauzeto(), s.getSegment());
	}
	
	public SedisteDTO(Long id, Integer kolona, Integer red, Boolean zauzeto, Integer segment) {
		super();
		this.id = id;
		this.kolona = kolona;
		this.red = red;
		this.zauzeto = zauzeto;
		this.segment=segment;
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

	public Integer getSegment() {
		return segment;
	}

	public void setSegment(Integer segment) {
		this.segment = segment;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	
	
}
