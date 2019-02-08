package com.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.model.Karta;
import com.repository.KartaRepository;
@Service
public class KartaService {
	@Autowired
	private KartaRepository kartaRepository;
	
	public Optional<Karta> findById(Long id) {
		return kartaRepository.findById(id);
	}
	
	public List<Karta> findAll(){
		return kartaRepository.findAll();
	}
	
	public Karta save(Karta karta) {
		return kartaRepository.save(karta);
	}
	
	public void remove(Long id) {
		kartaRepository.deleteById(id);
	}
}
