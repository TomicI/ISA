package com.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Soba {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="soba_id")
	private Long id;
	@Column
	private String tipSobe;
	@Column
	private Integer brojOsoba;
	@Column
	private Double prosecnaOcena;
	@Column
	private Integer sprat;
	@Column
	private Boolean terasa;
	
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private Hotel soba;
	
	@OneToMany(mappedBy = "sobaRez",fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<RezervacijaSobe> dostupnost = new HashSet<RezervacijaSobe>();
	
	public Soba() {
		super();
	}

	public Integer getBrojOsoba() {
		return brojOsoba;
	}
	public void setBrojOsoba(Integer brojOsoba) {
		this.brojOsoba = brojOsoba;
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Double getProsecnaOcena() {
		return prosecnaOcena;
	}
	public void setProsecnaOcena(Double prosecnaOcena) {
		this.prosecnaOcena = prosecnaOcena;
	}
	public String getTipSobe() {
		return tipSobe;
	}
	public void setTipSobe(String tipSobe) {
		this.tipSobe = tipSobe;
	}
	public Integer getSprat() {
		return sprat;
	}
	public void setSprat(Integer sprat) {
		this.sprat = sprat;
	}
	public Boolean getTerasa() {
		return terasa;
	}
	public void setTerasa(Boolean terasa) {
		this.terasa = terasa;
	}
	public Hotel getSoba() {
		return soba;
	}
	public void setSoba(Hotel soba) {
		this.soba = soba;
	}
	public Set<RezervacijaSobe> getDostupnost() {
		return dostupnost;
	}
	public void setDostupnost(Set<RezervacijaSobe> dostupnost) {
		this.dostupnost = dostupnost;
	} 
}
