package com.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.dto.CenovnikRentACarDTO;
import com.model.CenovnikRentACar;
import com.model.Vozilo;
import com.service.CenovnikRentACarService;
import com.service.VoziloService;

@RestController
@RequestMapping(value="api/cenovnikrent")
public class CenovnikRentACarController {
	
	@Autowired
	private CenovnikRentACarService cenovnikService;
	
	@Autowired
	private VoziloService voziloService;
	
	@RequestMapping(method=RequestMethod.POST,consumes="application/json")
	public ResponseEntity<CenovnikRentACarDTO> saveCenovnik(@PathVariable CenovnikRentACarDTO cenovnikDTO){
		
		if (cenovnikDTO.getVoziloDTO()==null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		Optional<Vozilo> voziloOptional = voziloService.findOne(cenovnikDTO.getVoziloDTO().getId());
		
		if (!voziloOptional.isPresent()) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		CenovnikRentACar rent = new CenovnikRentACar();
		
		rent.setId(cenovnikDTO.getId());
		rent.setOdDatuma(cenovnikDTO.getOdDatuma());
		rent.setDoDatuma(cenovnikDTO.getDoDatuma());
		rent.setCena(cenovnikDTO.getCena());
		rent.setSlobodan(cenovnikDTO.getSlobodan());
		rent.setCenovnik(voziloOptional.get());
		
		rent = cenovnikService.save(rent);
		
		return new ResponseEntity<>(new CenovnikRentACarDTO(rent),HttpStatus.CREATED);
		
	}

	@RequestMapping(value="/{id}",method=RequestMethod.GET)
	public ResponseEntity<CenovnikRentACarDTO> getCenovnik(@PathVariable Long id){
		
		Optional<CenovnikRentACar> optionalCenovnik = cenovnikService.findOne(id);
		
		if (!optionalCenovnik.isPresent()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<>(new CenovnikRentACarDTO(optionalCenovnik.get()),HttpStatus.OK);
		
	}
	
	@RequestMapping(value="/{id}",method=RequestMethod.DELETE)
	public ResponseEntity<Void> deleteCenovnik(@PathVariable Long id){
		
		Optional<CenovnikRentACar> optionalCenovnik = cenovnikService.findOne(id);
		
		if (optionalCenovnik.isPresent()) {
			cenovnikService.remove(id);
			return new ResponseEntity<>(HttpStatus.OK);
		}else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
	}
	

}
