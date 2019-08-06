package com.model.aviokompanija;

import javax.persistence.*;

@Entity
public class Sediste {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column
	private Integer kolona;
	
	@Column
	private Integer red;
	
	@Column
	private Boolean zauzeto;
	
	@ManyToOne
	private Karta karta;
	
	@ManyToOne
	private Putnik putnik;
	
	@ManyToOne
	private Prtljag prtljag;
	
	@ManyToOne
	private Segment segment;

	@ManyToOne
	private Let let;
	
	public Sediste() {
		super();
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

	public Karta getKarta() {
		return karta;
	}

	public void setKarta(Karta karta) {
		this.karta = karta;
	}

	public Putnik getPutnik() {
		return putnik;
	}

	public void setPutnik(Putnik putnik) {
		this.putnik = putnik;
	}

	public Prtljag getPrtljag() {
		return prtljag;
	}

	public void setPrtljag(Prtljag prtljag) {
		this.prtljag = prtljag;
	}

	public Segment getSegment() {
		return segment;
	}

	public void setSegment(Segment segment) {
		this.segment = segment;
	}

	public Let getLet() {
		return let;
	}

	public void setLet(Let let) {
		this.let = let;
	}
		
}
