package com.dto.aviokompanija;

import com.model.aviokompanija.Aviokompanija;
import com.model.aviokompanija.DodatnaUslugaAviokompanija;
import com.model.aviokompanija.Karta;

public class DodatnaUslugaAviokompanijaDTO {

	private Long id;
	private String naziv;
	private String opis;
	private Double cena;
	private AviokompanijaDTO aviokompanija;

	
	public DodatnaUslugaAviokompanijaDTO(DodatnaUslugaAviokompanija dodatnaUsluga) {
		this(dodatnaUsluga.getId(),dodatnaUsluga.getNaziv(),dodatnaUsluga.getOpis(),dodatnaUsluga.getCena(),
				dodatnaUsluga.getAviokompanija());
	}
	
	public DodatnaUslugaAviokompanijaDTO(Long id, String naziv, String opis, Double cena, Aviokompanija aviokompanija) {
		this.setId(id);
		this.setNaziv(naziv);
		this.setOpis(opis);
		this.setCena(cena);
		if(aviokompanija!=null)
			this.aviokompanija = new AviokompanijaDTO(aviokompanija);
		else
			this.aviokompanija=null;

	}


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public Double getCena() {
		return cena;
	}

	public void setCena(Double cena) {
		this.cena = cena;
	}


	public AviokompanijaDTO getAviokompanija() {
		return aviokompanija;
	}

	public void setAviokompanija(AviokompanijaDTO aviokompanija) {
		this.aviokompanija = aviokompanija;
	}
}
