package com.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.model.Filijala;
import com.repository.FilijalaRepository;

@Service
public class FilijalaService {
	
	@Autowired
	private FilijalaRepository filijalaRepository;
	
	public Optional<Filijala> findOne(Long id){
		return filijalaRepository.findById(id);
	}
	
	public List<Filijala> findAll(){
		return filijalaRepository.findAll();
	}
	
	public Filijala save(Filijala f) {
		return filijalaRepository.save(f);
	}
	
	public void remove (Long id) {
		filijalaRepository.deleteById(id);
	}

}
