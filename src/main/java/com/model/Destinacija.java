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
	
	@OneToMany(mappedBy = "destinacija",fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<Aerodrom> aerodoromi = new HashSet<Aerodrom>();
	

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

	public Set<Aerodrom> getAerodoromi() {
		return aerodoromi;
	}

	public void setAerodoromi(Set<Aerodrom> aerodoromi) {
		this.aerodoromi = aerodoromi;
	}

	
	
	
}
