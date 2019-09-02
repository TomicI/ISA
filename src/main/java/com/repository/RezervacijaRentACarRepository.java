package com.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.model.RezervacijaRentACar;
import com.model.Vozilo;

public interface RezervacijaRentACarRepository extends JpaRepository<RezervacijaRentACar,Long> {
	
	List<RezervacijaRentACar> findByVozilo(Vozilo vozilo);


	

}
