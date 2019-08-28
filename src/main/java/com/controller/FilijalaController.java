package com.controller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

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
	
	@Autowired
	private RentACarService rentACarService;
	
	@Autowired
	private UserService userService;
	
	

	@RequestMapping(method = RequestMethod.POST, consumes = "application/json")
	@PreAuthorize("hasRole('ADMIN_RENT')")
	public ResponseEntity<FilijalaDTO> insertFilijala(@RequestBody FilijalaDTO filijalaDTO){
		System.out.println(filijalaDTO);
		
		if (filijalaDTO.getRentACarDTO()==null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		Optional<RentACar> rentACar = rentACarService.findOne(filijalaDTO.getRentACarDTO().getId());
		
		if (!rentACar.isPresent()) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		Filijala filijala = new Filijala();
		filijala.setAdresa(filijalaDTO.getAdresa());
		filijala.setFilijala(rentACar.get());
		
		filijala = filijalaService.save(filijala);
		
		return new ResponseEntity<>(new FilijalaDTO(filijala),HttpStatus.CREATED);
		
	}
	
	@RequestMapping(value="/{id}/vozila",method=RequestMethod.GET)
	public ResponseEntity<List<VoziloDTO>> getVozila(@PathVariable Long id){
		return new ResponseEntity<>(filijalaService.findOneVeh(id),HttpStatus.OK);
	}
	
	
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	@PreAuthorize("hasRole('ADMIN_RENT')")
	public ResponseEntity<Void> deleteFilijala(@PathVariable Long id) {
		
		System.out.println("Brisnjae");
		
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
		
		Optional<Filijala> optFilijala = filijalaService.findOne(filijalaDTO.getId());
		
		if (!optFilijala.isPresent()) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		Filijala filijala = optFilijala.get();
		
		filijala.setAdresa(filijalaDTO.getAdresa());
		
		filijala = filijalaService.save(filijala);
		
		return new ResponseEntity<>(new FilijalaDTO(filijala),HttpStatus.OK);
		
		
	}
	
	@RequestMapping(value = "/rezadmin", method = RequestMethod.GET)
	@PreAuthorize("hasRole('ADMIN_RENT')")
	public ResponseEntity<?> allRez(Principal user) {
		
		Optional<User> optionalUser = userService.findByUsername(user.getName());
		
		RentACar rtemp = optionalUser.get().getRentACar();
		
		Set<Filijala> filLista = rtemp.getFilijale();
		
		List<RezervacijaRentACarDTO> rezList = new ArrayList<RezervacijaRentACarDTO>();
		
		
		
		for (Filijala f : filLista) {
			for(RezervacijaRentACar r : f.getRezervacije()) {
				RezervacijaRentACarDTO reztemp = new RezervacijaRentACarDTO(r);
				rezList.add(reztemp);
			}
		}

		
	
		return new ResponseEntity<>(rezList,HttpStatus.OK);
		
	}
	
	
	
	
	

}
