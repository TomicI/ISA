package com.controller;

import java.security.Principal;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import com.dto.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

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

	@RequestMapping(value="/updateStatus",method=RequestMethod.GET)
	public ResponseEntity<RezervacijaRentACarDTO> updateStatus(@RequestBody RezervacijaRentACarDTO rezervacijaRentACarDTO){

		return new ResponseEntity<>(rezService.changeStatus(rezervacijaRentACarDTO),HttpStatus.OK);

	}


	@RequestMapping(value="/resAdmin",method=RequestMethod.GET)
	@PreAuthorize("hasRole('ADMIN_RENT')")
	public ResponseEntity<List<RezervacijaRentACarDTO>> getAllAdmin(@RequestParam(value="res")boolean res,Principal username){

		return new ResponseEntity<>(rezService.getAllAdmin(res,username),HttpStatus.OK);

	}


	@RequestMapping(value="/allDeals",method=RequestMethod.GET)
	public ResponseEntity<List<RezervacijaRentACarDTO>> getAllDeals(@RequestParam(value="rentId")Long rentId,
																	@RequestParam(value="pickUp")Date pickUp,
																	@RequestParam(value="dropOff")Date dropOff){

		return new ResponseEntity<>(rezService.getDealRes(rentId,pickUp,dropOff),HttpStatus.OK);
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
