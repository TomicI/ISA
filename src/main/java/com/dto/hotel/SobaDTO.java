package com.dto.hotel;

import com.model.hotel.Soba;

public class SobaDTO {

    private Long id;
    private String tipSobe;
    private Integer brojOsoba;
    private Double prosecnaOcena;
    private Integer sprat;

    public SobaDTO() {

        }

        public SobaDTO(Soba s) {
        		this(s.getId(), s.getTipSobe(),s.getBrojOsoba(),s.getProsecnaOcena(),s.getSprat());
        	}

        	public SobaDTO(Long id, String tipSobe, Integer brojOsoba, Double prosecnaOcena, Integer sprat) {
        		super();
        		this.id = id;
                this.tipSobe=tipSobe;
                this.brojOsoba=brojOsoba;
                this.prosecnaOcena=prosecnaOcena;
                this.sprat=sprat;
        	}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTipSobe() {
        return tipSobe;
    }

    public void setTipSobe(String tipSobe) {
        this.tipSobe = tipSobe;
    }

    public Integer getBrojOsoba() {
        return brojOsoba;
    }

    public void setBrojOsoba(Integer brojOsoba) {
        this.brojOsoba = brojOsoba;
    }

    public Double getProsecnaOcena() {
        return prosecnaOcena;
    }

    public void setProsecnaOcena(Double prosecnaOcena) {
        this.prosecnaOcena = prosecnaOcena;
    }

    public Integer getSprat() {
        return sprat;
    }

    public void setSprat(Integer sprat) {
        this.sprat = sprat;
    }

    public Boolean getTerasa() {
        return terasa;
    }

    public void setTerasa(Boolean terasa) {
        this.terasa = terasa;
    }

    private Boolean terasa;
}
