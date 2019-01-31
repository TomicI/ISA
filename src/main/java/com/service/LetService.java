package com.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.model.Let;
import com.repository.LetRepository;

@Service
public class LetService {
	@Autowired
	private LetRepository letRepository;
	
	public Optional<Let> findById(Long id) {
		return letRepository.findById(id);
	}
	
	public List<Let> findAll(){
		return letRepository.findAll();
	}
	
	public Let save(Let let) {
		return letRepository.save(let);
	}
	
	public void remove(Long id) {
		letRepository.deleteById(id);
	}
}
