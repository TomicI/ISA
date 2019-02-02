package com.dto;


import javax.persistence.Column;

import com.model.Gorivo;
import com.model.Menjac;
import com.model.Vozilo;

public class VoziloDTO {
	
	private Long id;

	private String naziv;

	private String marka;

	private String model;

	private Integer brojSedista;

	private Integer brojVrata;

	private Double brojTorbi;

	private Gorivo gorivo;

	private Menjac menjac;

	private Boolean klima;

	private Double prosecnaOcena;
	
	private Integer rezervoar;

	private Double potrosnja;
	
	private String dodatniopis;

	private FilijalaDTO filijalaDTO;
	
	
	public VoziloDTO() {
		
	}
	

	public VoziloDTO(Long id, String naziv, String marka, String model, Integer brojSedista, Integer brojVrata,
			Double brojTorbi, Gorivo gorivo, Menjac menjac, Boolean klima, Double prosecnaOcena,Integer rezervoar,Double potrosnja,String dodatniopis,
			FilijalaDTO filijalaDTO) {
		super();
		this.id = id;
		this.naziv = naziv;
		this.marka = marka;
		this.model = model;
		this.brojSedista = brojSedista;
		this.brojVrata = brojVrata;
		this.brojTorbi = brojTorbi;
		this.gorivo = gorivo;
		this.menjac = menjac;
		this.klima = klima;
		this.prosecnaOcena = prosecnaOcena;
		this.rezervoar = rezervoar;
		this.potrosnja =potrosnja;
		this.dodatniopis = dodatniopis;
		this.filijalaDTO = filijalaDTO;
	}


	public VoziloDTO(Vozilo v) {
		this.id = v.getId();
		this.naziv = v.getNaziv();
		this.marka =v.getMarka();
		this.model = v.getModel();
		this.brojSedista = v.getBrojSedista();
		this.brojVrata = v.getBrojVrata();
		this.brojTorbi =v.getBrojTorbi();
		this.gorivo = v.getGorivo();
		this.menjac = v.getMenjac();
		this.klima = v.getKlima();
		this.prosecnaOcena = v.getProsecnaOcena();
		this.rezervoar=v.getRezervoar();
		this.potrosnja= v.getPotrosnja();
		this.dodatniopis = v.getDodatniopis();
		this.filijalaDTO = new FilijalaDTO(v.getVozilo());

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

	public String getMarka() {
		return marka;
	}

	public void setMarka(String marka) {
		this.marka = marka;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public Integer getBrojSedista() {
		return brojSedista;
	}

	public void setBrojSedista(Integer brojSedista) {
		this.brojSedista = brojSedista;
	}

	public Integer getBrojVrata() {
		return brojVrata;
	}

	public void setBrojVrata(Integer brojVrata) {
		this.brojVrata = brojVrata;
	}

	public Double getBrojTorbi() {
		return brojTorbi;
	}

	public void setBrojTorbi(Double brojTorbi) {
		this.brojTorbi = brojTorbi;
	}

	public Gorivo getGorivo() {
		return gorivo;
	}

	public void setGorivo(Gorivo gorivo) {
		this.gorivo = gorivo;
	}

	public Menjac getMenjac() {
		return menjac;
	}

	public void setMenjac(Menjac menjac) {
		this.menjac = menjac;
	}

	public Boolean getKlima() {
		return klima;
	}

	public void setKlima(Boolean klima) {
		this.klima = klima;
	}

	public Double getProsecnaOcena() {
		return prosecnaOcena;
	}

	public void setProsecnaOcena(Double prosecnaOcena) {
		this.prosecnaOcena = prosecnaOcena;
	}

	public FilijalaDTO getFilijalaDTO() {
		return filijalaDTO;
	}

	public void setFilijalaDTO(FilijalaDTO filijalaDTO) {
		this.filijalaDTO = filijalaDTO;
	}


	public Integer getRezervoar() {
		return rezervoar;
	}


	public void setRezervoar(Integer rezervoar) {
		this.rezervoar = rezervoar;
	}


	public Double getPotrosnja() {
		return potrosnja;
	}


	public void setPotrosnja(Double potrosnja) {
		this.potrosnja = potrosnja;
	}


	public String getDodatniopis() {
		return dodatniopis;
	}


	public void setDodatniopis(String dodatniopis) {
		this.dodatniopis = dodatniopis;
	}

	
}
