package com.model;

import com.model.aviokompanija.Karta;
import com.model.aviokompanija.Ocena;
import com.model.user.User;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
public class Rezervacija {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column
	private Date datumVremeP;

	@Column
	private Date datumVremeS;

	@Column
	private double cena;


	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "karta_id")
	private Karta karta;

	@OneToOne
	private RezervacijaSobe rezervacijaSobe;

	@OneToOne
	private RezervacijaRentACar rezervacijaRentACar;

	@ManyToOne
	@JoinColumn(name = "user_id",nullable=false)
	private User user;

	@OneToMany
	private List<Invite> invites;

	public Rezervacija(){

	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public double getCena() {
		return cena;
	}

	public void setCena(double cena) {
		this.cena = cena;
	}

	public Karta getKarta() {
		return karta;
	}

	public void setKarta(Karta karta) {
		this.karta = karta;
	}

	public RezervacijaSobe getRezervacijaSobe() {
		return rezervacijaSobe;
	}

	public void setRezervacijaSobe(RezervacijaSobe rezervacijaSobe) {
		this.rezervacijaSobe = rezervacijaSobe;
	}

	public RezervacijaRentACar getRezervacijaRentACar() {
		return rezervacijaRentACar;
	}

	public void setRezervacijaRentACar(RezervacijaRentACar rezervacijaRentACar) {
		this.rezervacijaRentACar = rezervacijaRentACar;
	}

	public Date getDatumVremeP() {
		return datumVremeP;
	}

	public void setDatumVremeP(Date datumVremeP) {
		this.datumVremeP = datumVremeP;
	}

	public Date getDatumVremeS() {
		return datumVremeS;
	}

	public void setDatumVremeS(Date datumVremeS) {
		this.datumVremeS = datumVremeS;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<Invite> getInvites() {
		return invites;
	}

	public void setInvites(List<Invite> invites) {
		this.invites = invites;
	}
}
