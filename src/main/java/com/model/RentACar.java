package com.model;


import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;


@Entity
public class RentACar {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="rent_id")
	private Long id;	
	@Column(nullable = false,unique=true)
	private String naziv;
	@Column
	private String opis;
	//cascade = {CascadeType.PERSIST, CascadeType.REFRESH, CascadeType.MERGE})
	@OneToMany(mappedBy="rentACar",fetch = FetchType.LAZY)
	private List<Filijala> filijale ;

	@OneToMany(mappedBy = "servis",fetch = FetchType.LAZY)
	private List<CenovnikRentACar> cenovnici;
	

	public RentACar() {
		super();
	}
	
	public RentACar(Long id, String naziv, String opis, Double prosecnaOcena) {
		super();
		this.id = id;
		this.naziv = naziv;
		this.opis = opis;

	}


	public String getNaziv() {
		return naziv;
	}
	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}	
	public String getOpis() {
		return opis;
	}
	public void setOpis(String opis) {
		this.opis = opis;
	}

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}

	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		
		if (this == obj) {
			return true;
		}
		
		if (obj == null || getClass() !=obj.getClass()) {
			return false;
		}
		
		RentACar r = (RentACar) obj;
		if (r.id == null || id == null) {
			return false;
		}
		
		return Objects.equals(id,r.id);
	}

	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		return Objects.hashCode(id);
	}

	public List<Filijala> getFilijale() {
		return filijale;
	}

	public void setFilijale(List<Filijala> filijale) {
		this.filijale = filijale;
	}

	public List<CenovnikRentACar> getCenovnici() {
		return cenovnici;
	}

	public void setCenovnici(List<CenovnikRentACar> cenovnici) {
		this.cenovnici = cenovnici;
	}
}
