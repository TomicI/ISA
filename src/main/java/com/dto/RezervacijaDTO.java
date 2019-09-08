package com.dto;

import com.dto.aviokompanija.KartaDTO;
import com.model.Rezervacija;
import com.model.RezervacijaRentACar;
import com.model.RezervacijaSobe;
import com.model.aviokompanija.Karta;
import com.model.user.User;

import java.util.Date;


public class RezervacijaDTO {
	private Long id;
	private Date datumVremeP;
	private Date datumVremeS;
	private double cena;
	private KartaDTO kartaDTO;
	private RezervacijaRentACarDTO rezervacijaRentACarDTO;
	private UserDTO userDTO;
	
	public RezervacijaDTO() {
	}

	public RezervacijaDTO(Date datumVremeP, Date datumVremeS, double cena, KartaDTO kartaDTO, RezervacijaRentACarDTO rezervacijaRentACarDTO, UserDTO userDTO) {
		this.datumVremeP = datumVremeP;
		this.datumVremeS = datumVremeS;
		this.cena = cena;
		this.kartaDTO = kartaDTO;
		this.rezervacijaRentACarDTO = rezervacijaRentACarDTO;
		this.userDTO = userDTO;
	}

	public RezervacijaDTO(Rezervacija r) {
		this.id = r.getId();
		this.datumVremeP = r.getDatumVremeP();
		this.datumVremeS = r.getDatumVremeS();
		this.cena = r.getCena();
		if(r.getKarta()!=null) {
			this.kartaDTO = new KartaDTO(r.getKarta());
			System.out.println("karta id "+ r.getKarta().getId());
		}else {
			this.kartaDTO = null;
			System.out.println("karta je null");
		}
		if(r.getRezervacijaRentACar()!=null)
			this.rezervacijaRentACarDTO = new RezervacijaRentACarDTO(r.getRezervacijaRentACar());
		else
			this.rezervacijaRentACarDTO=null;
		if(r.getUser()!=null)
			this.userDTO = new UserDTO(r.getUser());
		else
			this.userDTO=null;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public double getCena() {
		return cena;
	}

	public void setCena(double cena) {
		this.cena = cena;
	}

	public KartaDTO getKartaDTO() {
		return kartaDTO;
	}

	public void setKartaDTO(KartaDTO kartaDTO) {
		this.kartaDTO = kartaDTO;
	}

	public RezervacijaRentACarDTO getRezervacijaRentACarDTO() {
		return rezervacijaRentACarDTO;
	}

	public void setRezervacijaRentACarDTO(RezervacijaRentACarDTO rezervacijaRentACarDTO) {
		this.rezervacijaRentACarDTO = rezervacijaRentACarDTO;
	}

	public UserDTO getUserDTO() {
		return userDTO;
	}

	public void setUserDTO(UserDTO userDTO) {
		this.userDTO = userDTO;
	}
}
