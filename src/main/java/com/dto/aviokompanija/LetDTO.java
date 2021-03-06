package com.dto.aviokompanija;

import com.model.aviokompanija.*;

import java.util.Date;

public class LetDTO {

	private Long id;
	private Date vremePolaska;
	private Date vremeDolaska;
	private Double prosecnaOcena;
	private Double duzinaPutovanja;
	private String vremePutovanja;
	private String opis;
	private String presedanja;
	private int brojPresedanja;
	private VrstaLeta vrstaLeta;
	private AerodromDTO aerodrom;
	private LokacijaDTO destinacija;
	private KonfiguracijaLetaDTO konfiguracijaLeta;

	
	public LetDTO(Let let) {
		this(let.getId(),let.getVremePolaska(),let.getVremeDolaska(),let.getProsecnaOcena(),let.getDuzinaPutovanja(),let.getVremePutovanja(),
				let.getOpis(),let.getPresedanja(),let.getBrojPresedanja(),let.getVrstaLeta(),let.getAerodrom(),let.getDestinacija(),let.getKonfiguracija());
	}
	
	public LetDTO(Long id, Date vremePolaska, Date vremeDolaska, Double prosecnaOcena, Double duzinaPutovanja, String vremePutovanja,
				  String opis, String presedanja, int brojPresedanja, VrstaLeta vrstaLeta, Aerodrom aerodrom, Lokacija lokacija,
				  KonfiguracijaLeta konfiguracijaLeta) {
		super();
		this.setId(id);
		if(vremeDolaska!=null)
			this.setVremeDolaska(vremeDolaska);
		else
			this.setVremeDolaska(null);
		this.setVremePolaska(vremePolaska);
		this.setProsecnaOcena(prosecnaOcena);
		this.setDuzinaPutovanja(duzinaPutovanja);
		this.setVremePutovanja(vremePutovanja);
		this.setOpis(opis);
		this.setPresedanja(presedanja);
		this.setBrojPresedanja(brojPresedanja);
		if(vrstaLeta!=null)
			this.setVrstaLeta(vrstaLeta);
		else
			this.setVrstaLeta(null);
		if(aerodrom!=null)
			this.setAerodrom(new AerodromDTO(aerodrom));
		else
			this.setAerodrom(null);
		if(lokacija!=null)
			this.setDestinacija(new LokacijaDTO(lokacija));
		else
			this.setDestinacija(null);
		if(konfiguracijaLeta!=null)
			this.setKonfiguracijaLeta(new KonfiguracijaLetaDTO(konfiguracijaLeta));
		else
			this.setKonfiguracijaLeta(null);
	}


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getVremePolaska() {
		return vremePolaska;
	}

	public void setVremePolaska(Date vremePolaska) {
		this.vremePolaska = vremePolaska;
	}

	public Date getVremeDolaska() {
		return vremeDolaska;
	}

	public void setVremeDolaska(Date vremeDolaska) {
		this.vremeDolaska = vremeDolaska;
	}

	public Double getProsecnaOcena() {
		return prosecnaOcena;
	}

	public void setProsecnaOcena(Double prosecnaOcena) {
		this.prosecnaOcena = prosecnaOcena;
	}

	public Double getDuzinaPutovanja() {
		return duzinaPutovanja;
	}

	public void setDuzinaPutovanja(Double duzinaPutovanja) {
		this.duzinaPutovanja = duzinaPutovanja;
	}

	public String getVremePutovanja() {
		return vremePutovanja;
	}

	public void setVremePutovanja(String vremePutovanja) {
		this.vremePutovanja = vremePutovanja;
	}

	public String getOpis() {
		return opis;
	}

	public void setOpis(String opis) {
		this.opis = opis;
	}

	public String getPresedanja() {
		return presedanja;
	}

	public void setPresedanja(String presedanja) {
		this.presedanja = presedanja;
	}

	public int getBrojPresedanja() {
		return brojPresedanja;
	}

	public void setBrojPresedanja(int brojPresedanja) {
		this.brojPresedanja = brojPresedanja;
	}

	public VrstaLeta getVrstaLeta() {
		return vrstaLeta;
	}

	public void setVrstaLeta(VrstaLeta vrstaLeta) {
		this.vrstaLeta = vrstaLeta;
	}

	public AerodromDTO getAerodrom() {
		return aerodrom;
	}

	public void setAerodrom(AerodromDTO aerodrom) {
		this.aerodrom = aerodrom;
	}

	public LokacijaDTO getDestinacija() {
		return destinacija;
	}

	public void setDestinacija(LokacijaDTO destinacija) {
		this.destinacija = destinacija;
	}

	public KonfiguracijaLetaDTO getKonfiguracijaLeta() {
		return konfiguracijaLeta;
	}

	public void setKonfiguracijaLeta(KonfiguracijaLetaDTO konfiguracijaLeta) {
		this.konfiguracijaLeta = konfiguracijaLeta;
	}
}
