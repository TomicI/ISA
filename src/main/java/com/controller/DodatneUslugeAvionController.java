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

import com.dto.DodatneUslugeAvionDTO;
import com.model.DodatneUslugeAvion;
import com.service.DodatneUslugeAvionService;


@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping(value="api/dodatneUsluge")
public class DodatneUslugeAvionController {
	@Autowired
	private DodatneUslugeAvionService as;

	 @RequestMapping(value="/lista",method=RequestMethod.GET)
	 public ResponseEntity<List<DodatneUslugeAvionDTO>> getAll(){

	 List<DodatneUslugeAvion> dodUsl = as.findAll();
	 List<DodatneUslugeAvionDTO> dodUslDTO = new ArrayList<>();
		for (DodatneUslugeAvion s : dodUsl) {
			dodUslDTO.add(new DodatneUslugeAvionDTO(s));
		}
		
		return new ResponseEntity<List<DodatneUslugeAvionDTO>>(dodUslDTO, HttpStatus.OK);
 }
 
 @RequestMapping(value="/{id}",method = RequestMethod.GET)
 public ResponseEntity<DodatneUslugeAvionDTO> getDosatneUsl(@PathVariable Long id){

 Optional<DodatneUslugeAvion> dodUsl = as.findById(id);

	 if (dodUsl.isPresent()) {	
		 return new ResponseEntity<>(new DodatneUslugeAvionDTO(dodUsl.get()),HttpStatus.OK);
	 }else {
		 return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	 }

 }
 
 @RequestMapping(method=RequestMethod.POST, consumes="application/json")
	public ResponseEntity<DodatneUslugeAvionDTO> saveDodatneUsl(@RequestBody DodatneUslugeAvionDTO dodUslDTO){
		
	 DodatneUslugeAvion dodUsl = new DodatneUslugeAvion();
	 dodUsl.setNazivUsluge(dodUslDTO.getNaziv());
	 dodUsl.setCenaUsluge(dodUslDTO.getCena());
	 dodUsl.setOpisUsluge(dodUslDTO.getOpis());
	
	 dodUsl = as.save(dodUsl);
		return new ResponseEntity<>(new DodatneUslugeAvionDTO(dodUsl), HttpStatus.CREATED);	
	}
 
 @RequestMapping(method=RequestMethod.PUT, consumes="application/json")
	public ResponseEntity<DodatneUslugeAvionDTO> updateDodatneUsl(@RequestBody DodatneUslugeAvionDTO dodUslDTO){
		
		//a course must exist
	 Optional<DodatneUslugeAvion> dodUsl = as.findById(dodUslDTO.getId()); 
		
		if (dodUsl == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		

		 dodUsl.get().setNazivUsluge(dodUslDTO.getNaziv());
		 dodUsl.get().setCenaUsluge(dodUslDTO.getCena());
		 dodUsl.get().setOpisUsluge(dodUslDTO.getOpis());
		return new ResponseEntity<>(new DodatneUslugeAvionDTO(as.save(dodUsl.get())), HttpStatus.OK);	
	}
 
 @RequestMapping(value="/{id}", method=RequestMethod.DELETE)
	public ResponseEntity<Void> deleteDodatneUsl(@PathVariable Long id){
		
	 Optional<DodatneUslugeAvion> dodUsl = as.findById(id);
		
		if (dodUsl != null){
			as.remove(id);
			return new ResponseEntity<>(HttpStatus.OK);
		} else {		
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
}
