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

import com.dto.CenovnikAvioDTO;
import com.model.CenovnikAvio;
import com.service.CenovnikAvioService;


@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping(value="api/cenovnikAvio")
public class CenovnikAvioController {
	@Autowired
	private CenovnikAvioService as;

	 @RequestMapping(value="/lista",method=RequestMethod.GET)
	 public ResponseEntity<List<CenovnikAvioDTO>> getAll(){

	 List<CenovnikAvio> cenovnik = as.findAll();
	 List<CenovnikAvioDTO> cenovnikDTO = new ArrayList<>();
		for (CenovnikAvio s : cenovnik) {
			cenovnikDTO.add(new CenovnikAvioDTO(s));
		}
		
		return new ResponseEntity<List<CenovnikAvioDTO>>(cenovnikDTO, HttpStatus.OK);
 }
 
 @RequestMapping(value="/{id}",method = RequestMethod.GET)
 public ResponseEntity<CenovnikAvioDTO> getCenovnikAvio(@PathVariable Long id){

 Optional<CenovnikAvio> cenovnik = as.findById(id);

	 if (cenovnik.isPresent()) {	
		 return new ResponseEntity<>(new CenovnikAvioDTO(cenovnik.get()),HttpStatus.OK);
	 }else {
		 return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	 }

 }
 
 @RequestMapping(method=RequestMethod.POST, consumes="application/json")
	public ResponseEntity<CenovnikAvioDTO> saveCenovnikAvio(@RequestBody CenovnikAvioDTO cenovnikDTO){
		
	 CenovnikAvio cenovnik = new CenovnikAvio();
	 cenovnik.setCena(cenovnikDTO.getCena());
	 cenovnik.setDoDatuma(cenovnikDTO.getDoDatuma());
	 cenovnik.setOdDatuma(cenovnikDTO.getOdDatuma());
	 cenovnik.setPopust(cenovnikDTO.getPopust());
	
	 cenovnik = as.save(cenovnik);
		return new ResponseEntity<>(new CenovnikAvioDTO(cenovnik), HttpStatus.CREATED);	
	}
 
 @RequestMapping(method=RequestMethod.PUT, consumes="application/json")
	public ResponseEntity<CenovnikAvioDTO> updateCenovnikAvio(@RequestBody CenovnikAvioDTO cenovnikDTO){
		
		//a course must exist
	 Optional<CenovnikAvio> cenovnik = as.findById(cenovnikDTO.getId()); 
		
		if (cenovnik == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		cenovnik.get().setCena(cenovnikDTO.getCena());
		cenovnik.get().setDoDatuma(cenovnikDTO.getDoDatuma());
		cenovnik.get().setOdDatuma(cenovnikDTO.getOdDatuma());
		cenovnik.get().setPopust(cenovnikDTO.getPopust());
		
		return new ResponseEntity<>(new CenovnikAvioDTO(as.save(cenovnik.get())), HttpStatus.OK);	
	}
 
 @RequestMapping(value="/{id}", method=RequestMethod.DELETE)
	public ResponseEntity<Void> deleteCenovnikAvio(@PathVariable Long id){
		
	 Optional<CenovnikAvio> cenovnik = as.findById(id);
		
		if (cenovnik != null){
			as.remove(id);
			return new ResponseEntity<>(HttpStatus.OK);
		} else {		
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
 
 
}
