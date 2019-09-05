package com.service.hotel;

import com.dto.hotel.HotelDTO;
import com.model.hotel.Hotel;
import com.repository.hotel.HotelRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class HotelService {

    private HotelRepository hotelRepository;

    public Optional<Hotel> findById(Long id){
            return hotelRepository.findById(id);
    }

    public List<Hotel> findAll(){
        return hotelRepository.findAll();
    }

    public Hotel save(Hotel hotel){
        return hotelRepository.save(hotel);
    }

    public void remove(Long id){
        hotelRepository.deleteById(id);
    }


}
