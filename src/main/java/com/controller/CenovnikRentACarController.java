package com.controller;

import java.util.Optional;

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
import com.model.CenovnikRentACar;
import com.model.Filijala;
import com.model.RentACar;
import com.model.Vozilo;
import com.service.CenovnikRentACarService;
import com.service.RentACarService;
import com.service.VoziloService;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping(value = "api/cenovnikrent")
public class CenovnikRentACarController {

	@Autowired
	private CenovnikRentACarService cenovnikService;

	@Autowired
	private VoziloService voziloService;

	@Autowired
	private RentACarService rentService;

	@RequestMapping(method=RequestMethod.POST,consumes="application/json")
	@PreAuthorize("hasRole('ADMIN_RENT')")
	public ResponseEntity<CenovnikRentACarDTO> saveCenovnik(@RequestBody CenovnikRentACarDTO cenovnikDTO){

		
		
		if (cenovnikDTO.getVoziloDTO()==null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}

		if (cenovnikDTO.getRentACarDTO()==null){
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		Optional<Vozilo> voziloOptional = voziloService.findOne(cenovnikDTO.getVoziloDTO().getId());

		System.out.println(voziloOptional.get().getNaziv());
		
		if (!voziloOptional.isPresent()) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}

		Optional<RentACar> rentACarOptional = rentService.findOne(cenovnikDTO.getRentACarDTO().getId());

		if (!rentACarOptional.isPresent()){
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}

		Vozilo voz = voziloOptional.get();

		Filijala f = voz.getVozilo();

		RentACar r = f.getFilijala();

		RentACar rentACar = rentACarOptional.get();

		if (rentACar.getId()!=r.getId()){
			System.out.println("Vozilo ne pripada filijali trenutnog RentACar-a");
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}

		CenovnikRentACar rent = new CenovnikRentACar();

		System.out.println(cenovnikDTO.getOdDatuma());
		
		rent.setOdDatuma(cenovnikDTO.getOdDatuma());
		rent.setDoDatuma(cenovnikDTO.getDoDatuma());
		rent.setCena(cenovnikDTO.getCena());
		rent.setVozilo(voz);
		rent.setServis(rentACar);
		
		rent = cenovnikService.save(rent);
		
		return new ResponseEntity<>(new CenovnikRentACarDTO(rent),HttpStatus.CREATED);
		
	}


	@RequestMapping(method=RequestMethod.PUT,consumes="application/json")
	@PreAuthorize("hasRole('ADMIN_RENT')")
	public ResponseEntity<CenovnikRentACarDTO> editCenovnik(@RequestBody CenovnikRentACarDTO cenovnikDTO){

		
		Optional<CenovnikRentACar> optCenovnik = cenovnikService.findOne(cenovnikDTO.getId());

		if (!optCenovnik.isPresent()){
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST); 
		}

		
		CenovnikRentACar rent = optCenovnik.get();

		
		rent.setOdDatuma(cenovnikDTO.getOdDatuma());
		rent.setDoDatuma(cenovnikDTO.getDoDatuma());
		rent.setCena(cenovnikDTO.getCena());
		
		rent = cenovnikService.save(rent);
		
		return new ResponseEntity<>(new CenovnikRentACarDTO(rent),HttpStatus.CREATED);
		
	}


	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<CenovnikRentACarDTO> getCenovnik(@PathVariable Long id) {

		Optional<CenovnikRentACar> optionalCenovnik = cenovnikService.findOne(id);

		if (!optionalCenovnik.isPresent()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<>(new CenovnikRentACarDTO(optionalCenovnik.get()), HttpStatus.OK);

	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	@PreAuthorize("hasRole('ADMIN_RENT')")
	public ResponseEntity<Void> deleteCenovnik(@PathVariable Long id) {

		Optional<CenovnikRentACar> optionalCenovnik = cenovnikService.findOne(id);

		if (optionalCenovnik.isPresent()) {
			cenovnikService.remove(id);
			return new ResponseEntity<>(HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

	}

	




}
