package com.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.model.Prtljag;
import com.repository.PrtljagRepository;
@Service
public class PrtljagService {

	private PrtljagRepository prtljagRepository;
	
	public Optional<Prtljag> findById(Long id) {
		return prtljagRepository.findById(id);
	}
	
	public List<Prtljag> findAll(){
		return prtljagRepository.findAll();
	}
	
	public Prtljag save(Prtljag p) {
		return prtljagRepository.save(p);
	}
	
	public void remove(Long id) {
		prtljagRepository.deleteById(id);
	}
}
