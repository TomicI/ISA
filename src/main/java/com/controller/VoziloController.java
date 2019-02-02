package com.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.dto.CenovnikRentACarDTO;
import com.dto.VoziloDTO;
import com.model.CenovnikRentACar;
import com.model.Filijala;
import com.model.Vozilo;
import com.service.FilijalaService;
import com.service.VoziloService;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping(value="api/vozilo")
public class VoziloController {
	
	@Autowired
	private VoziloService voziloService;
	
	@Autowired
	private FilijalaService filijalaService;
	
	@RequestMapping(method=RequestMethod.POST,consumes="application/json")
	@PreAuthorize("hasRole('ADMIN_RENT')")
	public ResponseEntity<VoziloDTO> saveVozilo(@RequestBody VoziloDTO voziloDTO){
		
		if (voziloDTO.getFilijalaDTO()==null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		Optional<Filijala> filijalaOptional = filijalaService.findOne(voziloDTO.getFilijalaDTO().getId());
		
		if (!filijalaOptional.isPresent()) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		
		Vozilo vozilo = new Vozilo();
		vozilo.setNaziv(voziloDTO.getNaziv());
		vozilo.setMarka(voziloDTO.getMarka());
		vozilo.setModel(voziloDTO.getModel());
		vozilo.setMenjac(voziloDTO.getMenjac());
		vozilo.setRezervoar(voziloDTO.getRezervoar());
		vozilo.setGorivo(voziloDTO.getGorivo());
		vozilo.setPotrosnja(voziloDTO.getPotrosnja());
		vozilo.setBrojSedista(voziloDTO.getBrojSedista());
		vozilo.setBrojTorbi(voziloDTO.getBrojTorbi());
		vozilo.setBrojVrata(voziloDTO.getBrojVrata());
		vozilo.setDodatniopis(voziloDTO.getDodatniopis());
		vozilo.setKlima(voziloDTO.getKlima());
		vozilo.setProsecnaOcena(0.00);
		vozilo.setVozilo(filijalaOptional.get());
		
		
		vozilo = voziloService.save(vozilo);
		
		return new ResponseEntity<>(new VoziloDTO(vozilo),HttpStatus.CREATED);
		
	}

	@RequestMapping(value="/{id}",method=RequestMethod.GET)
	public ResponseEntity<VoziloDTO> getVozilo(@PathVariable Long id){
		
		Optional<Vozilo> optionalVozilo = voziloService.findOne(id);
		
		if (!optionalVozilo.isPresent()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<>(new VoziloDTO(optionalVozilo.get()),HttpStatus.OK);
		
	}
	
	@RequestMapping(value="/{id}",method=RequestMethod.DELETE)
	@PreAuthorize("hasRole('ADMIN_RENT')")
	public ResponseEntity<Void> deleteVozilo(@PathVariable Long id){
		
		Optional<Vozilo> voziloOptional = voziloService.findOne(id);
		
		if (voziloOptional.isPresent()) {
			voziloService.remove(id);
			return new ResponseEntity<>(HttpStatus.OK);
		}else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
	}
	
	@RequestMapping(value="/{id}/cenovnik",method=RequestMethod.GET)
	public ResponseEntity<List<CenovnikRentACarDTO>> getCenovnik(@PathVariable Long id){
		
		Optional<Vozilo> voziloOptional = voziloService.findOne(id);
		
		if (!voziloOptional.isPresent()) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		Set<CenovnikRentACar> rentACars = voziloOptional.get().getCenovnik();
		
		List<CenovnikRentACarDTO> cenovnikRDTO = new ArrayList<>();
		
		for (CenovnikRentACar c:rentACars) {
			CenovnikRentACarDTO cenovnikDTO = new CenovnikRentACarDTO(c);
			cenovnikRDTO.add(cenovnikDTO);
		}
		
		return new ResponseEntity<>(cenovnikRDTO,HttpStatus.OK);
		
	}	
	
	@RequestMapping(method=RequestMethod.PUT,consumes="application/json")
	@PreAuthorize("hasRole('ADMIN_RENT')")
	public ResponseEntity<VoziloDTO> updateVozilo(@RequestBody VoziloDTO voziloDTO){
		
		Optional<Vozilo> voziloOptional = voziloService.findOne(voziloDTO.getId());
		
		if (!voziloOptional.isPresent()) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		Optional<Filijala> filijalaOptional = filijalaService.findOne(voziloDTO.getFilijalaDTO().getId());
		
		if (!filijalaOptional.isPresent()) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		
		Vozilo vozilo = voziloOptional.get();
		
		vozilo.setNaziv(voziloDTO.getNaziv());
		vozilo.setMarka(voziloDTO.getMarka());
		vozilo.setModel(voziloDTO.getModel());
		vozilo.setMenjac(voziloDTO.getMenjac());
		vozilo.setRezervoar(voziloDTO.getRezervoar());
		vozilo.setGorivo(voziloDTO.getGorivo());
		vozilo.setPotrosnja(voziloDTO.getPotrosnja());
		vozilo.setBrojSedista(voziloDTO.getBrojSedista());
		vozilo.setBrojTorbi(voziloDTO.getBrojTorbi());
		vozilo.setBrojVrata(voziloDTO.getBrojVrata());
		vozilo.setDodatniopis(voziloDTO.getDodatniopis());
		vozilo.setKlima(voziloDTO.getKlima());
		
		vozilo = voziloService.save(vozilo);
		
		return new ResponseEntity<>(new VoziloDTO(vozilo),HttpStatus.OK);
		
	}

	
	
	
}
