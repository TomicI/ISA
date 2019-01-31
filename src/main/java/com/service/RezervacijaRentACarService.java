package com.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.model.RezervacijaRentACar;
import com.repository.RezervacijaRentACarRepository;

@Service
public class RezervacijaRentACarService {
	
	@Autowired
	private RezervacijaRentACarRepository rezRepository;
	
	public Optional<RezervacijaRentACar> findOne(Long id){
		return rezRepository.findById(id);
	}
	
	public List<RezervacijaRentACar> findAll(){
		return rezRepository.findAll();
	}
	
	public RezervacijaRentACar save(RezervacijaRentACar r) {
		return rezRepository.save(r);
	}
	
	public void remove (Long id) {
		rezRepository.deleteById(id);
	}

}
