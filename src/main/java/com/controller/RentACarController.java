package com.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.dto.FilijalaDTO;
import com.dto.RentACarDTO;
import com.model.Filijala;
import com.model.RentACar;
import com.repository.RentACarRepository;
import com.service.RentACarService;

import ch.qos.logback.core.rolling.helper.RenameUtil;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping(value="api/rentacar")
public class RentACarController {

	@Autowired
	private RentACarService rentACarService;
	
	@RequestMapping(value="/{id}",method = RequestMethod.GET)
	public ResponseEntity<RentACarDTO> getRentACar(@PathVariable Long id){
		
		Optional<RentACar> rentOptional = rentACarService.findOne(id);
		
		if (rentOptional.isPresent()) {		
			return new ResponseEntity<>(new RentACarDTO(rentOptional.get()),HttpStatus.OK);
		}else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	
	}
	
	@RequestMapping(value="/all",method=RequestMethod.GET)
	public ResponseEntity<List<RentACarDTO>> getAll(){
		
		List<RentACar> rentACar = rentACarService.findAll();
		
		List<RentACarDTO> rentACarDTO = new ArrayList<>();
		
		for (RentACar r : rentACar) {
			rentACarDTO.add(new RentACarDTO(r));
		}

		return new ResponseEntity<>(rentACarDTO,HttpStatus.OK);
	}
	
	@RequestMapping(value="/{id}/filijale",method=RequestMethod.GET)
	public ResponseEntity<List<FilijalaDTO>> getFilijale(@PathVariable Long id){
		
		Optional<RentACar> rentOptional = rentACarService.findOne(id);
		
		if (!rentOptional.isPresent()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		Set<Filijala> filijale = rentOptional.get().getFilijale();
		
		List<FilijalaDTO> filijaleDTO = new ArrayList<>();
		
		for (Filijala f : filijale) {
			FilijalaDTO filijalaDTO = new FilijalaDTO(f);
			filijaleDTO.add(filijalaDTO);
		}

		return new ResponseEntity<>(filijaleDTO,HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.POST,consumes = "application/json")
	public ResponseEntity<RentACarDTO> saveRentACar(@RequestBody RentACarDTO rentACarDTO){
		
		RentACar rentACar = new RentACar();
		rentACar.setId(rentACarDTO.getId());
		rentACar.setNaziv(rentACarDTO.getNaziv());
		rentACar.setOpis(rentACarDTO.getOpis());
	
		rentACar = rentACarService.save(rentACar);
		
		return new ResponseEntity<>(new RentACarDTO(rentACar),HttpStatus.CREATED);

	}
	
	@RequestMapping(value="/{id}",method = RequestMethod.DELETE)
	public ResponseEntity<Void> deleteRentACar(@PathVariable Long id){
		
		Optional<RentACar> rentOptional = rentACarService.findOne(id);
		
		if (rentOptional.isPresent()) {		
			rentACarService.remove(id);
			return new ResponseEntity<>(HttpStatus.OK);
		}else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	
	}
	
	
	
	
}
