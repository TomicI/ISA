package com.service;


import com.model.Hotel;
import com.model.aviokompanija.Lokacija;
import com.repository.HotelRepository;
import com.repository.aviokompanija.LokacijaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class HotelService {

    @Autowired
    private HotelRepository hotelRepository;

    @Autowired
    private LokacijaRepository lokacijaRepository;

    public List<Hotel> findByLokacija(String grad, String drzava){
        List<Lokacija> lokacijas=lokacijaRepository.findByGradAndDrzava(grad, drzava);
        List<Hotel> hoteli=new ArrayList<>();

        for(Lokacija l :lokacijas){
            Hotel hotel=hotelRepository.findByLokacijaId(l.getId());
            if(hotel!=null && !hoteli.contains(hotel)){
                hoteli.add(hotel);

            }

        }

        return hoteli;
    }
}
