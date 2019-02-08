package com.controller;

import java.util.ArrayList;
import java.util.List;
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

import com.dto.PrtljagDTO;
import com.dto.RezervacijaDTO;
import com.model.Prtljag;
import com.model.Rezervacija;
import com.service.RezervacijaService;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping(value="api/rezervacijaAvio")
public class RezervacijaController {
	@Autowired
	private RezervacijaService as;

	 @RequestMapping(value="/lista",method=RequestMethod.GET)
	 public ResponseEntity<List<RezervacijaDTO>> getAll(){

	 List<Rezervacija> rezervacija = as.findAll();
	 List<RezervacijaDTO> rezervacijaDTO = new ArrayList<>();
		for (Rezervacija s : rezervacija) {
			rezervacijaDTO.add(new RezervacijaDTO(s));
		}
		
		return new ResponseEntity<List<RezervacijaDTO>>(rezervacijaDTO, HttpStatus.OK);
 }
 
 @RequestMapping(value="/{id}",method = RequestMethod.GET)
 public ResponseEntity<RezervacijaDTO> getRezervacija(@PathVariable Long id){

 Optional<Rezervacija> rezervacija = as.findById(id);

	 if (rezervacija.isPresent()) {	
		 return new ResponseEntity<>(new RezervacijaDTO(rezervacija.get()),HttpStatus.OK);
	 }else {
		 return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	 }

 }
 
 @RequestMapping(method=RequestMethod.POST, consumes="application/json")
	public ResponseEntity<RezervacijaDTO> saveRezervacija(@RequestBody RezervacijaDTO rezervacijaDTO){
		
	 Rezervacija rezervacija = new Rezervacija();
	 rezervacija.setDatumVremeP(rezervacijaDTO.getDatumVremeP());
	 rezervacija.setDatumVremeS(rezervacijaDTO.getDatumVremeS());
	 
	 rezervacija = as.save(rezervacija);
		return new ResponseEntity<>(new RezervacijaDTO(rezervacija), HttpStatus.CREATED);	
	}
 
 @RequestMapping(method=RequestMethod.PUT, consumes="application/json")
	public ResponseEntity<RezervacijaDTO> updateRezervacija(@RequestBody RezervacijaDTO rezervacijaDTO){
		
		//a course must exist
	 Optional<Rezervacija> rezervacija = as.findById(rezervacijaDTO.getId()); 
		
		if (rezervacija == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		rezervacija.get().setDatumVremeP(rezervacijaDTO.getDatumVremeP());
		rezervacija.get().setDatumVremeS(rezervacijaDTO.getDatumVremeS());

		return new ResponseEntity<>(new RezervacijaDTO(as.save(rezervacija.get())), HttpStatus.OK);	
	}
 
 @RequestMapping(value="/{id}", method=RequestMethod.DELETE)
	public ResponseEntity<Void> deleteRezervacija(@PathVariable Long id){
		
	 Optional<Rezervacija> rezervacija = as.findById(id);
		
		if (rezervacija != null){
			as.remove(id);
			return new ResponseEntity<>(HttpStatus.OK);
		} else {		
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
}