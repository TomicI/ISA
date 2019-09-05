package com.service.hotel;


import com.model.hotel.CenovnikHotel;
import com.repository.hotel.CenovnikHotelRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CenovnikHotelService {

    private CenovnikHotelRepository cenovnikHotelRepository;

    public Optional<CenovnikHotel> findById(Long id){
                return cenovnikHotelRepository.findById(id);
        }

        public List<CenovnikHotel> findAll(){
            return cenovnikHotelRepository.findAll();
        }

        public CenovnikHotel save(CenovnikHotel cenovnikHotel){
            return cenovnikHotelRepository.save(cenovnikHotel);
        }

        public void remove(Long id){
            cenovnikHotelRepository.deleteById(id);
       }
}
