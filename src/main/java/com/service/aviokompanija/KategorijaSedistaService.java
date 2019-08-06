package com.service.aviokompanija;

import com.dto.aviokompanija.KategorijaSedistaDTO;
import com.model.aviokompanija.KategorijaSedista;
import com.repository.aviokompanija.KategorijaSedistaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

public class KategorijaSedistaService {

    @Autowired
    private KategorijaSedistaRepository kategorijaSedistaRepository;

    private ListeDTO liste = new ListeDTO();

    public List<KategorijaSedistaDTO> getAll(){
        List<KategorijaSedista> kategorijeSedista = kategorijaSedistaRepository.findAll();

        if(kategorijeSedista.isEmpty())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Kategorije sedista ne postoji");

        return liste.kategorijeSedista(kategorijeSedista);
    }

    public KategorijaSedistaDTO findById(Long id){
        Optional<KategorijaSedista> kategorijaSedista = kategorijaSedistaRepository.findById(id);

        if(kategorijaSedista.isPresent())
            return new KategorijaSedistaDTO(kategorijaSedista.get());

        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Kategorija sedista ne postoji");
    }

    public KategorijaSedistaDTO create(KategorijaSedistaDTO kategorijaSedistaDTO){
        KategorijaSedista kategorijaSedista = new KategorijaSedista();
        kategorijaSedista.setNaziv(kategorijaSedistaDTO.getNaziv());
        kategorijaSedista.setCena(kategorijaSedistaDTO.getCena());

        kategorijaSedistaRepository.save(kategorijaSedista);

        return kategorijaSedistaDTO;
    }

    public KategorijaSedistaDTO update(KategorijaSedistaDTO kategorijaSedistaDTO){
        Optional<KategorijaSedista> kategorijaSedista = kategorijaSedistaRepository.findById(kategorijaSedistaDTO.getId());
        if (!kategorijaSedista.isPresent())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Kategorija sedista ne postoji");

        kategorijaSedistaRepository.save(kategorijaSedista.get());
        return new KategorijaSedistaDTO(kategorijaSedista.get());
    }

    public void delete(Long id){
        Optional<KategorijaSedista> kategorijaSedista = kategorijaSedistaRepository.findById(id);
        if (!kategorijaSedista.isPresent())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Kategorija sedista ne postoji");

        kategorijaSedistaRepository.deleteById(id);
    }
}
