package com.service.hotel;

import com.model.hotel.DodatneUslugeHotel;
import com.repository.hotel.DodatneuslugeHotelRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DodatneUslugeHotelService {

    private DodatneuslugeHotelRepository dodatneUslugeHotelRepository;

    public Optional<DodatneUslugeHotel> findById(Long id){
                return dodatneUslugeHotelRepository.findById(id);
        }

        public List<DodatneUslugeHotel> findAll(){
            return dodatneUslugeHotelRepository.findAll();
        }

        public DodatneUslugeHotel save(DodatneUslugeHotel dodatneUslugeHotel){
            return dodatneUslugeHotelRepository.save(dodatneUslugeHotel);
        }

        public void remove(Long id){
            dodatneUslugeHotelRepository.deleteById(id);
       }

}
