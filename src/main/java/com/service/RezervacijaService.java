package com.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.model.Rezervacija;
import com.repository.RezervacijaRepository;
@Service
public class RezervacijaService {

	private RezervacijaRepository rezervacijaRepository;
	
	public Optional<Rezervacija> findById(Long id) {
		return rezervacijaRepository.findById(id);
	}
	
	public List<Rezervacija> findAll(){
		return rezervacijaRepository.findAll();
	}
	
	public Rezervacija save(Rezervacija r) {
		return rezervacijaRepository.save(r);
	}
	
	public void remove(Long id) {
		rezervacijaRepository.deleteById(id);
	}
}
