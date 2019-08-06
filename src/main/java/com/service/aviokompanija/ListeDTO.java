package com.service.aviokompanija;

import com.dto.aviokompanija.*;
import com.model.aviokompanija.*;

import java.util.ArrayList;
import java.util.List;

public class ListeDTO {

    public List<AviokompanijaDTO> aviokompanije(List<Aviokompanija> ulaznaLista){
        List<AviokompanijaDTO> lista = new ArrayList<>();
        for (Aviokompanija aviokompanija : ulaznaLista) {
            lista.add(new AviokompanijaDTO(aviokompanija));
        }

        return lista;
    }

    public List<AerodromDTO> aerodromi(List<Aerodrom> ulaznaLista){
        List<AerodromDTO> lista = new ArrayList<>();
        for (Aerodrom aerodrom : ulaznaLista) {
            lista.add(new AerodromDTO(aerodrom));
        }
        return lista;
    }

    public List<LokacijaDTO> lokacije(List<Lokacija> ulaznaLista){
        List<LokacijaDTO> lista = new ArrayList<>();
        for (Lokacija lokacija : ulaznaLista) {
            lista.add(new LokacijaDTO(lokacija));
        }
        return lista;
    }

    public List<PrtljagDTO> prtljag(List<Prtljag> ulaznaLista){
        List<PrtljagDTO> lista = new ArrayList<>();
        for (Prtljag prtljag : ulaznaLista) {
            lista.add(new PrtljagDTO(prtljag));
        }
        return lista;
    }

    public List<LetDTO> letovi(List<Let> ulaznaLista){
        List<LetDTO> lista = new ArrayList<>();
        for (Let let : ulaznaLista) {
            lista.add(new LetDTO(let));
        }
        return lista;
    }

    public List<KonfiguracijaLetaDTO> konfiguracije(List<KonfiguracijaLeta> ulaznaLista){
        List<KonfiguracijaLetaDTO> lista = new ArrayList<>();
        for (KonfiguracijaLeta konfiguracijaLeta : ulaznaLista) {
            lista.add(new KonfiguracijaLetaDTO(konfiguracijaLeta));
        }
        return lista;
    }

    public List<DodatnaUslugaAviokompanijaDTO> dodatneUsluge(List<DodatnaUslugaAviokompanija> ulaznaLista){
        List<DodatnaUslugaAviokompanijaDTO> lista = new ArrayList<>();
        for (DodatnaUslugaAviokompanija dodatnaUsluga : ulaznaLista) {
            lista.add(new DodatnaUslugaAviokompanijaDTO(dodatnaUsluga));
        }
        return lista;
    }

    public List<KategorijaSedistaDTO> kategorijeSedista(List<KategorijaSedista> ulaznaLista){
        List<KategorijaSedistaDTO> lista = new ArrayList<>();
        for (KategorijaSedista kategorijaSedista : ulaznaLista) {
            lista.add(new KategorijaSedistaDTO(kategorijaSedista));
        }
        return lista;
    }

    public List<KonfiguracijaLetaDTO> konfiguracijeLetova(List<KonfiguracijaLeta> ulaznaLista){
        List<KonfiguracijaLetaDTO> lista = new ArrayList<>();
        for (KonfiguracijaLeta konfiguracijaLeta : ulaznaLista) {
            lista.add(new KonfiguracijaLetaDTO(konfiguracijaLeta));
        }
        return lista;
    }

    public List<SegmentDTO> segmenti(List<Segment> ulaznaLista){
        List<SegmentDTO> lista = new ArrayList<>();
        for (Segment segment : ulaznaLista) {
            lista.add(new SegmentDTO(segment));
        }
        return lista;
    }

    public List<SedisteDTO> sedista(List<Sediste> ulaznaLista){
        List<SedisteDTO> lista = new ArrayList<>();
        for (Sediste sediste : ulaznaLista) {
            lista.add(new SedisteDTO(sediste));
        }
        return lista;
    }

    public List<OcenaDTO> ocene(List<Ocena> ulaznaLista){
        List<OcenaDTO> lista = new ArrayList<>();
        for (Ocena ocena : ulaznaLista) {
            lista.add(new OcenaDTO(ocena));
        }
        return lista;
    }

    public List<KartaDTO> karte(List<Karta> ulaznaLista){
        List<KartaDTO> lista = new ArrayList<>();
        for (Karta karta : ulaznaLista) {
            lista.add(new KartaDTO(karta));
        }
        return lista;
    }
}
