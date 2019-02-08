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

import com.dto.SedisteDTO;
import com.model.Sediste;
import com.service.SedisteService;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping(value="api/sedista")
public class SedisteController {
	@Autowired
	private SedisteService as;

	 @RequestMapping(value="/lista",method=RequestMethod.GET)
	 public ResponseEntity<List<SedisteDTO>> getAll(){

	 List<Sediste> sediste = as.findAll();
	 List<SedisteDTO> sedisteDTO = new ArrayList<>();
		for (Sediste s : sediste) {
			sedisteDTO.add(new SedisteDTO(s));
		}
		
		return new ResponseEntity<List<SedisteDTO>>(sedisteDTO, HttpStatus.OK);
 }
 
 @RequestMapping(value="/{id}",method = RequestMethod.GET)
 public ResponseEntity<SedisteDTO> getSediste(@PathVariable Long id){

 Optional<Sediste> sediste = as.findById(id);

	 if (sediste.isPresent()) {	
		 return new ResponseEntity<>(new SedisteDTO(sediste.get()),HttpStatus.OK);
	 }else {
		 return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	 }

 }
 
 @RequestMapping(method=RequestMethod.POST, consumes="application/json")
	public ResponseEntity<SedisteDTO> saveSediste(@RequestBody SedisteDTO sedisteDTO){
		
	 Sediste sediste = new Sediste();
	 sediste.setKolona(sedisteDTO.getKolona());
	 sediste.setRed(sedisteDTO.getRed());
	 sediste.setZauzeto(sedisteDTO.getZauzeto());
	 sediste.setSegment(sedisteDTO.getSegment());
	 sediste = as.save(sediste);
		return new ResponseEntity<>(new SedisteDTO(sediste), HttpStatus.CREATED);	
	}
 
 @RequestMapping(method=RequestMethod.PUT, consumes="application/json")
	public ResponseEntity<SedisteDTO> updateSediste(@RequestBody SedisteDTO sedisteDTO){
		
		//a course must exist
	 Optional<Sediste> sediste = as.findById(sedisteDTO.getId()); 
		
		if (sediste == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}

		sediste.get().setKolona(sedisteDTO.getKolona());
		sediste.get().setRed(sedisteDTO.getRed());
		sediste.get().setZauzeto(sedisteDTO.getZauzeto());
		sediste.get().setSegment(sedisteDTO.getSegment());
		return new ResponseEntity<>(new SedisteDTO(as.save(sediste.get())), HttpStatus.OK);	
	}
 
 @RequestMapping(value="/{id}", method=RequestMethod.DELETE)
	public ResponseEntity<Void> deleteSediste(@PathVariable Long id){
		
	 Optional<Sediste> sediste = as.findById(id);
		
		if (sediste != null){
			as.remove(id);
			return new ResponseEntity<>(HttpStatus.OK);
		} else {		
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
}
