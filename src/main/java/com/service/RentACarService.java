package com.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.model.RentACar;
import com.repository.RentACarRepository;

@Service
public class RentACarService {
	
	@Autowired
	private RentACarRepository rentACarRepository;
	
	public	RentACar findByNaziv(String naziv) {
		return rentACarRepository.findOneByNaziv(naziv);
	}
	
	public Optional<RentACar> findOne(Long id) {
		return rentACarRepository.findById(id);	
	}
	
	public List<RentACar> findAll(){
		return rentACarRepository.findAll();
	}
	
	public RentACar save(RentACar rentACar) {
		return rentACarRepository.save(rentACar);
	}
	
	public void remove(Long id) {
		rentACarRepository.deleteById(id);
	}

	public boolean exists(String name){
		return rentACarRepository.existsByNaziv(name);
	}

	
	

}
