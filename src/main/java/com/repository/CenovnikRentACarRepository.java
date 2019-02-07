package com.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.model.CenovnikRentACar;
import com.model.Vozilo;

public interface CenovnikRentACarRepository extends JpaRepository<CenovnikRentACar, Long> {
	
	List<CenovnikRentACar> findByVozilo(Vozilo vozilo);

}
