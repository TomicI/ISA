package com.controller;

import java.security.Principal;
import java.util.Calendar;
import java.util.Date;
import java.util.Optional;

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
import com.dto.RezervacijaRentACarDTO;
import com.dto.VoziloDTO;
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
	
	@Autowired
	private  FilijalaService filijalaService;
	
	@Autowired
	private VoziloService voziloService;

	@Autowired
	private UserService userService;
	
	@RequestMapping(method=RequestMethod.POST,consumes="application/json")
	public ResponseEntity<RezervacijaRentACarDTO> saveRezervacija (@RequestBody RezervacijaRentACarDTO rezDTO,Principal user){
		
		 Optional<User> optionalUser = userService.findByUsername(user.getName());
		 
		 if (!optionalUser.isPresent()) {
			 return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		 }
		
		if (rezDTO.getFilijalaDTO()==null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		if (rezDTO.getVoziloDTO()==null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		Optional<Filijala> filijalaOptional = filijalaService.findOne(rezDTO.getFilijalaDTO().getId());
		Optional<Vozilo> voziloOptional = voziloService.findOne(rezDTO.getVoziloDTO().getId());
		
		Optional<Filijala> filijalaDropOptional = filijalaService.findOne(rezDTO.getFilijalaDTO().getId());
		
		if (!filijalaOptional.isPresent()) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		if (!voziloOptional.isPresent()) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		if (!filijalaDropOptional.isPresent()) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		
		RezervacijaRentACar rez = new RezervacijaRentACar();
				
		rez.setDatumRez(new Date());
		rez.setDatumPreuz (rezDTO.getDatumPreuz());
		rez.setDatumVracanja (rezDTO.getDatumVracanja());
		rez.setCena (rezDTO.getCena());
	//	rez.setOtkazana(false);
		rez.setRezervacija(filijalaOptional.get());
	//	rez.setRezervacijaDrop(filijalaDropOptional.get());
	//	rez.setUser(optionalUser.get());
	//	rez.setStatus(StatusRes.Reserved);
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
	public ResponseEntity<Void> deleteRezervacija(@RequestBody Long id){
		
		Optional<RezervacijaRentACar> rezOptional  = rezService.findOne(id);
		
		if (rezOptional .isPresent()) {
			rezService.remove(id);
			return new ResponseEntity<>(HttpStatus.OK);
		}else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
	}

	@RequestMapping(value="/cancel/{id}",method=RequestMethod.GET)
	public ResponseEntity<?> cancelStatus(@PathVariable Long id){
		
		Optional<RezervacijaRentACar> rezOptional  = rezService.findOne(id);
		
		if (!rezOptional .isPresent()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

		Calendar cal = Calendar.getInstance();


		if (( rezOptional.get().getDatumPreuz().getTime() - cal.getTime().getTime() ) <= 0) {
			return new ResponseEntity<>(new ResponseMessage("Reservation can't be canceled , cancelation has to be made 48 h prior to the Pick-up date !"), HttpStatus.BAD_REQUEST);
		}


		return new ResponseEntity<>(new ResponseMessage(" Reservation can be canceled , cancelation has to be made 48 h prior to the Pick-up date !" ), HttpStatus.OK);
		
	}


	@RequestMapping(value="/cancel",method=RequestMethod.PUT)
	public ResponseEntity<?> cancel(@RequestBody RezervacijaRentACarDTO rezDTO){
		
		if (rezDTO.getFilijalaDTO()==null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		if (rezDTO.getVoziloDTO()==null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}

		if (rezDTO.getUserDTO() == null){
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		Optional<Filijala> filijalaOptional = filijalaService.findOne(rezDTO.getFilijalaDTO().getId());
		Optional<Vozilo> voziloOptional = voziloService.findOne(rezDTO.getVoziloDTO().getId());
		Optional<User> userOptional = userService.findOne(rezDTO.getUserDTO().getId());
		
		if (!filijalaOptional.isPresent()) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		if (!voziloOptional.isPresent()) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}

		if (!userOptional.isPresent()) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}	

		Optional<RezervacijaRentACar> rezOptional = rezService.findOne(rezDTO.getId());
		
		Calendar cal = Calendar.getInstance();


		if (( rezOptional.get().getDatumPreuz().getTime() - cal.getTime().getTime() ) <= 0) {
			return new ResponseEntity<>(new ResponseMessage("Reservation can't be canceled , cancelation has to be made 48 h prior to the Pick-up date !"), HttpStatus.BAD_REQUEST);
		}

		RezervacijaRentACar rez = rezOptional.get();

//		rez.setOtkazana(true);

		rez = rezService.save(rez);

		return new ResponseEntity<>(new ResponseMessage("Reservation canceled!"),HttpStatus.OK);

	}


	

}
