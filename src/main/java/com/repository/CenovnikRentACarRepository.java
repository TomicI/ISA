package com.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.model.CenovnikRentACar;
import com.model.RentACar;
import com.model.Vozilo;

public interface CenovnikRentACarRepository extends JpaRepository<CenovnikRentACar, Long> {
	
	List<CenovnikRentACar> findByVozilo(Vozilo vozilo);
	List<CenovnikRentACar> findByServis(RentACar servis);

	List<CenovnikRentACar> findByVoziloId(Long id);

	

}
