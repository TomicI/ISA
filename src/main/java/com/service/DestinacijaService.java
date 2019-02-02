package com.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.model.Destinacija;
import com.repository.DestinacijaRepository;
@Service
public class DestinacijaService {
	@Autowired
	private DestinacijaRepository destinacijaRepository;
	
	public Optional<Destinacija> findById(Long id) {
		return destinacijaRepository.findById(id);
	}
	
	public List<Destinacija> findAll(){
		return destinacijaRepository.findAll();
	}
	
	public Destinacija save(Destinacija destinacija) {
		return destinacijaRepository.save(destinacija);
	}
	
	public void remove(Long id) {
		destinacijaRepository.deleteById(id);
	}
}
