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
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;


@Entity
public class Aerodrom {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="aero_id")
	private Long id;
	
	@Column
	private String nazivAerodroma;
	@ManyToMany(mappedBy = "presedanje", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<Let> presedanje=new HashSet<Let>();
	
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private Destinacija destinacija;
	
	@OneToMany(mappedBy = "aerodrom", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<VezaAA> veza = new HashSet<VezaAA>();
	public Aerodrom() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNazivAerodroma() {
		return nazivAerodroma;
	}

	public void setNazivAerodroma(String nazivAerodroma) {
		this.nazivAerodroma = nazivAerodroma;
	}

	public Destinacija getDestinacija() {
		return destinacija;
	}

	public void setDestinacija(Destinacija destinacija) {
		this.destinacija = destinacija;
	}
	
	
}
