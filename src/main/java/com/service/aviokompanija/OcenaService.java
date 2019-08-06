package com.service.aviokompanija;

import com.dto.aviokompanija.OcenaDTO;
import com.model.aviokompanija.Ocena;
import com.repository.aviokompanija.OcenaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class OcenaService {

    @Autowired
    private OcenaRepository ocenaRepository;

    private ListeDTO liste = new ListeDTO();

    public List<OcenaDTO> getAll(){
        List<Ocena> ocene = ocenaRepository.findAll();

        if(ocene.isEmpty())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Ocene ne postoje");

        return liste.ocene(ocene);
    }

    public OcenaDTO findById(Long id){
        Optional<Ocena> ocena = ocenaRepository.findById(id);

        if(ocena.isPresent())
            return new OcenaDTO(ocena.get());

        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Ocena ne postoji");
    }

    public OcenaDTO create(OcenaDTO ocenaDTO){
        Ocena ocena = new Ocena();
        ocena.setOcena(ocenaDTO.getOcena());

        ocenaRepository.save(ocena);
        return ocenaDTO;
    }

    public OcenaDTO update(OcenaDTO ocenaDTO){
        Optional<Ocena> ocena = ocenaRepository.findById(ocenaDTO.getId());
        if (!ocena.isPresent())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Ocena ne postoji");

        ocena.get().setOcena(ocenaDTO.getOcena());

        ocenaRepository.save(ocena.get());
        return new OcenaDTO(ocena.get());
    }

    public void delete(Long id){
        Optional<Ocena> ocena = ocenaRepository.findById(id);
        if (!ocena.isPresent())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Ocena ne postoji");

        ocenaRepository.deleteById(id);
    }
}
