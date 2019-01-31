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


import com.dto.AviokompanijaDTO;
import com.dto.VezaAADTO;
import com.model.Aviokompanija;
import com.model.VezaAA;
import com.service.AviokompanijaService;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping(value="api/aviokompanija")
public class AviokompanijaController {
	@Autowired
	private AviokompanijaService avios;
	
	 @RequestMapping(value="/lista",method=RequestMethod.GET)
		 public ResponseEntity<List<AviokompanijaDTO>> getAll(){
	
		 List<Aviokompanija> aviokompanija = avios.findAll();
		 List<AviokompanijaDTO> aviokompanijaDTO = new ArrayList<>();
			for (Aviokompanija s : aviokompanija) {
				aviokompanijaDTO.add(new AviokompanijaDTO(s));
			}
			
			return new ResponseEntity<List<AviokompanijaDTO>>(aviokompanijaDTO, HttpStatus.OK);
	 }
	 
	 @RequestMapping(value="/{id}",method = RequestMethod.GET)
	 public ResponseEntity<AviokompanijaDTO> getAviokompanijas(@PathVariable Long id){

	 Optional<Aviokompanija> aviokompanija = avios.findById(id);

		 if (aviokompanija.isPresent()) {	
			 return new ResponseEntity<>(new AviokompanijaDTO(aviokompanija.get()),HttpStatus.OK);
		 }else {
			 return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		 }

	 }
	 
	 @RequestMapping(method=RequestMethod.POST, consumes="application/json")
		public ResponseEntity<AviokompanijaDTO> saveAviokompanija(@RequestBody AviokompanijaDTO aviokompanijaDTO){
			
		 	Aviokompanija aviokompanija = new Aviokompanija();
		 	aviokompanija.setNaziv(aviokompanijaDTO.getNaziv());
		
		 	aviokompanija = avios.save(aviokompanija);
			return new ResponseEntity<>(new AviokompanijaDTO(aviokompanija), HttpStatus.CREATED);	
		}
	 
	 @RequestMapping(method=RequestMethod.PUT, consumes="application/json")
		public ResponseEntity<AviokompanijaDTO> updateAviokompanija(@RequestBody AviokompanijaDTO avioDTO){
			
			//a course must exist
		 Optional<Aviokompanija> avio = avios.findById(avioDTO.getId()); 
			
			if (avio == null) {
				return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
			}
			
			avio.get().setNaziv(avioDTO.getNaziv());
			avio.get().setAdresa(avioDTO.getAdresa());
			avio.get().setOpis(avioDTO.getOpis());
			avio.get().setProsecnaOcena(avioDTO.getProsecnaOcena());
			
			return new ResponseEntity<>(new AviokompanijaDTO(avios.save(avio.get())), HttpStatus.OK);	
		}
	 
	 @RequestMapping(value="/{id}", method=RequestMethod.DELETE)
		public ResponseEntity<Void> deleteAviokompanija(@PathVariable Long id){
			
		 Optional<Aviokompanija> avio = avios.findById(id);
			
			if (avio != null){
				avios.remove(id);
				return new ResponseEntity<>(HttpStatus.OK);
			} else {		
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
		}
	 
	 
	 
	 @RequestMapping(value = "/{aviokomId}/aerodromi", method = RequestMethod.GET)
		public ResponseEntity<List<VezaAADTO>> getAerodromi(
				@PathVariable Long aviokomId) {
			
			Optional<Aviokompanija> ak = avios.findById(aviokomId);
			Set<VezaAA> veze = ak.get().getVeza();
			List<VezaAADTO> vezeDTO = new ArrayList<>();
			
			for (VezaAA v: veze) {
				VezaAADTO vezaDTO = new VezaAADTO();
				vezaDTO.setId(v.getId());
				vezaDTO.setAerodrom(v.getAerodrom());
				vezaDTO.setAviokompanija(v.getAviokompanija());
			
				vezeDTO.add(vezaDTO);
			}
			
			return new ResponseEntity<>(vezeDTO, HttpStatus.OK);
		}
	 
}
