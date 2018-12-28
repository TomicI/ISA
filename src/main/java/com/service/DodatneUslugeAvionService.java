package com.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.model.DodatneUslugeAvion;
import com.repository.DodatneUslugeAvionRepository;
@Service
public class DodatneUslugeAvionService {

	private DodatneUslugeAvionRepository dodatneUslugeAvionRepository;
	
	public Optional<DodatneUslugeAvion> findById(Long id) {
		return dodatneUslugeAvionRepository.findById(id);
	}
	
	public List<DodatneUslugeAvion> findAll(){
		return dodatneUslugeAvionRepository.findAll();
	}
	
	public DodatneUslugeAvion save(DodatneUslugeAvion dU) {
		return dodatneUslugeAvionRepository.save(dU);
	}
	
	public void remove(Long id) {
		dodatneUslugeAvionRepository.deleteById(id);
	}
}
