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
import javax.persistence.OneToMany;

@Entity
public class Destinacija {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="dest_id")
	private Long id;
	
	@Column
	private String nazivDestinacije;
	
	@ManyToMany(mappedBy = "avioDest",fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<Aviokompanija> avioDest = new HashSet<Aviokompanija>();
	
	@OneToMany(mappedBy = "letoviD",fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<Karta> letoviD = new HashSet<Karta>();
	
	@OneToMany(mappedBy = "letoviP",fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<Karta> letoviP = new HashSet<Karta>();

	public Destinacija() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNazivDestinacije() {
		return nazivDestinacije;
	}

	public void setNazivDestinacije(String nazivDestinacije) {
		this.nazivDestinacije = nazivDestinacije;
	}

	public Set<Karta> getLetoviD() {
		return letoviD;
	}

	public void setLetoviD(Set<Karta> letoviD) {
		this.letoviD = letoviD;
	}

	public Set<Karta> getLetoviP() {
		return letoviP;
	}

	public void setLetoviP(Set<Karta> letoviP) {
		this.letoviP = letoviP;
	}

	public Set<Aviokompanija> getAvioDest() {
		return avioDest;
	}

	public void setAvioDest(Set<Aviokompanija> avioDest) {
		this.avioDest = avioDest;
	}
	
	
}
