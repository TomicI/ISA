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
public class Rezervacija {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="rez_id")
	private Long id;
	
	@Column
	private Date datumVremeP;
	@Column
	private Date datumVremeS;
	
	
	
	public Rezervacija() {
		super();
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Date getDatumVremeP() {
		return datumVremeP;
	}
	public void setDatumVremeP(Date datumVremeP) {
		this.datumVremeP = datumVremeP;
	}
	public Date getDatumVremeS() {
		return datumVremeS;
	}
	public void setDatumVremeS(Date datumVremeS) {
		this.datumVremeS = datumVremeS;
	}
	
	
}
