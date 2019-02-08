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
import com.dto.KartaDTO;
import com.dto.SedisteDTO;
import com.model.CenovnikAvio;
import com.model.DodatneUslugeAvion;
import com.model.Karta;
import com.model.Let;
import com.model.Prtljag;
import com.model.Sediste;
import com.service.CenovnikAvioService;
import com.service.DodatneUslugeAvionService;
import com.service.KartaService;
import com.service.LetService;
import com.service.PrtljagService;
import com.service.SedisteService;
@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping(value = "api/karta")
public class KartaController {
	@Autowired
	private KartaService as;
	@Autowired
	private CenovnikAvioService cs;
	@Autowired
	private DodatneUslugeAvionService ds;
	@Autowired
	private PrtljagService ps;
	@Autowired
	private LetService ls;
	@Autowired
	private SedisteService ss;
	
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
	 if(kartaDTO.getCena()!=null) {
		 Optional<CenovnikAvio> c=cs.findById(kartaDTO.getCena().getId());
		 karta.setCena(c.get());
	 }
	 
	 if(kartaDTO.getLet()!=null) {
		 Optional<Let> l=ls.findById(kartaDTO.getLet().getId());
		 karta.setLet(l.get());
	 }
	 if(kartaDTO.getSediste()!=null) {
		 for(int i=0; i<kartaDTO.getSediste().size(); i++) {
			 
			 Optional <Sediste> d=ss.findById(kartaDTO.getSediste().get(i).getId());
			 karta.getSediste().add(d.get());
			 
		 }
	 }
		 
	 karta.setProsecnaOcena(kartaDTO.getProsecnaOcena());
	 
	 if(kartaDTO.getDodatneUsluge()!=null) {
		 for(int i=0; i<kartaDTO.getDodatneUsluge().size(); i++) {
			 Optional <DodatneUslugeAvion> d=ds.findById(kartaDTO.getDodatneUsluge().get(i).getId());
			 karta.getDodatneUsl().add(d.get());
		 }
	 }
	 
	 if(kartaDTO.getPrtljag()!=null) {
		 for(int i=0; i<kartaDTO.getPrtljag().size(); i++) {
			 Optional <Prtljag> d=ps.findById(kartaDTO.getPrtljag().get(i).getId());
			 karta.getPrtljag().add(d.get());
		 }
	 }
	 karta.setUser(kartaDTO.getUser());
	 
	 
	 karta = as.save(karta);
	 KartaDTO kar=new KartaDTO(karta);
		return new ResponseEntity<>(kar, HttpStatus.CREATED);	
	}
 
 @RequestMapping(method=RequestMethod.PUT, consumes="application/json")
	public ResponseEntity<KartaDTO> updateKarta(@RequestBody KartaDTO kartaDTO){
		
		//a course must exist
	 Optional<Karta> karta = as.findById(kartaDTO.getId()); 
		
		if (karta == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		if(kartaDTO.getCena()!=null) {
			 Optional<CenovnikAvio> c=cs.findById(kartaDTO.getCena().getId());
			 karta.get().setCena(c.get());
		 }
		 
		 if(kartaDTO.getLet()!=null) {
			 Optional<Let> l=ls.findById(kartaDTO.getLet().getId());
			 System.out.println("SEDISTA " + l.get().getSedista().size());
			 karta.get().setLet(l.get());
		 }
		 if(kartaDTO.getSediste()!=null) {
			 
			 karta.get().getSediste().clear();
			 for(int i=0; i<kartaDTO.getSediste().size(); i++) {
				 
				 Optional <Sediste> d=ss.findById(kartaDTO.getSediste().get(i).getId());
				 karta.get().getSediste().add(d.get());
				 
			 }
		 }
			 
		 karta.get().setProsecnaOcena(kartaDTO.getProsecnaOcena());
		 
		 if(kartaDTO.getDodatneUsluge()!=null) {
			 karta.get().getDodatneUsl().clear();
			 for(int i=0; i<kartaDTO.getDodatneUsluge().size(); i++) {
				 Optional <DodatneUslugeAvion> d=ds.findById(kartaDTO.getDodatneUsluge().get(i).getId());
				 karta.get().getDodatneUsl().add(d.get());
			 }
		 }
		 
		 if(kartaDTO.getPrtljag()!=null) {
			 karta.get().getPrtljag().clear();
			 for(int i=0; i<kartaDTO.getPrtljag().size(); i++) {
				 Optional <Prtljag> d=ps.findById(kartaDTO.getPrtljag().get(i).getId());
				 karta.get().getPrtljag().add(d.get());
			 }
		 }
		 karta.get().setUser(kartaDTO.getUser());
		KartaDTO k=new KartaDTO(as.save(karta.get()));
		if(karta.get().getCena()!=null) {
			k.setCena(new CenovnikAvioDTO(karta.get().getCena()));
		}
		if(karta.get().getDodatneUsl()!=null) {
			k.setDodatneUslugeL(karta.get().getDodatneUsl());
		}
		
		if(karta.get().getProsecnaOcena()!=null) {
			k.setProsecnaOcena(karta.get().getProsecnaOcena());
		}
		
		if(karta.get().getPrtljag()!=null) {
			k.setPrtljagL(karta.get().getPrtljag());
		}
		
		if(karta.get().getDodatneUsl()!=null) {
			k.setDodatneUslugeL(karta.get().getDodatneUsl());
		}
		
		if(karta.get().getSediste()!=null) {
			
			for(Sediste s: karta.get().getSediste()) {
				 
				 Optional <Sediste> d=ss.findById(s.getId());
				 k.getSediste().add(new SedisteDTO(d.get()));
				 
			 }
		}
		
		return new ResponseEntity<>(k, HttpStatus.OK);	
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
