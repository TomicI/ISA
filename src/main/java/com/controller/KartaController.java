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

import com.dto.KartaDTO;
import com.model.Karta;
import com.service.KartaService;
@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping(value = "api/karta")
public class KartaController {
	@Autowired
	private KartaService as;

	 @RequestMapping(value="/lista",method=RequestMethod.GET)
	 public ResponseEntity<List<KartaDTO>> getAll(){

	 List<Karta> karta = as.findAll(); 
	 List<KartaDTO> kartaDTO = new ArrayList<>();
		for (Karta s : karta) {
			kartaDTO.add(new KartaDTO(s));
		}
		
		return new ResponseEntity<List<KartaDTO>>(kartaDTO, HttpStatus.OK);
 }
 
 @RequestMapping(value="/{id}",method = RequestMethod.GET)
 public ResponseEntity<KartaDTO> getKarta(@PathVariable Long id){

 Optional<Karta> karta = as.findById(id);

	 if (karta.isPresent()) {	
		 return new ResponseEntity<>(new KartaDTO(karta.get()),HttpStatus.OK);
	 }else {
		 return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	 }

 }
 
 @RequestMapping(method=RequestMethod.POST, consumes="application/json")
	public ResponseEntity<KartaDTO> saveKarta(@RequestBody KartaDTO kartaDTO){
		
	 Karta karta = new Karta();
	 karta.setDatumVremeP(kartaDTO.getDatumVremeP());
	 karta.setDatumVremeS(kartaDTO.getDatumVremeS());
	 karta.setProsecnaOcena(kartaDTO.getProsecnaOcena());
	
	 karta = as.save(karta);
		return new ResponseEntity<>(new KartaDTO(karta), HttpStatus.CREATED);	
	}
 
 @RequestMapping(method=RequestMethod.PUT, consumes="application/json")
	public ResponseEntity<KartaDTO> updateKarta(@RequestBody KartaDTO kartaDTO){
		
		//a course must exist
	 Optional<Karta> karta = as.findById(kartaDTO.getId()); 
		
		if (karta == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		

		karta.get().setDatumVremeP(kartaDTO.getDatumVremeP());
		 karta.get().setDatumVremeS(kartaDTO.getDatumVremeS());
		 karta.get().setProsecnaOcena(kartaDTO.getProsecnaOcena());
		return new ResponseEntity<>(new KartaDTO(as.save(karta.get())), HttpStatus.OK);	
	}
 
 @RequestMapping(value="/{id}", method=RequestMethod.DELETE)
	public ResponseEntity<Void> deleteKarta(@PathVariable Long id){
		
	 Optional<Karta> karta = as.findById(id);
		
		if (karta != null){
			as.remove(id);
			return new ResponseEntity<>(HttpStatus.OK);
		} else {		
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
}
