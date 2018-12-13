package com.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class CenovnikRentACar {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="cenovnikRAC_id")
	private Long id;
	
	@Column
	private String odDatuma;
	@Column
	private String doDatuma;
	
	@Column
	private Double cena;
	
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private RentACarService cenovnik;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getOdDatuma() {
		return odDatuma;
	}

	public void setOdDatuma(String odDatuma) {
		this.odDatuma = odDatuma;
	}

	public String getDoDatuma() {
		return doDatuma;
	}

	public void setDoDatuma(String doDatuma) {
		this.doDatuma = doDatuma;
	}

	public Double getCena() {
		return cena;
	}

	public void setCena(Double cena) {
		this.cena = cena;
	}


	public CenovnikRentACar() {
		super();
	}

	public RentACarService getCenovnik() {
		return cenovnik;
	}

	public void setCenovnik(RentACarService cenovnik) {
		this.cenovnik = cenovnik;
	}
	
	
	
}
