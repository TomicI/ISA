package com.controller;

import java.util.Date;
import java.util.Optional;

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

import com.dto.CenovnikRentACarDTO;
import com.model.CenovnikRentACar;
import com.model.Filijala;
import com.model.RentACar;
import com.model.Vozilo;
import com.service.CenovnikRentACarService;
import com.service.RentACarService;
import com.service.VoziloService;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping(value = "api/cenovnikrent")
public class CenovnikRentACarController {

	@Autowired
	private CenovnikRentACarService cenovnikService;



	@RequestMapping(method=RequestMethod.POST,consumes="application/json")
	@PreAuthorize("hasRole('ADMIN_RENT')")
	public ResponseEntity<CenovnikRentACarDTO> saveCenovnik(@RequestBody CenovnikRentACarDTO cenovnikDTO){
		
		return new ResponseEntity<>(new CenovnikRentACarDTO(cenovnikService.insertCenovnik(cenovnikDTO)),HttpStatus.CREATED);
		
	}


	@RequestMapping(method=RequestMethod.PUT,consumes="application/json")
	@PreAuthorize("hasRole('ADMIN_RENT')")
	public ResponseEntity<CenovnikRentACarDTO> editCenovnik(@RequestBody CenovnikRentACarDTO cenovnikDTO){

		return new ResponseEntity<>(cenovnikService.edit(cenovnikDTO),HttpStatus.OK);
	}


	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<CenovnikRentACarDTO> getCenovnik(@PathVariable Long id) {

		return new ResponseEntity<>(new CenovnikRentACarDTO(cenovnikService.getOne(id)), HttpStatus.OK);
	}

	@RequestMapping(value = "/veh/{id}", method = RequestMethod.GET)
	public ResponseEntity<Date> getDate(@PathVariable Long id) {

		return new ResponseEntity<>(cenovnikService.getDate(id), HttpStatus.OK);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	@PreAuthorize("hasRole('ADMIN_RENT')")
	public ResponseEntity<?> deleteCenovnik(@PathVariable Long id) {

		cenovnikService.deleteCenovnik(id);
		return new  ResponseEntity<>(HttpStatus.OK);

	}

	




}
