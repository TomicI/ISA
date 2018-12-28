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
import com.model.Prtljag;
import com.service.PrtljagService;


@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping(value="api/prtljag")
public class PrtljagController {
	@Autowired
	private PrtljagService as;

	 @RequestMapping(value="/lista",method=RequestMethod.GET)
	 public ResponseEntity<List<PrtljagDTO>> getAll(){

	 List<Prtljag> prtljag = as.findAll();
	 List<PrtljagDTO> prtljagDTO = new ArrayList<>();
		for (Prtljag s : prtljag) {
			prtljagDTO.add(new PrtljagDTO(s));
		}
		
		return new ResponseEntity<List<PrtljagDTO>>(prtljagDTO, HttpStatus.OK);
 }
 
 @RequestMapping(value="/{id}",method = RequestMethod.GET)
 public ResponseEntity<PrtljagDTO> getPrtljag(@PathVariable Long id){

 Optional<Prtljag> prtljag = as.findById(id);

	 if (prtljag.isPresent()) {	
		 return new ResponseEntity<>(new PrtljagDTO(prtljag.get()),HttpStatus.OK);
	 }else {
		 return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	 }

 }
 
 @RequestMapping(method=RequestMethod.POST, consumes="application/json")
	public ResponseEntity<PrtljagDTO> savePrtljag(@RequestBody PrtljagDTO prtljagDTO){
		
	 Prtljag prtljag = new Prtljag();
	 prtljag.setCena(prtljagDTO.getCena());
	 prtljag.setDuzina(prtljagDTO.getCena());
	 prtljag.setSirina(prtljagDTO.getSirina());
	 prtljag.setTezina(prtljagDTO.getTezina());
	 
	 prtljag = as.save(prtljag);
		return new ResponseEntity<>(new PrtljagDTO(prtljag), HttpStatus.CREATED);	
	}
 
 @RequestMapping(method=RequestMethod.PUT, consumes="application/json")
	public ResponseEntity<PrtljagDTO> updatePrtljag(@RequestBody PrtljagDTO prtljagDTO){
		
		//a course must exist
	 Optional<Prtljag> prtljag = as.findById(prtljagDTO.getId()); 
		
		if (prtljag == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		prtljag.get().setCena(prtljagDTO.getCena());
		 prtljag.get().setDuzina(prtljagDTO.getCena());
		 prtljag.get().setSirina(prtljagDTO.getSirina());
		 prtljag.get().setTezina(prtljagDTO.getTezina());
		return new ResponseEntity<>(new PrtljagDTO(as.save(prtljag.get())), HttpStatus.OK);	
	}
 
 @RequestMapping(value="/{id}", method=RequestMethod.DELETE)
	public ResponseEntity<Void> deletePrtljag(@PathVariable Long id){
		
	 Optional<Prtljag> prtljag = as.findById(id);
		
		if (prtljag != null){
			as.remove(id);
			return new ResponseEntity<>(HttpStatus.OK);
		} else {		
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
}
