package com.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

import com.model.RentACar;


public interface RentACarRepository extends JpaRepository<RentACar,Long> {
	
	RentACar findOneByNaziv(String naziv);
	Boolean existsByNaziv(String username);



}
