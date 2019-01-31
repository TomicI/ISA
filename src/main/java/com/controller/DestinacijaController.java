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

import com.dto.DestinacijaDTO;
import com.model.Destinacija;
import com.service.DestinacijaService;



@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping(value="api/destinacija")
public class DestinacijaController {
	@Autowired
	private DestinacijaService as;

	 @RequestMapping(value="/lista",method=RequestMethod.GET)
	 public ResponseEntity<List<DestinacijaDTO>> getAll(){

	 List<Destinacija> destinacija = as.findAll();
	 List<DestinacijaDTO> destinacijaDTO = new ArrayList<>();
		for (Destinacija s : destinacija) {
			destinacijaDTO.add(new DestinacijaDTO(s));
		}
		
		return new ResponseEntity<List<DestinacijaDTO>>(destinacijaDTO, HttpStatus.OK);
 }
 
 @RequestMapping(value="/{id}",method = RequestMethod.GET)
 public ResponseEntity<DestinacijaDTO> getDestinacija(@PathVariable Long id){

 Optional<Destinacija> destinacija = as.findById(id);

	 if (destinacija.isPresent()) {	
		 return new ResponseEntity<>(new DestinacijaDTO(destinacija.get()),HttpStatus.OK);
	 }else {
		 return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	 }

 }
 
 @RequestMapping(method=RequestMethod.POST, consumes="application/json")
	public ResponseEntity<DestinacijaDTO> saveDestinacija(@RequestBody DestinacijaDTO destinacijaDTO){
		
	 Destinacija destinacija = new Destinacija();
	 destinacija.setNazivDestinacije(destinacijaDTO.getNazivDestinacije());
	
	 destinacija = as.save(destinacija);
		return new ResponseEntity<>(new DestinacijaDTO(destinacija), HttpStatus.CREATED);	
	}
 
 @RequestMapping(method=RequestMethod.PUT, consumes="application/json")
	public ResponseEntity<DestinacijaDTO> updateDestinacija(@RequestBody DestinacijaDTO destinacijaDTO){
		
		//a course must exist
	 Optional<Destinacija> destinacija = as.findById(destinacijaDTO.getId()); 
		
		if (destinacija == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		

		 destinacija.get().setNazivDestinacije(destinacijaDTO.getNazivDestinacije());
		
		return new ResponseEntity<>(new DestinacijaDTO(as.save(destinacija.get())), HttpStatus.OK);	
	}
 
 @RequestMapping(value="/{id}", method=RequestMethod.DELETE)
	public ResponseEntity<Void> deleteDestinacija(@PathVariable Long id){
		
	 Optional<Destinacija> destinacija = as.findById(id);
		
		if (destinacija != null){
			as.remove(id);
			return new ResponseEntity<>(HttpStatus.OK);
		} else {		
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
}
