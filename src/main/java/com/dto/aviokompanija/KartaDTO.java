package com.dto.aviokompanija;

import com.model.aviokompanija.Karta;
import com.model.user.User;

import java.util.List;

public class KartaDTO {

	private Long id;
	private LetDTO let;
	private User user;
	private CenovnikAvioDTO cena;
	private Double prosecnaOcena;
	public KartaDTO() {
	}
	
	public KartaDTO(Karta k) {
	}
	
	
	public KartaDTO(Long id, List<SedisteDTO> sediste, LetDTO let, List<PrtljagDTO> prtljag, User user, List<DodatnaUslugaAviokompanijaDTO> du, Double prosecnaOcena, CenovnikAvioDTO c) {
		this.id = id;

		this.let=let;
		this.user=user;
				this.prosecnaOcena = prosecnaOcena;
		this.cena=c;
	}
	
	public KartaDTO(Long id, LetDTO let, User user) {
		this.id = id;
		this.let=let;
		this.user=user;
	}

	
}
