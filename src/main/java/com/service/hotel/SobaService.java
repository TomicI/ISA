package com.service.hotel;

import com.model.hotel.Soba;
import com.repository.hotel.SobaRepositry;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SobaService {

    private SobaRepositry sobaRepositry;

    public Optional<Soba> findById(Long id){
                return sobaRepositry.findById(id);
        }

        public List<Soba> findAll(){
            return sobaRepositry.findAll();
        }

        public Soba save(Soba soba){
            return sobaRepositry.save(soba);
        }

        public void remove(Long id){
            sobaRepositry.deleteById(id);
        }
}
