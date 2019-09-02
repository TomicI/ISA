package com.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import com.dto.RezervacijaDTO;
import com.dto.aviokompanija.OcenaDTO;
import com.model.*;
import com.model.aviokompanija.Ocena;
import com.security.ResponseMessage;
import com.service.RezervacijaRentACarService;
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
import com.service.FilijalaService;
import com.service.VoziloService;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping(value="api/vozilo")
public class VoziloController {
	
	@Autowired
	private VoziloService voziloService;
	
	@RequestMapping(method=RequestMethod.POST,consumes="application/json")
	@PreAuthorize("hasRole('ADMIN_RENT')")
	public ResponseEntity<VoziloDTO> saveVozilo(@RequestBody VoziloDTO voziloDTO){

		return new ResponseEntity<>(new VoziloDTO(voziloService.insertVehicle(voziloDTO)),HttpStatus.CREATED);
		
	}

	@RequestMapping(value="/{id}",method=RequestMethod.GET)
	public ResponseEntity<VoziloDTO> getVozilo(@PathVariable Long id){

		return new ResponseEntity<>(new VoziloDTO(voziloService.getOne(id)),HttpStatus.OK);
		
	}

	@RequestMapping(value="/rent",method=RequestMethod.GET)
	public ResponseEntity<List<VoziloDTO>> getAllVehicleRent(){

		return new ResponseEntity<>(voziloService.getAllVehicleRent(),HttpStatus.OK);

	}
	
	@RequestMapping(value="/{id}",method=RequestMethod.DELETE)
	@PreAuthorize("hasRole('ADMIN_RENT')")
	public ResponseEntity<Void> deleteVozilo(@PathVariable Long id){
			voziloService.removeVehicle(id);
			return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@RequestMapping(value="/{id}/cenovnik",method=RequestMethod.GET)
	public ResponseEntity<List<CenovnikRentACarDTO>> getCenovnik(@PathVariable Long id){

		return new ResponseEntity<>(voziloService.getCenovnik(id),HttpStatus.OK);
		
	}	
	
	@RequestMapping(method=RequestMethod.PUT,consumes="application/json")
	@PreAuthorize("hasRole('ADMIN_RENT')")
	public ResponseEntity<VoziloDTO> updateVozilo(@RequestBody VoziloDTO voziloDTO){

		return new ResponseEntity<>(new VoziloDTO(voziloService.updateVehicle(voziloDTO)),HttpStatus.OK);
		
	}

	@RequestMapping(value="/rate",method=RequestMethod.POST,consumes="application/json")
	public ResponseEntity<ResponseMessage> rateVehicle(@RequestBody OcenaDTO ocenaDTO){

		return new ResponseEntity<>(voziloService.rateVehicle(ocenaDTO),HttpStatus.OK);

	}

	

}
