package com.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.dto.FilijalaDTO;
import com.dto.RentACarDTO;
import com.dto.RezervacijaRentACarDTO;
import com.dto.VoziloDTO;
import com.model.Filijala;
import com.model.RezervacijaRentACar;
import com.model.Vozilo;
import com.service.FilijalaService;
import com.service.RezervacijaRentACarService;
import com.service.VoziloService;

@RestController
@RequestMapping(value="api/rezervacijarent")
public class RezervacijaRentACarController {
	
	@Autowired
	private  RezervacijaRentACarService rezService;
	
	@Autowired
	private  FilijalaService filijalaService;
	
	@Autowired
	private VoziloService voziloService;
	
	@RequestMapping(method=RequestMethod.POST,consumes="application/json")
	public ResponseEntity<RezervacijaRentACarDTO> saveRezervacija (@PathVariable RezervacijaRentACarDTO rezDTO){
		
		if (rezDTO.getFilijalaDTO()==null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		if (rezDTO.getVoziloDTO()==null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		Optional<Filijala> filijalaOptional = filijalaService.findOne(rezDTO.getFilijalaDTO().getId());
		Optional<Vozilo> voziloOptional = voziloService.findOne(rezDTO.getVoziloDTO().getId());
		
		if (!filijalaOptional.isPresent()) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		if (!voziloOptional.isPresent()) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		RezervacijaRentACar rez = new RezervacijaRentACar();
		
		rez.setId(rezDTO.getId());
		rez.setDatumRez(rezDTO.getDatumRez());
		rez.setDatumPreuz (rezDTO.getDatumPreuz());
		rez.setDatumVracanja (rezDTO.getDatumVracanja());
		rez.setCena (rezDTO.getCena());
		rez.setRezervacija(filijalaOptional.get());
		rez.setVozilo(voziloOptional.get());
		
		rez = rezService.save(rez);

		return new ResponseEntity<>(new RezervacijaRentACarDTO(rez),HttpStatus.CREATED);
		
	}
	
	@RequestMapping(value="/{id}",method=RequestMethod.GET)
	public ResponseEntity<RezervacijaRentACarDTO> getRezervacija(@PathVariable Long id){
		
		Optional<RezervacijaRentACar> rezOptional  = rezService.findOne(id);
		
		if (!rezOptional .isPresent()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<>(new RezervacijaRentACarDTO(rezOptional.get()),HttpStatus.OK);
		
	}
	
	@RequestMapping(value="/{id}",method=RequestMethod.DELETE)
	public ResponseEntity<Void> deleteRezervacija(@PathVariable Long id){
		
		Optional<RezervacijaRentACar> rezOptional  = rezService.findOne(id);
		
		if (rezOptional .isPresent()) {
			rezService.remove(id);
			return new ResponseEntity<>(HttpStatus.OK);
		}else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
	}
	

}
