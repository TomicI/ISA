package com.model.aviokompanija;

import com.model.Filijala;
import com.model.Rezervacija;
import com.model.Vozilo;
import com.model.user.User;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Ocena {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column
	private int ocena;

	@Column
	private Date ocDate;
	
	@ManyToOne
	private Aviokompanija aviokompanija;
	
	@ManyToOne
	private Let let;

	@ManyToOne
	private Vozilo vozilo;

	@ManyToOne
	private User user;

	@ManyToOne
	private Filijala filijala;

	@ManyToOne
	private Rezervacija rezervacija;

	public Ocena() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getOcena() {
		return ocena;
	}

	public void setOcena(int ocena) {
		this.ocena = ocena;
	}

	public Aviokompanija getAviokompanija() {
		return aviokompanija;
	}

	public void setAviokompanija(Aviokompanija aviokompanija) {
		this.aviokompanija = aviokompanija;
	}

	public Let getLet() {
		return let;
	}

	public void setLet(Let let) {
		this.let = let;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Vozilo getVozilo() {
		return vozilo;
	}

	public void setVozilo(Vozilo vozilo) {
		this.vozilo = vozilo;
	}

	public Filijala getFilijala() {
		return filijala;
	}

	public void setFilijala(Filijala filijala) {
		this.filijala = filijala;
	}

	public Date getOcDate() {
		return ocDate;
	}

	public void setOcDate(Date ocDate) {
		this.ocDate = ocDate;
	}

	public Rezervacija getRezervacija() {
		return rezervacija;
	}

	public void setRezervacija(Rezervacija rezervacija) {
		this.rezervacija = rezervacija;
	}
}
