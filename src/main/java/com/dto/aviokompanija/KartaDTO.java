package com.dto.aviokompanija;

import com.dto.RezervacijaDTO;
import com.model.Rezervacija;
import com.model.aviokompanija.Karta;
import com.model.user.User;

import java.util.List;

public class KartaDTO {

	private Long id;
	private LetDTO let;
	private User user;
	private Double prosecnaOcena;
	private Double cena;
	private Double popust;
	//private RezervacijaDTO rezervacija;

	public KartaDTO() {
	}

	public KartaDTO(Karta k) {
		this.id=k.getId();
		this.let=new LetDTO(k.getLet());
		this.cena=k.getCena();
		this.popust=k.getPopust();
		//this.rezervacija=new RezervacijaDTO(k.getRezervacija());
	}


	public KartaDTO(Long id, List<SedisteDTO> sediste, LetDTO let, List<PrtljagDTO> prtljag, User user, List<DodatnaUslugaAviokompanijaDTO> du, Double prosecnaOcena) {
		this.id = id;

		this.let=let;
		this.user=user;
		this.prosecnaOcena = prosecnaOcena;

	}
	
	public KartaDTO(Long id, LetDTO let, User user) {
		this.id = id;
		this.let=let;
		this.user=user;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LetDTO getLet() {
		return let;
	}

	public void setLet(LetDTO let) {
		this.let = let;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Double getProsecnaOcena() {
		return prosecnaOcena;
	}

	public void setProsecnaOcena(Double prosecnaOcena) {
		this.prosecnaOcena = prosecnaOcena;
	}

	public Double getCena() {
		return cena;
	}

	public void setCena(Double cena) {
		this.cena = cena;
	}

	public Double getPopust() {
		return popust;
	}

	public void setPopust(Double popust) {
		this.popust = popust;
	}
}
