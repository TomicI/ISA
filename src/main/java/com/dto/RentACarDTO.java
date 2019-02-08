package com.dto;

import com.model.RentACar;

public class RentACarDTO {
	
	private Long id;
	private String naziv;
	private String opis;
	
	public RentACarDTO() {	
		
	}
	
	public RentACarDTO(RentACar r) {
		this(r.getId(),r.getNaziv(),r.getOpis());
	}

	public RentACarDTO(Long id, String naziv,String opis) {
		super();
		this.id = id;
		this.naziv = naziv;
		this.opis=opis;
	}
	

	public Long getId() {
		return id;
	}

	public String getNaziv() {
		return naziv;
	}

	public String getOpis() {
		return opis;
	}

	public void setOpis(String opis) {
		this.opis = opis;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}


	
}
