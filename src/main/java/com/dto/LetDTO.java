package com.dto;

import java.sql.Date;
import java.sql.Time;

import com.model.Let;

public class LetDTO {

	private Long id;
	private Date datumP;
	private Date datumS;
	private String vremeP;
	private String vremeS;
	private Integer brojSedista;
	private AerodromDTO aerodromP;
	private AerodromDTO aerodromS;
	private Double prosecnaOcena;
	private Long aviokompanijaID;
	private String vremePutovanja;
	private Double duzinaPutovanja;
	private Boolean imaPresedanje;
	
	public LetDTO() {
	}
	
	public LetDTO(Let l) {
		this(l.getId(), l.getDatumP(), l.getDatumS(), l.getVremeP().toString(), l.getVremeS().toString(), l.getBrojSedista(), new AerodromDTO(l.getAerodromP()), new AerodromDTO(l.getAerodromS()), l.getProsecnaOcena(), l.getAviokompanija().getId(), l.getVremePutovanja(), l.getDuzinaPutovanja(), l.getImaPresedanje());
	}
	
	public LetDTO(Long id, Date dP, Date dS, String vP, String vS, Integer bS, AerodromDTO aP, AerodromDTO aS, Double pO, Long aviID, String vremePutovanja, Double duzinaPutovanja, Boolean iP) {
		super();
		System.out.println("Dobijem " + id );
		this.id = id;
		this.brojSedista=bS;
		this.datumP=dP;
		this.datumS=dS;
		this.vremeP=vP;
		this.vremeS=vS;
		this.aerodromP=aP;
		this.aerodromS=aS;
		this.prosecnaOcena=pO;
		this.aviokompanijaID=aviID;
		this.vremePutovanja=vremePutovanja;
		this.duzinaPutovanja=duzinaPutovanja;
		this.imaPresedanje=iP;
	}
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}

	public Date getDatumP() {
		return datumP;
	}

	public void setDatumP(Date datumP) {
		this.datumP = datumP;
	}

	public Date getDatumS() {
		return datumS;
	}

	public void setDatumS(Date datumS) {
		this.datumS = datumS;
	}

	public String getVremeP() {
		return vremeP;
	}

	public void setVremeP(String vremeP) {
		this.vremeP = vremeP;
	}

	public String getVremeS() {
		return vremeS;
	}

	public void setVremeS(String vremeS) {
		this.vremeS = vremeS;
	}

	public Integer getBrojSedista() {
		return brojSedista;
	}

	public void setBrojSedista(Integer brojSedista) {
		this.brojSedista = brojSedista;
	}

	public AerodromDTO getAerodromP() {
		return aerodromP;
	}

	public void setAerodromP(AerodromDTO aerodromP) {
		this.aerodromP = aerodromP;
	}

	public AerodromDTO getAerodromS() {
		return aerodromS;
	}

	public void setAerodromS(AerodromDTO aerodromS) {
		this.aerodromS = aerodromS;
	}

	public Double getProsecnaOcena() {
		return prosecnaOcena;
	}

	public void setProsecnaOcena(Double prosecnaOcena) {
		this.prosecnaOcena = prosecnaOcena;
	}

	public Long getAviokompanijaID() {
		return aviokompanijaID;
	}

	public void setAviokompanijaID(Long aviokompanijaID) {
		this.aviokompanijaID = aviokompanijaID;
	}

	public String getVremePutovanja() {
		return vremePutovanja;
	}

	public void setVremePutovanja(String vremePutovanja) {
		this.vremePutovanja = vremePutovanja;
	}

	public Double getDuzinaPutovanja() {
		return duzinaPutovanja;
	}

	public void setDuzinaPutovanja(Double duzinaPutovanja) {
		this.duzinaPutovanja = duzinaPutovanja;
	}

	public Boolean getImaPresedanje() {
		return imaPresedanje;
	}

	public void setImaPresedanje(Boolean imaPresedanje) {
		this.imaPresedanje = imaPresedanje;
	}
	
	
}
