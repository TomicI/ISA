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

import com.dto.AerodromDTO;
import com.dto.DestinacijaDTO;
import com.model.Aerodrom;
import com.model.Destinacija;
import com.service.AerodromService;
import com.service.DestinacijaService;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping(value="api/aerodrom")
public class AerodromController {
	@Autowired
	private AerodromService as;
	
	@Autowired
	private DestinacijaService ds;

	 @RequestMapping(value="/lista",method=RequestMethod.GET)
	 public ResponseEntity<List<AerodromDTO>> getAll(){

			System.out.println( " FAC " );
	 List<Aerodrom> aerodrom = as.findAll();
	 List<AerodromDTO> aerodromDTO = new ArrayList<>();
		for (Aerodrom s : aerodrom) {
			System.out.println("Dodaje a " + s.getNazivAerodroma());
			aerodromDTO.add(new AerodromDTO(s));
		}
		
		return new ResponseEntity<List<AerodromDTO>>(aerodromDTO, HttpStatus.OK);
 }
 
 @RequestMapping(value="/{id}",method = RequestMethod.GET)
 public ResponseEntity<AerodromDTO> getAerodrom(@PathVariable Long id){

	 System.out.println(" GAC " + id);
 Optional<Aerodrom> aerodrom = as.findById(id);
	 if (aerodrom.isPresent()) {	
		 return new ResponseEntity<>(new AerodromDTO(aerodrom.get()),HttpStatus.OK);
	 }else {
		 return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	 }

 }
 
 @RequestMapping(method=RequestMethod.POST, consumes="application/json")
	public ResponseEntity<AerodromDTO> saveAerodrom(@RequestBody AerodromDTO avioDTO){
		
	 Aerodrom aerodrom = new Aerodrom();
	 System.out.println("Naziv aerodroma " + avioDTO.getNazivAerodroma());
	 aerodrom.setNazivAerodroma(avioDTO.getNazivAerodroma());
	Optional<Destinacija> des=ds.findById(avioDTO.getDestinacijaID());
	if(des.isPresent())
		aerodrom.setDestinacija(des.get());
	 aerodrom = as.save(aerodrom);
		return new ResponseEntity<>(new AerodromDTO(aerodrom), HttpStatus.CREATED);	
	}
 
 @RequestMapping(method=RequestMethod.PUT, consumes="application/json")
	public ResponseEntity<AerodromDTO> updateAerodrom(@RequestBody AerodromDTO avioDTO){
		
		//a course must exist
	 Optional<Aerodrom> avio = as.findById(avioDTO.getId()); 
		
		if (avio == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		avio.get().setNazivAerodroma(avioDTO.getNazivAerodroma());
		
		return new ResponseEntity<>(new AerodromDTO(as.save(avio.get())), HttpStatus.OK);	
	}
 
 @RequestMapping(value="/{id}", method=RequestMethod.DELETE)
	public ResponseEntity<Void> deleteAerodrom(@PathVariable Long id){
		
	 Optional<Aerodrom> avio = as.findById(id);
		
		if (avio != null){
			as.remove(id);
			return new ResponseEntity<>(HttpStatus.OK);
		} else {		
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
 

 
}
