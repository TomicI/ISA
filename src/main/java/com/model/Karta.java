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
public class Karta {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="karta_id")
	private Long id;
	
	@Column
	private Integer popust;
	
	@Column           
	private Double cena;
	
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private Destinacija letoviP;
	
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private Destinacija letoviD;
	
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private Let karte;
	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}



	public Karta() {
		super();
	}

	public int getPopust() {
		return popust;
	}

	public void setPopust(int popust) {
		this.popust = popust;
	}

	public Double getCena() {
		return cena;
	}

	public void setCena(Double cena) {
		this.cena = cena;
	}

	public void setPopust(Integer popust) {
		this.popust = popust;
	}

	public Destinacija getLetoviP() {
		return letoviP;
	}

	public void setLetoviP(Destinacija letoviP) {
		this.letoviP = letoviP;
	}

	public Destinacija getLetoviD() {
		return letoviD;
	}

	public void setLetoviD(Destinacija letoviD) {
		this.letoviD = letoviD;
	}
	
	
}
