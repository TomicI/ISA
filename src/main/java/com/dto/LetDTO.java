package com.dto;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.model.Aerodrom;
import com.model.Let;
import com.model.Sediste;

public class LetDTO {

	private Long id;
	private Date datumP;
	private Date datumS;
	private String vremeP;
	private String vremeS;
	private Integer brojSegmenata;
	private Integer brojKolona;
	private Integer brojRedova;
	private AerodromDTO aerodromP;
	private AerodromDTO aerodromS;
	private Double prosecnaOcena;
	private AviokompanijaDTO aviokompanijaID;
	private String vremePutovanja;
	private Double duzinaPutovanja;
	private List<AerodromDTO> presedanje;
	private List<SedisteDTO> sedista;
	private String opis;
	
	public LetDTO() {
	}
	
	public LetDTO(Let l) {
		this(l.getId(), l.getDatumP(), l.getDatumS(), l.getVremeP().toString(), l.getVremeS().toString(), l.getBrojSegmenata() , new AerodromDTO(l.getAerodromP()), new AerodromDTO(l.getAerodromS()), l.getProsecnaOcena(), new AviokompanijaDTO(l.getAviokompanija()), l.getVremePutovanja(), l.getDuzinaPutovanja(), l.getBrojKolona() ,l.getBrojRedova(), l.getOpis(), getPresedanjeL(l.getPresedanje()), getSedistaL(l.getSedista()));
	}
	
	public LetDTO(Long id, Date dP, Date dS, String vP, String vS, Integer bS, AerodromDTO aP, AerodromDTO aS, Double pO, AviokompanijaDTO aviID, String vremePutovanja, Double duzinaPutovanja,  Integer bK, Integer bR, String o, List<AerodromDTO> pr, List<SedisteDTO> s) {
		super();
		System.out.println("Dobijem " + id );
		this.id = id;
		this.brojSegmenata=bS;
		this.brojKolona=bK;
		this.brojRedova=bR;
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
		this.setPresedanje(pr);
		this.opis=o;
		this.setSedista(s);
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

	public AviokompanijaDTO getAviokompanijaID() {
		return aviokompanijaID;
	}

	public void setAviokompanijaID(AviokompanijaDTO aviokompanijaID) {
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

	public List<AerodromDTO> getPresedanje() {
		return presedanje;
	}

	public void setPresedanje(List<AerodromDTO> aerodromi) {
		this.presedanje = aerodromi;
	}

	public Integer getBrojSegmenata() {
		return brojSegmenata;
	}

	public void setBrojSegmenata(Integer brojSegmenata) {
		this.brojSegmenata = brojSegmenata;
	}

	public Integer getBrojKolona() {
		return brojKolona;
	}

	public void setBrojKolona(Integer brojKolona) {
		this.brojKolona = brojKolona;
	}

	public Integer getBrojRedova() {
		return brojRedova;
	}

	public void setBrojRedova(Integer brojRedova) {
		this.brojRedova = brojRedova;
	}

	public List<SedisteDTO> getSedista() {
		return sedista;
	}

	public void setSedista(List<SedisteDTO> sedista) {
		this.sedista = sedista;
	}

	public String getOpis() {
		return opis;
	}

	public void setOpis(String opis) {
		this.opis = opis;
	}
	
	public static List<AerodromDTO> getPresedanjeL(Set<Aerodrom> let) {
		List<AerodromDTO> l=new ArrayList<>();
		if(let.size()>0) {
			for (Aerodrom ae : let)  {
				AerodromDTO a=new AerodromDTO(ae);
				l.add(a);
			}
		}
		return l;
	}
	
	public static List<SedisteDTO> getSedistaL(Set<Sediste> let){
		List<SedisteDTO> l=new ArrayList<>();
		if(let.size()>0) {
			for (Sediste s : let)  {
				SedisteDTO a=new SedisteDTO(s);
				l.add(a);
			}
		}
		return l;
	}
} 
