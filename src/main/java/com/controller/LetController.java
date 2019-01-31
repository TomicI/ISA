package com.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.dto.LetDTO;
import com.model.Karta;
import com.model.Let;
import com.model.Sediste;
import com.service.LetService;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping(value="api/let")
public class LetController {
	@Autowired
	private LetService as;

	 @RequestMapping(value="/lista",method=RequestMethod.GET)
	 public ResponseEntity<List<LetDTO>> getAll(){

	 List<Let> let = as.findAll();
	 List<LetDTO> letlDTO = new ArrayList<>();
		for (Let s : let) {
			letlDTO.add(new LetDTO(s));
		}
		
		return new ResponseEntity<List<LetDTO>>(letlDTO, HttpStatus.OK);
 }
 
 @RequestMapping(value="/{id}",method = RequestMethod.GET)
 public ResponseEntity<LetDTO> getLet(@PathVariable Long id){

 Optional<Let> let = as.findById(id);

	 if (let.isPresent()) {	
		 return new ResponseEntity<>(new LetDTO(let.get()),HttpStatus.OK);
	 }else {
		 return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	 }

 }
 
 @RequestMapping(method=RequestMethod.POST, consumes="application/json")
	public ResponseEntity<LetDTO> saveLet(@RequestBody LetDTO letDTO){
		
	 Let let = new Let();
	 let.setSedista((Set<Sediste>) letDTO.getSedista());
	let.setLet((Set<Karta>) letDTO.getKarte());
	 let = as.save(let);
		return new ResponseEntity<>(new LetDTO(let), HttpStatus.CREATED);	
	}
 
 @RequestMapping(method=RequestMethod.PUT, consumes="application/json")
	public ResponseEntity<LetDTO> updateLet(@RequestBody LetDTO letDTO){
		
		//a course must exist
	 Optional<Let> let = as.findById(letDTO.getId()); 
		
		if (let == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		let.get().setSedista((Set<Sediste>) letDTO.getSedista());
		let.get().setLet((Set<Karta>) letDTO.getKarte());

		return new ResponseEntity<>(new LetDTO(as.save(let.get())), HttpStatus.OK);	
	}
 
 @RequestMapping(value="/{id}", method=RequestMethod.DELETE)
	public ResponseEntity<Void> deleteLet(@PathVariable Long id){
		
	 Optional<Let> let = as.findById(id);
		
		if (let != null){
			as.remove(id);
			return new ResponseEntity<>(HttpStatus.OK);
		} else {		
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
}
