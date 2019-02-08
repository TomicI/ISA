package com.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.model.user.User;

@Entity
public class Sediste {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="sed_id")
	private Long id;
	@Column
	private Integer kolona;
	@Column
	private Integer red;
	@Column
	private Integer segment;
	@Column
	private Boolean zauzeto;
	
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private User user;

	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private Let sedista;
	
	public Sediste() {
		super();
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
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Let getSedista() {
		return sedista;
	}
	public void setSedista(Let sedista) {
		this.sedista = sedista;
	}
	public Integer getSegment() {
		return segment;
	}
	public void setSegment(Integer segment) {
		this.segment = segment;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	
	
}
