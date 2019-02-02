package com.controller;

import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
import com.dto.LetDTO;
import com.model.Aerodrom;
import com.model.Aviokompanija;
import com.model.Let;
import com.service.AerodromService;
import com.service.AviokompanijaService;
import com.service.LetService;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping(value="api/let")
public class LetController {
	@Autowired
	private LetService as;
	@Autowired
	private AerodromService aes;
	@Autowired
	private AviokompanijaService avs;
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
	System.out.println(" aerodrom p " + letDTO.getAerodromP());
	System.out.println(" aerodrom s " + letDTO.getAerodromS());
	System.out.println(" v p " + letDTO.getVremeP());
	System.out.println(" v s " + letDTO.getVremeS());
	 Let let = new Let();
	 LetDTO letP = new LetDTO();
	 Optional<Aerodrom> a=aes.findById(letDTO.getAerodromP().getId());
	 Optional<Aerodrom> s=aes.findById(letDTO.getAerodromS().getId());
	 Optional<Aviokompanija> av=avs.findById(letDTO.getAviokompanijaID());
	 System.out.println("ima presedanje " + letDTO.getImaPresedanje());
	 
			 
	 
	 let.setAerodromP(a.get());
	 let.setAerodromS(s.get());
	 let.setBrojSedista(letDTO.getBrojSedista());
	 let.setProsecnaOcena(letDTO.getProsecnaOcena());
	 
	 let.setVremePutovanja(letDTO.getVremePutovanja());
	 let.setDuzinaPutovanja(letDTO.getDuzinaPutovanja());
	 let.setImaPresedanje(letDTO.getImaPresedanje());
	 String st = letDTO.getVremeP();
	 SimpleDateFormat sdf = new SimpleDateFormat("HH:MM");
	 long ms;
	try {
		ms = sdf.parse(st).getTime();
		Time t = new Time(ms);
		let.setVremeP(t);
	} catch (ParseException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	 
	 String st1 = letDTO.getVremeS();
	 SimpleDateFormat sdf1 = new SimpleDateFormat("HH:MM");
	 long ms1;
	try {
		ms1 = sdf.parse(st1).getTime();
		Time t1 = new Time(ms1);
		let.setVremeS(t1);
	} catch (ParseException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	 
	 
	 
	 let.setDatumP(letDTO.getDatumP());
	 let.setDatumS(letDTO.getDatumS());
	 let.setAviokompanija(av.get());
	 let=as.save(let);
	 LetDTO pom=new LetDTO(let);
	 
	 
	return new ResponseEntity<>(pom, HttpStatus.CREATED);	
	}
 
 @RequestMapping(method=RequestMethod.PUT, consumes="application/json")
	public ResponseEntity<LetDTO> updateLet(@RequestBody LetDTO letDTO){
		
		//a course must exist
	 Optional<Let> let = as.findById(letDTO.getId()); 
		
		if (let == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		Optional<Aerodrom> a=aes.findById(letDTO.getAerodromP().getId());
		 Optional<Aerodrom> s=aes.findById(letDTO.getAerodromS().getId());
		 Optional<Aviokompanija> av=avs.findById(letDTO.getAviokompanijaID());
		 
		 let.get().setAerodromP(a.get());
		 let.get().setAerodromS(s.get());
		 let.get().setBrojSedista(letDTO.getBrojSedista());
		 let.get().setProsecnaOcena(letDTO.getProsecnaOcena());
		 let.get().setVremeP(java.sql.Time.valueOf(letDTO.getVremeP()));
		 let.get().setVremeS(java.sql.Time.valueOf(letDTO.getVremeS()));
		 let.get().setDatumP(letDTO.getDatumP());
		 let.get().setDatumS(letDTO.getDatumS());
		 let.get().setAviokompanija(av.get());
		
		 let.get().setVremePutovanja(letDTO.getVremePutovanja());
		 let.get().setDuzinaPutovanja(letDTO.getDuzinaPutovanja());
		 let.get().setImaPresedanje(letDTO.getImaPresedanje());
		 LetDTO pom=new LetDTO(as.save(let.get()));
		 
		return new ResponseEntity<>(pom, HttpStatus.OK);	
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
 	
 	@RequestMapping(value="/letovi/{id}",method=RequestMethod.GET)
	 public ResponseEntity<List<LetDTO>> getAaviokompanija(@PathVariable Long id){
 		System.out.println(" letovi" + id);
	 List<Let> let = as.findAll();
	 List<LetDTO> letlDTO = new ArrayList<>();
		for (Let s : let) {
			System.out.println("ID" + s.getAviokompanija().getId());
			if(id.equals(s.getAviokompanija().getId())) {
				
				letlDTO.add(new LetDTO(s));
			}
		}
		
		return new ResponseEntity<List<LetDTO>>(letlDTO, HttpStatus.OK);
 	}
}
