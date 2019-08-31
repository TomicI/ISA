package com.service.aviokompanija;

import com.dto.aviokompanija.AerodromDTO;
import com.dto.aviokompanija.AviokompanijaDTO;
import com.dto.aviokompanija.LetDTO;
import com.dto.aviokompanija.LokacijaDTO;
import com.model.aviokompanija.Lokacija;
import com.repository.aviokompanija.LokacijaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@Service
public class LokacijaService {

    @Autowired
    private LokacijaRepository lokacijaRepository;

    private ListeDTO liste = new ListeDTO();

    public List<LokacijaDTO> getAll(){
        List<Lokacija> lokacije = lokacijaRepository.findAll();

        if(lokacije.isEmpty())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Lokacije ne postoji");

        return liste.lokacije(lokacije);
    }

    public LokacijaDTO findById(Long id){
        Optional<Lokacija> lokacija = lokacijaRepository.findById(id);

        if(lokacija.isPresent())
            return new LokacijaDTO(lokacija.get());

        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Lokacija ne postoji");
    }

    public LokacijaDTO create(LokacijaDTO lokacijaDTO){
        Lokacija lokacija = new Lokacija();

        lokacija.setGeoSirina(lokacijaDTO.getGeoSirina());
        lokacija.setGeoVisina(lokacijaDTO.getGeoVisina());
        lokacija.setNaziv(lokacijaDTO.getNaziv());

        lokacijaRepository.save(lokacija);
        return lokacijaDTO;
    }

    public LokacijaDTO update(LokacijaDTO lokacijaDTO){
        Optional<Lokacija> lokacija = lokacijaRepository.findById(lokacijaDTO.getId());
        if (!lokacija.isPresent())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Lokacija ne postoji");

        lokacijaRepository.save(lokacija.get());
        return new LokacijaDTO(lokacija.get());
    }

    public void delete(Long id){
        Optional<Lokacija> lokacija = lokacijaRepository.findById(id);
        if (!lokacija.isPresent())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Lokacija ne postoji");

        lokacijaRepository.deleteById(id);
    }

    public List<LetDTO> sviLetovi(Long id){
        Optional<Lokacija> lokacija = lokacijaRepository.findById(id);
        if (!lokacija.isPresent())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Lokacija ne postoji");

        if(lokacija.get().getLetovi().isEmpty())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Ne postoje letovi na datoj lokaciji");

        return liste.letovi(new ArrayList<>(lokacija.get().getLetovi()));
    }

    public List<AviokompanijaDTO> sveAviokompanije(Long id){
        Optional<Lokacija> lokacija = lokacijaRepository.findById(id);
        if (!lokacija.isPresent())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Lokacija ne postoji");

        if(lokacija.get().getAviokompanije().isEmpty())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Ne postoje aviokompanije na datoj lokaciji");

        return liste.aviokompanije(new ArrayList<>(lokacija.get().getAviokompanije()));
    }

    public List<AerodromDTO> sviAerodromi(Long id){
        Optional<Lokacija> lokacija = lokacijaRepository.findById(id);
        if (!lokacija.isPresent())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Lokacija ne postoji");

        if(lokacija.get().getAerodrom().isEmpty())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Ne postoje aerodromi na datoj lokaciji");

        return liste.aerodromi(new ArrayList<>(lokacija.get().getAerodrom()));
    }
}
