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

import com.model.user.User;


@Entity
public class Karta {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="karta_id")
	private Long id;
	
	
	@Column
	private Double prosecnaOcena;

	
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private Let let;
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private Set<Sediste> sediste=new HashSet<>();
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private Set<Prtljag> prtljag=new HashSet<>();
	
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private User user;
	
	@OneToMany(mappedBy = "karta", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<DodatneUslugeAvion> dodatneUsl = new HashSet<DodatneUslugeAvion>();
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private CenovnikAvio cena ;
	
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}



	public Karta() {
		super();
	}

	
	public Double getProsecnaOcena() {
		return prosecnaOcena;
	}

	public void setProsecnaOcena(Double prosecnaOcena) {
		this.prosecnaOcena = prosecnaOcena;
	}

	

	public Set<DodatneUslugeAvion> getDodatneUsl() {
		return dodatneUsl;
	}

	public void setDodatneUsl(Set<DodatneUslugeAvion> dodatneUsl) {
		this.dodatneUsl = dodatneUsl;
	}

	public CenovnikAvio getCena() {
		return cena;
	}

	public void setCena(CenovnikAvio cena) {
		this.cena = cena;
	}

	public Let getLet() {
		return let;
	}

	public void setLet(Let let) {
		this.let = let;
	}

	

	public Set<Sediste> getSediste() {
		return sediste;
	}

	public void setSediste(Set<Sediste> sediste) {
		this.sediste = sediste;
	}

	public Set<Prtljag> getPrtljag() {
		return prtljag;
	}

	public void setPrtljag(Set<Prtljag> prtljag) {
		this.prtljag = prtljag;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	
}
