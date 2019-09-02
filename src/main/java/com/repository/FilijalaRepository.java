package com.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

import com.model.Filijala;

public interface FilijalaRepository extends JpaRepository<Filijala,Long> {
    Filijala findByAdresa(String adresa);
    
    Boolean existsByAdresa(String adresa);

    List<Filijala> findByRentACarId(Long rentacar_id);


}
