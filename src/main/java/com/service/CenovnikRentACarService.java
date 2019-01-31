package com.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.model.CenovnikRentACar;
import com.repository.CenovnikRentACarRepository;

@Service
public class CenovnikRentACarService {
	
	@Autowired
	private CenovnikRentACarRepository cenovnikRentACarRepository;
	
	public List<CenovnikRentACar> findAll(){
		return cenovnikRentACarRepository.findAll();
	}
	
	public Optional<CenovnikRentACar> findOne(Long id){
		return cenovnikRentACarRepository.findById(id);
	}
	
	public CenovnikRentACar save(CenovnikRentACar c) {
		return cenovnikRentACarRepository.save(c);
	}
	
	public void remove(Long id) {
		cenovnikRentACarRepository.deleteById(id);
	}
	

}
