package com.model;

import java.sql.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class CenovnikAvio {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="cenovnikA_id")
	private Long id;
	@Column
	private Date odDatuma;
	@Column
	private Date doDatuma;
	@Column
	private Double cena;
	@Column
	private Double popust;
	
	
	
	public CenovnikAvio() {
		super();
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
