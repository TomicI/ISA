package com.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.model.CenovnikAvio;
import com.repository.CenovnikAvioRepository;

@Service
public class CenovnikAvioService {
	@Autowired
	private CenovnikAvioRepository cenovnikAvioRepository;
	
	public Optional<CenovnikAvio> findById(Long id){
		return cenovnikAvioRepository.findById(id);
	}
	
	public List<CenovnikAvio> findAll(){
		return cenovnikAvioRepository.findAll();
	}
	
	public CenovnikAvio save(CenovnikAvio cA) {
		return cenovnikAvioRepository.save(cA);
	}
	
	public void remove(Long id) {
		cenovnikAvioRepository.deleteById(id);
	}
}
