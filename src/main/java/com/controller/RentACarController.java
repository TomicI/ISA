package com.controller;

import java.util.*;

import com.dto.aviokompanija.OcenaDTO;
import com.model.*;
import com.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.dto.CenovnikRentACarDTO;
import com.dto.FilijalaDTO;
import com.dto.RentACarDTO;
import com.dto.RezervacijaRentACarDTO;
import com.dto.VoziloDTO;
import com.repository.RentACarRepository;
import com.security.ResponseMessage;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping(value = "api/rentacar")
public class RentACarController {

	@Autowired
	private RentACarService rentACarService;
	
	@Autowired
	private FilijalaService filijalaService;


	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<RentACarDTO> getRentACar(@PathVariable Long id) {

		return new ResponseEntity<>(new RentACarDTO(rentACarService.getOne(id)), HttpStatus.OK);
	}

	@RequestMapping(value = "/all", method = RequestMethod.GET)
	public ResponseEntity<List<RentACarDTO>> getAll() {

		return new ResponseEntity<>(rentACarService.getAll(), HttpStatus.OK);
	}

	@RequestMapping(value = "/{id}/filijale", method = RequestMethod.GET)
	public ResponseEntity<List<FilijalaDTO>> getFilijale(@PathVariable Long id) {
		return new ResponseEntity<>(rentACarService.findOneFilijala(id), HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.POST, consumes = "application/json")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<RentACarDTO> saveRentACar(@RequestBody RentACarDTO rentACarDTO) {


		return new ResponseEntity<>(new RentACarDTO(rentACarService.saveRentACar(rentACarDTO)), HttpStatus.CREATED);

	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	@PreAuthorize("hasRole('ADMIN_RENT')")
	public ResponseEntity<?> deleteRentACar(@PathVariable Long id) {

		Optional<RentACar> rentOptional = rentACarService.findOne(id);

		if (rentOptional.isPresent()) {
			rentACarService.remove(id);
			return new ResponseEntity<>(HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

	}

	@RequestMapping(value = "/{id}/cenovnik", method = RequestMethod.GET)
	public ResponseEntity<List<CenovnikRentACarDTO>> getCenovnik(@PathVariable Long id) {


		return new ResponseEntity<>(rentACarService.getCenovnici(id), HttpStatus.OK);

	}

	@RequestMapping(method = RequestMethod.PUT, consumes = "application/json")
	@PreAuthorize("hasRole('ADMIN_RENT')")
	public ResponseEntity<?> edit(@RequestBody RentACarDTO rentACarDTO) {

		return new ResponseEntity<>(new RentACarDTO(rentACarService.edit(rentACarDTO)), HttpStatus.OK);
	}
	

	@RequestMapping(value = "/search", method = RequestMethod.GET)
	public ResponseEntity<?> search(@RequestParam(value="locationp")String locationp,
									@RequestParam(value = "bring") String bring,
									@RequestParam(value = "pickup") Date pickUp,
									@RequestParam(value = "dropoff") Date dropOff,
									@RequestParam(value = "range",required = false) String range,
									@RequestParam(value = "peo",required = false) String peo,
									@RequestParam(value = "gear",required = false) Menjac gear,
									@RequestParam(value = "group" ,required = false) String group
									) {

		System.out.println(pickUp);
		System.out.println(bring);

		return new ResponseEntity<>(filijalaService.search(locationp, bring, pickUp, dropOff,range,peo,gear,group),HttpStatus.OK);

	}

	@RequestMapping(value="/rate",method=RequestMethod.POST,consumes="application/json")
	public ResponseEntity<ResponseMessage> rateFilijala(@RequestBody OcenaDTO ocenaDTO){

		return new ResponseEntity<>(filijalaService.rateFilijala(ocenaDTO),HttpStatus.OK);

	}


	@RequestMapping(value = "/search/{grad}/{drzava}", method = RequestMethod.GET)
	public ResponseEntity<List<RentACarDTO>> getAll(@PathVariable(value = "grad") String grad, @PathVariable(value="drzava") String drzava) {

		return new ResponseEntity<>(rentACarService.findByLokacijaFilijale(grad, drzava), HttpStatus.OK);
	}

}
