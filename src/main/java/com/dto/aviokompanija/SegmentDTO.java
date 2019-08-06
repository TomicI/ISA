package com.dto.aviokompanija;

import com.model.aviokompanija.KategorijaSedista;
import com.model.aviokompanija.KonfiguracijaLeta;
import com.model.aviokompanija.Segment;

public class SegmentDTO {

    private Long id;
    private int duzina;
    private int sirina;
    private int redniBroj;
    private KonfiguracijaLetaDTO konfiguracija;
    private KategorijaSedistaDTO kategorija;

    public SegmentDTO(Segment segment){
        this(segment.getId(),segment.getDuzina(), segment.getRedniBroj(),segment.getSirina(),segment.getKonfiguracija(),segment.getKategorija());
    }

    public SegmentDTO(Long id, int duzina, int sirina, int redniBroj, KonfiguracijaLeta konfiguracijaLeta, KategorijaSedista kategorijaSedista){
        this.setId(id);
        this.setDuzina(duzina);
        this.setSirina(sirina);
        this.setRedniBroj(redniBroj);
        this.setKonfiguracija(new KonfiguracijaLetaDTO(konfiguracijaLeta));
        this.setKategorija(new KategorijaSedistaDTO(kategorijaSedista));
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getDuzina() {
        return duzina;
    }

    public void setDuzina(int duzina) {
        this.duzina = duzina;
    }

    public int getSirina() {
        return sirina;
    }

    public void setSirina(int sirina) {
        this.sirina = sirina;
    }

    public KonfiguracijaLetaDTO getKonfiguracija() {
        return konfiguracija;
    }

    public void setKonfiguracija(KonfiguracijaLetaDTO konfiguracija) {
        this.konfiguracija = konfiguracija;
    }

    public KategorijaSedistaDTO getKategorija() {
        return kategorija;
    }

    public void setKategorija(KategorijaSedistaDTO kategorija) {
        this.kategorija = kategorija;
    }

    public int getRedniBroj() {
        return redniBroj;
    }

    public void setRedniBroj(int redniBroj) {
        this.redniBroj = redniBroj;
    }
}
