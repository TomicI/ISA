package com.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.model.Sediste;
import com.repository.SedisteRepository;
@Service
public class SedisteService {
	@Autowired
	private SedisteRepository sedisteRepository;
	
	public Optional<Sediste> findById(Long id) {
		return sedisteRepository.findById(id);
	}
	
	public List<Sediste> findAll(){
		return sedisteRepository.findAll();
	}
	
	public Sediste save(Sediste sediste) {
		return sedisteRepository.save(sediste);
	}
	
	public void remove(Long id) {
		sedisteRepository.deleteById(id);
	}
}
