package com.service.aviokompanija;

import com.dto.aviokompanija.KategorijaSedistaDTO;
import com.model.aviokompanija.Aviokompanija;
import com.model.aviokompanija.KategorijaSedista;
import com.model.user.User;
import com.repository.UserRepository;
import com.repository.aviokompanija.AviokompanijaRepository;
import com.repository.aviokompanija.KategorijaSedistaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
@Service
public class KategorijaSedistaService {

    @Autowired
    private KategorijaSedistaRepository kategorijaSedistaRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AviokompanijaRepository aviokompanijaRepository;

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

    public KategorijaSedistaDTO create(KategorijaSedistaDTO kategorijaSedistaDTO, String username){
        Optional<User> us=this.userRepository.findByUsername(username);

        if(!us.isPresent() || us.get().getAviokompanija()==null)
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User ne postoji");


        KategorijaSedista kategorijaSedista = new KategorijaSedista();
        kategorijaSedista.setNaziv(kategorijaSedistaDTO.getNaziv());
        kategorijaSedista.setCena(kategorijaSedistaDTO.getCena());
        kategorijaSedista.setAviokompanija(us.get().getAviokompanija());

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
