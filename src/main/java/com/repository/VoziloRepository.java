package com.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.model.Vozilo;

public interface VoziloRepository extends JpaRepository<Vozilo,Long> {

    List<Vozilo> findByFilijalaId(Long filijala_id);



	
}
