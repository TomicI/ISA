package com.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.model.Vozilo;
import com.repository.VoziloRepository;

@Service
public class VoziloService {
	
	@Autowired
	private VoziloRepository voziloRepository;
	
	public Optional<Vozilo> findOne(Long id){
		return voziloRepository.findById(id);
	}
	
	public List<Vozilo> findAll(){
		return voziloRepository.findAll();
	}
	
	public Vozilo save(Vozilo vozilo) {
		return voziloRepository.save(vozilo);
	}
	
	public void remove(Long id) {
		voziloRepository.deleteById(id);
	}

}
