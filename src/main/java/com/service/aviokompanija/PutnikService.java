package com.service.aviokompanija;

import com.dto.aviokompanija.PutnikDTO;
import com.model.aviokompanija.Karta;
import com.model.aviokompanija.Putnik;
import com.model.aviokompanija.Sediste;
import com.model.user.User;
import com.repository.UserRepository;
import com.repository.aviokompanija.KartaRepository;
import com.repository.aviokompanija.PuntikRepository;
import com.repository.aviokompanija.SedisteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.HashSet;
import java.util.Optional;

@Service
public class PutnikService {

    @Autowired
    private PuntikRepository putnikRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private KartaRepository kartaRepository;

    @Autowired
    private SedisteRepository sedisteRepository;

    public PutnikDTO create(Long id, PutnikDTO putnikDTO){
        Optional<Karta> karta = kartaRepository.findById(id);
        Putnik putnik=new Putnik();
        if(!karta.isPresent())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Karta ne postoji");

        if(karta.get().getSedista().isEmpty())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Sedista ne postoje");

        Sediste pom=new Sediste();
        for(Sediste s: karta.get().getSedista()){
            if(s.getPutnik()==null  && s.getZauzeto()) {
               pom=s;
                break;
            }
        }
        if(putnik.getSedista()==null)
            putnik.setSedista(new HashSet<>());
        putnik.getSedista().add(pom);
        putnik.setBrojPasosa(putnikDTO.getBrojPasosa());

        if(putnikDTO.getUser()!=null){
            Optional<User> u=userRepository.findByUsername(putnikDTO.getUser().getUsername());
            if(!u.isPresent())
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User ne postoji");

            putnik.setUser(u.get());
        }

        putnik.setIme(putnikDTO.getIme());
        putnik.setPrezime(putnikDTO.getPrezime());
        putnik=putnikRepository.save(putnik);
        pom.setPutnik(putnik);
        sedisteRepository.save(pom);
        return new PutnikDTO(putnik);
    }

}
