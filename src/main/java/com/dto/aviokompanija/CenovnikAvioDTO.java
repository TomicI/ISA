package com.dto.aviokompanija;

import java.sql.Date;

public class CenovnikAvioDTO {
	private Long id;
	private Date odDatuma;
	private Date doDatuma;
	private Double cena;
	private Double popust;
	
	public CenovnikAvioDTO() {
	}
	


	
	public CenovnikAvioDTO(Long id, Date odDatuma, Date doDatuma, Double cena, Double popust) {
		this.id = id;
		this.odDatuma = odDatuma;
		this.doDatuma = doDatuma;
		this.cena = cena;
		this.popust = popust;
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
	public Double getPopust() {
		return popust;
	}
	public void setPopust(Double popust) {
		this.popust = popust;
	}
	
}
