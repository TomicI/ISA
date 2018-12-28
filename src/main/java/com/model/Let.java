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
import javax.persistence.OneToMany;

@Entity
public class Let {
	public Let() {
		super();
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="let_id")
	private Long id;
	
	@OneToMany(mappedBy = "sedista", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<Sediste> sedista=new HashSet<Sediste>();
	
	@OneToMany(mappedBy = "let", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<Karta> let=new HashSet<Karta>();

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Set<Sediste> getSedista() {
		return sedista;
	}

	public void setSedista(Set<Sediste> sedista) {
		this.sedista = sedista;
	}

	public Set<Karta> getLet() {
		return let;
	}

	public void setLet(Set<Karta> let) {
		this.let = let;
	}

	
	
	

}
