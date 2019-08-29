package com.service;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import com.dto.RezervacijaDTO;
import com.model.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;

import com.model.Rezervacija;
import com.repository.RezervacijaRepository;
import org.springframework.web.server.ResponseStatusException;

@Service
public class RezervacijaService {

	@Autowired
	UserService userService;

	@Autowired
	private RezervacijaRepository rezervacijaRepository;
	
	public Optional<Rezervacija> findById(Long id) {
		return rezervacijaRepository.findById(id);
	}

	public Rezervacija getOne(Long id){

		Optional<Rezervacija> optionalRezervacija = findById(id);

		if (!optionalRezervacija.isPresent()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Reservation doesn't exist!");
		}

		return  optionalRezervacija.get();
	}
	
	public List<Rezervacija> findAll(){
		return rezervacijaRepository.findAll();
	}
	
	public Rezervacija save(Rezervacija r) {
		return rezervacijaRepository.save(r);
	}
	
	public void remove(Long id) {
		rezervacijaRepository.deleteById(id);
	}

	public RezervacijaDTO findUserReservation(Long id,Principal user){

		Optional<User> optionalUser = userService.findByUsername(user.getName());

		if (!optionalUser.isPresent()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User doesn't exist!");
		}

		Optional<Rezervacija> optionalRezervacija = findById(id);

		if (!optionalRezervacija.isPresent()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Reservation doesn't exist!");
		}

		if (!optionalRezervacija.get().getUser().equals(optionalUser.get())){
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Wrong reservation id!");
		}

		return new RezervacijaDTO(optionalRezervacija.get());

	}


	public List<RezervacijaDTO> findAllUser(Principal user){

		Optional<User> optionalUser = userService.findByUsername(user.getName());

		if (!optionalUser.isPresent()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User doesn't exist!");
		}


		List<Rezervacija> rezervacijeList = optionalUser.get().getRezervacija();

		List<RezervacijaDTO> rezervacijaDTOS = new ArrayList<>();

		for (Rezervacija r:rezervacijeList){

			if (r.getDatumVremeS().after(new Date())){
				RezervacijaDTO rezervacijaDTO = new RezervacijaDTO(r);
				rezervacijaDTOS.add(rezervacijaDTO);
			}

		}

		return rezervacijaDTOS;

	}

	public List<RezervacijaDTO> findAllHistUser(Principal user){

		Optional<User> optionalUser = userService.findByUsername(user.getName());

		if (!optionalUser.isPresent()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User doesn't exist!");
		}

		List<Rezervacija> rezervacijeList = optionalUser.get().getRezervacija();

		List<RezervacijaDTO> rezervacijaDTOS = new ArrayList<>();

		for (Rezervacija r:rezervacijeList){

			if (r.getDatumVremeS().before(new Date())){
				RezervacijaDTO rezervacijaDTO = new RezervacijaDTO(r);
				rezervacijaDTOS.add(rezervacijaDTO);
			}

		}

		return rezervacijaDTOS;

	}



}
