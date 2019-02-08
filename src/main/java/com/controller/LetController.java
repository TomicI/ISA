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
import com.dto.SedisteDTO;
import com.model.Aerodrom;
import com.model.Aviokompanija;
import com.model.Let;
import com.model.Sediste;
import com.service.AerodromService;
import com.service.AviokompanijaService;
import com.service.DestinacijaService;
import com.service.LetService;
import com.service.SedisteService;

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
	@Autowired
	private DestinacijaService ds;
	@Autowired
	private SedisteService ss;
	
	
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
		 System.out.println("LET presedanje " + let.get().getPresedanje().size());
		 LetDTO l=new LetDTO(let.get());
		 
		
		 return new ResponseEntity<>(l,HttpStatus.OK);
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
	 Optional<Aerodrom> a=aes.findById(letDTO.getAerodromP().getId());
	 Optional<Aerodrom> s=aes.findById(letDTO.getAerodromS().getId());
	 Optional<Aviokompanija> av=avs.findById(letDTO.getAviokompanijaID().getId());
	 
	 
	for(int k=0; k<letDTO.getBrojSegmenata(); k++) {
		for(int j=0; j<letDTO.getBrojRedova();j++) {
			for(int i=0; i< letDTO.getBrojKolona(); i++) {
				Sediste sed=new Sediste();
				sed.setKolona(i);
				sed.setRed(j);
				sed.setSegment(k);
				sed.setZauzeto(false);
				sed.setSedista(let);
				let.getSedista().add(sed);
			}
		}
	}
	 let.setAerodromP(a.get());
	 let.setAerodromS(s.get());
	 let.setProsecnaOcena(letDTO.getProsecnaOcena());
	 let.setBrojKolona(letDTO.getBrojKolona());
	 let.setBrojRedova(letDTO.getBrojRedova());
	 let.setBrojSegmenata(letDTO.getBrojSegmenata());
	 let.setOpis(letDTO.getOpis());
	 let.setVremePutovanja(letDTO.getVremePutovanja());
	 let.setDuzinaPutovanja(letDTO.getDuzinaPutovanja());
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
		 Optional<Aviokompanija> av=avs.findById(letDTO.getAviokompanijaID().getId());
		
		 
		 if(letDTO.getPresedanje().size()>0) {
			 let.get().getPresedanje().clear();
				for(int i=0; i<letDTO.getPresedanje().size(); i++) {
					
					Optional<Aerodrom> aerodrom=aes.findById(letDTO.getPresedanje().get(i).getId());
					
					let.get().getPresedanje().add(aerodrom.get());
				}
			}
		 if(letDTO.getBrojSegmenata()!=let.get().getBrojSegmenata() || letDTO.getBrojKolona()!=let.get().getBrojKolona() || letDTO.getBrojRedova()!=let.get().getBrojRedova()) {
			 for(int k=0; k<letDTO.getBrojSegmenata(); k++) {
					for(int j=0; j<letDTO.getBrojRedova();j++) {
						for(int i=0; i< letDTO.getBrojKolona(); i++) {
							Sediste sed=new Sediste();
							sed.setKolona(i);
							sed.setRed(j);
							sed.setSegment(k);
							sed.setZauzeto(false);
							sed.setSedista(let.get());
							let.get().getSedista().add(sed);
						}
					}
				}
		 }
			 
		 
		 let.get().setAerodromP(a.get());
		 let.get().setAerodromS(s.get());
		 let.get().setProsecnaOcena(letDTO.getProsecnaOcena());
		 let.get().setVremeP(java.sql.Time.valueOf(letDTO.getVremeP()));
		 let.get().setVremeS(java.sql.Time.valueOf(letDTO.getVremeS()));
		 let.get().setDatumP(letDTO.getDatumP());
		 let.get().setDatumS(letDTO.getDatumS());
		 let.get().setAviokompanija(av.get());
		 let.get().setBrojKolona(letDTO.getBrojKolona());
		 let.get().setBrojRedova(letDTO.getBrojRedova());
		 let.get().setBrojSegmenata(letDTO.getBrojSegmenata());
		 let.get().setOpis(letDTO.getOpis());
		 let.get().setVremePutovanja(letDTO.getVremePutovanja());
		 let.get().setDuzinaPutovanja(letDTO.getDuzinaPutovanja());
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
 	
 	@RequestMapping(value="/sediste",method=RequestMethod.PUT)
	 public ResponseEntity<LetDTO> zauzmiSediste(@RequestBody LetDTO letDTO){
		System.out.println(" letovi" + letDTO.getId());
		Optional<Let> let = as.findById(letDTO.getId()); 
		
		if (let == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		if(letDTO.getSedista()!=null) {
			 if(letDTO.getSedista().size()>0) {
				 	let.get().getSedista().clear();
					for(int i=0; i<letDTO.getSedista().size(); i++) {
						System.out.println(" id sedista " +letDTO.getSedista().get(i).getId());
						Optional<Sediste> sediste=ss.findById(letDTO.getSedista().get(i).getId());
						sediste.get().setZauzeto(letDTO.getSedista().get(i).getZauzeto());
						let.get().getSedista().add(sediste.get());
					}
			}
		 }
		LetDTO pom=new LetDTO(as.save(let.get()));
		return new ResponseEntity<LetDTO>(pom, HttpStatus.OK);
	}
 	
 	@RequestMapping(value="/pretraga",method=RequestMethod.PUT)
	 public ResponseEntity<List<LetDTO>> pretraga(@RequestBody LetDTO pretraga){
		System.out.println(" letovi " + pretraga.getDatumP() + "   " + pretraga.getDatumS()+ "   "+ pretraga.getVremeP() + "   "+pretraga.getVremeS());
		List<LetDTO> letovi=new ArrayList<>();
		List<Let> svi=as.findAll();
		for(Let l: svi) {
			
			if(l.getAerodromP().getNazivAerodroma().equals(pretraga.getVremeP()) && l.getAerodromS().getNazivAerodroma().equals(pretraga.getVremeS()) && l.getDatumP().toString().equals(pretraga.getDatumP().toString()) ) {
				LetDTO le=new LetDTO(l);
				System.out.println(" poklapa se " + l.getId());
				
				letovi.add(le);
			}
		}
		
		return new ResponseEntity<List<LetDTO>>(letovi, HttpStatus.OK);
	}
}
