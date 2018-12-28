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

import com.dto.VezaAADTO;
import com.model.VezaAA;
import com.service.VezaAAService;


@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping(value="api/vezaAA")
public class VezaAAController {
	@Autowired
	private VezaAAService as;

	 @RequestMapping(value="/lista",method=RequestMethod.GET)
	 public ResponseEntity<List<VezaAADTO>> getAll(){

	 List<VezaAA> veza = as.findAll();
	 List<VezaAADTO> vezaDTO = new ArrayList<>();
		for (VezaAA s : veza) {
			vezaDTO.add(new VezaAADTO(s));
		}
		
		return new ResponseEntity<List<VezaAADTO>>(vezaDTO, HttpStatus.OK);
 }
 
 @RequestMapping(value="/{id}",method = RequestMethod.GET)
 public ResponseEntity<VezaAADTO> getVeza(@PathVariable Long id){

 Optional<VezaAA> veza = as.findById(id);

	 if (veza.isPresent()) {	
		 return new ResponseEntity<>(new VezaAADTO(veza.get()),HttpStatus.OK);
	 }else {
		 return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	 }

 }
 
 @RequestMapping(method=RequestMethod.POST, consumes="application/json")
	public ResponseEntity<VezaAADTO> saveVeza(@RequestBody VezaAADTO vezaDTO){
		
	 VezaAA veza = new VezaAA();
	 veza.setAerodrom(vezaDTO.getAerodrom());
	 veza.setAviokompanija(vezaDTO.getAviokompanija());
	 
	 veza = as.save(veza);
		return new ResponseEntity<>(new VezaAADTO(veza), HttpStatus.CREATED);	
	}
 
 @RequestMapping(method=RequestMethod.PUT, consumes="application/json")
	public ResponseEntity<VezaAADTO> updateVeza(@RequestBody VezaAADTO vezaDTO){
		
		//a course must exist
	 Optional<VezaAA> veza = as.findById(vezaDTO.getId()); 
		
		if (veza == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}

		veza.get().setAerodrom(vezaDTO.getAerodrom());
		veza.get().setAviokompanija(vezaDTO.getAviokompanija());
		return new ResponseEntity<>(new VezaAADTO(as.save(veza.get())), HttpStatus.OK);	
	}
 
 @RequestMapping(value="/{id}", method=RequestMethod.DELETE)
	public ResponseEntity<Void> deleteVeza(@PathVariable Long id){
		
	 Optional<VezaAA> veza = as.findById(id);
		
		if (veza != null){
			as.remove(id);
			return new ResponseEntity<>(HttpStatus.OK);
		} else {		
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
}
