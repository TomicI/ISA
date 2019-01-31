package com.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.model.VezaAA;
import com.repository.VezaAARepository;
@Service
public class VezaAAService {

	private VezaAARepository vezaAARepository;
	
	public Optional<VezaAA> findById(Long id) {
		return vezaAARepository.findById(id);
	}
	
	public List<VezaAA> findAll(){
		return vezaAARepository.findAll();
	}
	
	public VezaAA save(VezaAA v) {
		return vezaAARepository.save(v);
	}
	
	public void remove(Long id) {
		vezaAARepository.deleteById(id);
	}
}
