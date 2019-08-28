package com.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import com.dto.FilijalaDTO;
import com.model.Filijala;
import com.model.aviokompanija.Ocena;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.model.RentACar;
import com.repository.RentACarRepository;

@Service
public class RentACarService {
	
	@Autowired
	private RentACarRepository rentACarRepository;
	
	public	RentACar findByNaziv(String naziv) {
		return rentACarRepository.findOneByNaziv(naziv);
	}

	public Optional<RentACar> findOne(Long id){
		return rentACarRepository.findById(id);
	}
	
	public List<FilijalaDTO> findOneFilijala(Long id) {

		Optional<RentACar> rentOptional =findOne(id);

		if (!rentOptional.isPresent()) {

		}

		Set<Filijala> filijale = rentOptional.get().getFilijale();

		List<FilijalaDTO> filijaleDTO = new ArrayList<>();

		Double sumOcena;
		int broj;

		for (Filijala f : filijale) {
			FilijalaDTO filijalaDTO = new FilijalaDTO(f);
			Set<Ocena> ocene = f.getOcene();
			sumOcena = 0.0;
			broj = ocene.size();
			for(Ocena o : ocene){
				sumOcena += o.getOcena();
			}
			if (sumOcena > 0)
				filijalaDTO.setProsecnaOcena(sumOcena/broj);
			filijaleDTO.add(filijalaDTO);
		}

		return filijaleDTO;

	}
	
	public List<RentACar> findAll(){
		return rentACarRepository.findAll();
	}
	
	public RentACar save(RentACar rentACar) {
		return rentACarRepository.save(rentACar);
	}
	
	public void remove(Long id) {
		rentACarRepository.deleteById(id);
	}

	public boolean exists(String name){
		return rentACarRepository.existsByNaziv(name);
	}

	
	

}
