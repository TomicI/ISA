package com.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.model.Aerodrom;
import com.repository.AerodromRepository;


@Service
public class AerodromService {
	@Autowired
	private AerodromRepository aerodromRepository;
	
	public Optional<Aerodrom> findById(Long id) {
		System.out.println( " GAS " + id);
		return aerodromRepository.findById(id);
	}
	
	public List<Aerodrom> findAll(){
		System.out.println( " FAS " );
		return aerodromRepository.findAll();
	}
	
	public Aerodrom save(Aerodrom aerodrom) {
		return aerodromRepository.save(aerodrom);
	}
	
	public void remove(Long id) {
		aerodromRepository.deleteById(id);
	}
}
