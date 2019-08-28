package com.controller;

import java.util.*;

import com.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.dto.CenovnikRentACarDTO;
import com.dto.FilijalaDTO;
import com.dto.RentACarDTO;
import com.dto.RezervacijaRentACarDTO;
import com.dto.VoziloDTO;
import com.model.CenovnikRentACar;
import com.model.Filijala;
import com.model.RentACar;
import com.model.RezervacijaRentACar;
import com.model.StatusRes;
import com.model.Vozilo;
import com.repository.RentACarRepository;
import com.security.ResponseMessage;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping(value = "api/rentacar")
public class RentACarController {

	@Autowired
	private RentACarService rentACarService;
	
	@Autowired
	private CenovnikRentACarService cenovnikService;
	
	@Autowired
	private FilijalaService filijalaService;

	@Autowired
	private VoziloService voziloService;
	
	@Autowired
	private RezervacijaRentACarService rezService;

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<RentACarDTO> getRentACar(@PathVariable Long id) {

		Optional<RentACar> rentOptional = rentACarService.findOne(id);

		if (rentOptional.isPresent()) {
			return new ResponseEntity<>(new RentACarDTO(rentOptional.get()), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

	}

	@RequestMapping(value = "/all", method = RequestMethod.GET)
	public ResponseEntity<List<RentACarDTO>> getAll() {

		List<RentACar> rentACar = rentACarService.findAll();

		List<RentACarDTO> rentACarDTO = new ArrayList<>();

		for (RentACar r : rentACar) {
			rentACarDTO.add(new RentACarDTO(r));
		}

		return new ResponseEntity<>(rentACarDTO, HttpStatus.OK);
	}

	@RequestMapping(value = "/{id}/filijale", method = RequestMethod.GET)
	public ResponseEntity<List<FilijalaDTO>> getFilijale(@PathVariable Long id) {
		return new ResponseEntity<>(rentACarService.findOneFilijala(id), HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.POST, consumes = "application/json")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<RentACarDTO> saveRentACar(@RequestBody RentACarDTO rentACarDTO) {

		RentACar rentACar = new RentACar();
		rentACar.setId(rentACarDTO.getId());
		rentACar.setNaziv(rentACarDTO.getNaziv());
		rentACar.setOpis(rentACarDTO.getOpis());

		rentACar = rentACarService.save(rentACar);

		return new ResponseEntity<>(new RentACarDTO(rentACar), HttpStatus.CREATED);

	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	@PreAuthorize("hasRole('ADMIN_RENT')")
	public ResponseEntity<Void> deleteRentACar(@PathVariable Long id) {

		Optional<RentACar> rentOptional = rentACarService.findOne(id);

		if (rentOptional.isPresent()) {
			rentACarService.remove(id);
			return new ResponseEntity<>(HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

	}

	@RequestMapping(value = "/{id}/cenovnik", method = RequestMethod.GET)
	public ResponseEntity<List<CenovnikRentACarDTO>> getCenovnik(@PathVariable Long id) {

		Optional<RentACar> rentOptional = rentACarService.findOne(id);

		if (!rentOptional.isPresent()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

		Set<CenovnikRentACar> cenovnik = rentOptional.get().getCenovnici();

		List<CenovnikRentACarDTO> cenovnikDTO = new ArrayList<CenovnikRentACarDTO>();

		for (CenovnikRentACar c : cenovnik) {
			System.out.println(c.getOdDatuma());
			CenovnikRentACarDTO tempCDTO = new CenovnikRentACarDTO(c);
			cenovnikDTO.add(tempCDTO);
		}

		return new ResponseEntity<>(cenovnikDTO, HttpStatus.OK);

	}

	@RequestMapping(method = RequestMethod.PUT, consumes = "application/json")
	@PreAuthorize("hasRole('ADMIN_RENT')")
	public ResponseEntity<?> edit(@RequestBody RentACarDTO rentACarDTO) {

		if (rentACarService.exists(rentACarDTO.getNaziv())) {
			return new ResponseEntity<>(new ResponseMessage("Name is not available!"), HttpStatus.BAD_REQUEST);
		}

		if (rentACarDTO.getOpis().length() < 5) {
			return new ResponseEntity<>(new ResponseMessage("Minimum 5 characters!"), HttpStatus.BAD_REQUEST);
		}


		Optional<RentACar> rentOptional = rentACarService.findOne(rentACarDTO.getId());

		if (!rentOptional.isPresent()) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}

		RentACar rentaACar = rentOptional.get();

		rentaACar.setNaziv(rentACarDTO.getNaziv());
		rentaACar.setOpis(rentACarDTO.getOpis());

		rentaACar = rentACarService.save(rentaACar);

		return new ResponseEntity<>(new RentACarDTO(rentaACar), HttpStatus.OK);

	}
	

	@RequestMapping(value = "/search", method = RequestMethod.GET)
	public ResponseEntity<?> search(@RequestParam(value="locationp")String locationp,@RequestParam(value = "bring") String bring,@RequestParam(value = "pickup") Date pickUp, @RequestParam(value = "dropoff") Date dropOff) {

		System.out.println(pickUp);
		System.out.println(bring);

		return new ResponseEntity<>(filijalaService.search(locationp, bring, pickUp, dropOff),HttpStatus.OK);

	}

	


}
