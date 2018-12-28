package com.service;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.model.Aviokompanija;
import com.repository.AviokompanijaRepository;


@Service
public class AviokompanijaService {
	
	@Autowired
	private AviokompanijaRepository aviokompanijaRepository;
	
	public Optional<Aviokompanija> findById(Long id) {
		return aviokompanijaRepository.findById(id);
	}
	
	public List<Aviokompanija> findAll(){
		return aviokompanijaRepository.findAll();
	}
	
	public Aviokompanija save(Aviokompanija aviokompanija) {
		return aviokompanijaRepository.save(aviokompanija);
	}
	
	public void remove(Long id) {
		aviokompanijaRepository.deleteById(id);
	}
	
	
	
}
