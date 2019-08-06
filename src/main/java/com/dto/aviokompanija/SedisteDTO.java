package com.dto.aviokompanija;

import com.model.aviokompanija.*;

public class SedisteDTO {

	private Long id;
	private Integer kolona;
	private Integer red;
	private Boolean zauzeto;
	private KartaDTO karta;
	private PutnikDTO putnik;
	private PrtljagDTO prtljag;
	private SegmentDTO segment;

	public SedisteDTO(Sediste sediste) {
		this(sediste.getId(),sediste.getKolona(),sediste.getRed(),sediste.getZauzeto(),sediste.getKarta(),sediste.getPutnik(),sediste.getPrtljag(),
				sediste.getSegment());
	}
	
	public SedisteDTO(Long id, Integer kolona, Integer red, Boolean zauzeto, Karta karta, Putnik putnik, Prtljag prtljag, Segment segment) {
		super();
		this.setId(id);
		this.setKolona(kolona);
		this.setRed(red);
		this.setZauzeto(zauzeto);
		this.setKarta(new KartaDTO(karta));
		this.setPutnik(new PutnikDTO(putnik));
	}


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public KartaDTO getKarta() {
		return karta;
	}

	public void setKarta(KartaDTO karta) {
		this.karta = karta;
	}

	public PutnikDTO getPutnik() {
		return putnik;
	}

	public void setPutnik(PutnikDTO putnik) {
		this.putnik = putnik;
	}

	public PrtljagDTO getPrtljag() {
		return prtljag;
	}

	public void setPrtljag(PrtljagDTO prtljag) {
		this.prtljag = prtljag;
	}

	public SegmentDTO getSegment() {
		return segment;
	}

	public void setSegment(SegmentDTO segment) {
		this.segment = segment;
	}
}
