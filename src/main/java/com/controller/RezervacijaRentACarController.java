package com.controller;

import java.security.Principal;
import java.util.Calendar;
import java.util.Date;
import java.util.Optional;

import com.dto.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.model.Filijala;
import com.model.RezervacijaRentACar;
import com.model.StatusRes;
import com.model.Vozilo;
import com.model.user.User;
import com.repository.UserRepository;
import com.security.ResponseMessage;
import com.service.FilijalaService;
import com.service.RezervacijaRentACarService;
import com.service.UserService;
import com.service.VoziloService;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping(value="api/rezervacijarent")
public class RezervacijaRentACarController {
	
	@Autowired
	private  RezervacijaRentACarService rezService;

	
	@RequestMapping(method=RequestMethod.POST,consumes="application/json")
	public ResponseEntity<RezervacijaDTO> saveRezervacija (@RequestBody RezervacijaRentACarDTO rezDTO, Principal user){

		return new ResponseEntity<>(rezService.saveRentReservation(rezDTO,user),HttpStatus.CREATED);
	}
	
	@RequestMapping(value="/{id}",method=RequestMethod.GET)
	public ResponseEntity<RezervacijaRentACarDTO> getRezervacija(@PathVariable Long id){
		
		return new ResponseEntity<>(rezService.getOne(id),HttpStatus.OK);
		
	}

	@RequestMapping(value="/cancel/{id}",method=RequestMethod.GET)
	public ResponseEntity<?> cancelStatus(@PathVariable Long id){

		return new ResponseEntity<>(rezService.cancelStatus(id),HttpStatus.OK);
		
	}


	@RequestMapping(value="/cancel",method=RequestMethod.PUT)
	public ResponseEntity<?> cancel(@RequestBody RezervacijaRentACarDTO rezDTO){
		
		return new ResponseEntity<>(rezService.cancelRes(rezDTO),HttpStatus.OK) ;

	}


	

}
