package com.dto;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.model.DodatneUslugeAvion;
import com.model.Karta;
import com.model.Prtljag;
import com.model.Sediste;
import com.model.user.User;

public class KartaDTO {

	private Long id;
	private List<SedisteDTO> sediste;
	private LetDTO let;
	private List<PrtljagDTO> prtljag;
	private User user;
	private List<DodatneUslugeAvionDTO> dodatneUsluge;
	private CenovnikAvioDTO cena;
	private Double prosecnaOcena;
	public KartaDTO() {
	}
	
	public KartaDTO(Karta k) {
		this(k.getId() , new LetDTO(k.getLet()),k.getUser());
	}
	
	
	public KartaDTO(Long id, List<SedisteDTO> sediste, LetDTO let, List<PrtljagDTO> prtljag, User user, List<DodatneUslugeAvionDTO> du, Double prosecnaOcena, CenovnikAvioDTO c) {
		this.id = id;
		this.sediste=sediste;
		this.let=let;
		this.setPrtljag(prtljag);
		this.user=user;
		this.setDodatneUsluge(du);
		this.prosecnaOcena = prosecnaOcena;
		this.cena=c;
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
	
	public Double getProsecnaOcena() {
		return prosecnaOcena;
	}
	public void setProsecnaOcena(Double prosecnaOcena) {
		this.prosecnaOcena = prosecnaOcena;
	}

	public List<SedisteDTO> getSediste() {
		return sediste;
	}

	public void setSediste(List<SedisteDTO> sediste) {
		this.sediste = sediste;
	}

	public LetDTO getLet() {
		return let;
	}

	public void setLet(LetDTO let) {
		this.let = let;
	}

	public List<PrtljagDTO> getPrtljag() {
		return prtljag;
	}

	public void setPrtljag(List<PrtljagDTO> prtljag) {
		this.prtljag = prtljag;
	}
	
	public static List<PrtljagDTO> setPrtljagL(Set<Prtljag> prtljag) {
		List<PrtljagDTO> pr=new ArrayList<>();
		
		if(prtljag.size()>0) {
			for(Prtljag p: prtljag) {
				pr.add(new PrtljagDTO(p));
			}
		}
		return pr;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<DodatneUslugeAvionDTO> getDodatneUsluge() {
		return dodatneUsluge;
	}

	public void setDodatneUsluge(List<DodatneUslugeAvionDTO> dodatneUsluge) {
		this.dodatneUsluge = dodatneUsluge;
	}
	
	
	public static List<DodatneUslugeAvionDTO> setDodatneUslugeL(Set<DodatneUslugeAvion> dodatneUsluge) {
		List<DodatneUslugeAvionDTO> du=new ArrayList<>();
		if(dodatneUsluge.size()>0) {
			for(DodatneUslugeAvion p: dodatneUsluge) {
				du.add(new DodatneUslugeAvionDTO(p));
			}
		}
		return du;
	}
	
	public static List<SedisteDTO> setSedisteL(Set<Sediste> sedista) {
		List<SedisteDTO> du=new ArrayList<>();
		if(sedista.size()>0) {
			for(Sediste p: sedista) {
				du.add(new SedisteDTO(p));
			}
		}
		return du;
	}

	public CenovnikAvioDTO getCena() {
		return cena;
	}

	public void setCena(CenovnikAvioDTO cena) {
		this.cena = cena;
	}
	
}
