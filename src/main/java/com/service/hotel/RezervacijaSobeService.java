package com.service.hotel;

import com.model.hotel.RezervacijaSobe;
import com.repository.hotel.RezervacijaSobeRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RezervacijaSobeService {

    private RezervacijaSobeRepository rezervacijaSobeRepository;

     public Optional<RezervacijaSobe> findById(Long id){
                    return rezervacijaSobeRepository.findById(id);
            }

            public List<RezervacijaSobe> findAll(){
                return rezervacijaSobeRepository.findAll();
            }

            public RezervacijaSobe save(RezervacijaSobe rezervacijasobe){
                return rezervacijaSobeRepository.save(rezervacijasobe);
            }

            public void remove(Long id){
                rezervacijaSobeRepository.deleteById(id);
          }

}
