package com.controller;

import java.security.Principal;
import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.dto.FilijalaDTO;
import com.dto.RezervacijaRentACarDTO;
import com.dto.VoziloDTO;
import com.model.Filijala;
import com.model.RentACar;
import com.model.RezervacijaRentACar;
import com.model.Vozilo;
import com.model.user.User;
import com.service.FilijalaService;
import com.service.RentACarService;
import com.service.UserService;
import com.sun.mail.iap.Response;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping(value="api/filijala")
public class FilijalaController {
	
	@Autowired
	private FilijalaService filijalaService;

	@RequestMapping(value="/all" , method=RequestMethod.GET,produces ="application/json" )
	public ResponseEntity<List<FilijalaDTO>> getAll(){

		return new ResponseEntity<>(filijalaService.getAll(),HttpStatus.OK);
	}


	@RequestMapping(method = RequestMethod.POST, consumes = "application/json")
	@PreAuthorize("hasRole('ADMIN_RENT')")
	public ResponseEntity<FilijalaDTO> insertFilijala(@RequestBody FilijalaDTO filijalaDTO){

		return new ResponseEntity<>(new FilijalaDTO(filijalaService.insert(filijalaDTO)),HttpStatus.CREATED);
		
	}
	
	@RequestMapping(value="/{id}/vozila",method=RequestMethod.GET)
	public ResponseEntity<List<VoziloDTO>> getVozila(@PathVariable Long id){
		return new ResponseEntity<>(filijalaService.findOneVeh(id),HttpStatus.OK);
	}
	
	
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	@PreAuthorize("hasRole('ADMIN_RENT')")
	public ResponseEntity<Void> deleteFilijala(@PathVariable Long id) {

		Optional<Filijala> filijalaOptional = filijalaService.findOne(id);
		
		if (filijalaOptional.isPresent()) {
			filijalaService.remove(id);
			return new ResponseEntity<>(HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@RequestMapping(method=RequestMethod.PUT,consumes = "application/json" )
	@PreAuthorize("hasRole('ADMIN_RENT')")
	public ResponseEntity<FilijalaDTO> updateFilijala(@RequestBody FilijalaDTO filijalaDTO){

		return new ResponseEntity<>(new FilijalaDTO(filijalaService.edit(filijalaDTO)),HttpStatus.OK);
	}



	@RequestMapping(value="/income",method=RequestMethod.GET)
	public ResponseEntity<?> getIncome(@RequestParam(value = "from") Date from,
									   @RequestParam(value = "to") Date to){
		return new ResponseEntity<>(filijalaService.getIncome(from,to),HttpStatus.OK);
	}

	@RequestMapping(value="/dailyVeh",method=RequestMethod.GET)
	public ResponseEntity<?> getDailyVeh(@RequestParam(value = "pick") Date pick,
									   @RequestParam(value = "opt") int opt){
		return new ResponseEntity<>(filijalaService.getDailyVeh(pick,opt),HttpStatus.OK);
	}
	
	
	
	

}
