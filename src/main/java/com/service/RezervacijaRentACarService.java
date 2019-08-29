package com.service;

import java.security.Principal;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import com.dto.RezervacijaDTO;
import com.dto.RezervacijaRentACarDTO;
import com.model.*;
import com.model.user.User;
import com.security.ResponseMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.repository.RezervacijaRentACarRepository;
import org.springframework.web.server.ResponseStatusException;

@Service
public class RezervacijaRentACarService {
	
	@Autowired
	private RezervacijaRentACarRepository rezRepository;

	@Autowired
	private UserService userService;

	@Autowired
	private  FilijalaService filijalaService;

	@Autowired
	private VoziloService voziloService;

	@Autowired
	private RezervacijaService rezervacijaService;
	
	public Optional<RezervacijaRentACar> findOne(Long id){
		return rezRepository.findById(id);
	}
	
	public List<RezervacijaRentACar> findAll(){
		return rezRepository.findAll();
	}
	
	public RezervacijaRentACar save(RezervacijaRentACar r) {
		return rezRepository.save(r);
	}
	
	public void remove (Long id) {
		rezRepository.deleteById(id);
	}
	
	public List<RezervacijaRentACar> findByVoz(Vozilo voz){
		return rezRepository.findByVozilo(voz);
	}

	public RezervacijaRentACar getOneRes(Long id){

		Optional<RezervacijaRentACar> rezOptional  = findOne(id);

		if (!rezOptional .isPresent()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Reservation doesn't exist!");
		}

		return rezOptional.get();

	}

	public RezervacijaRentACarDTO getOne(Long id){

		Optional<RezervacijaRentACar> rezOptional  = findOne(id);

		if (!rezOptional .isPresent()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Reservation doesn't exist!");
		}

		return new RezervacijaRentACarDTO(rezOptional.get());
	}

	public RezervacijaDTO saveRentReservation(RezervacijaRentACarDTO rezDTO,Principal user){

		Optional<User> optionalUser = userService.findByUsername(user.getName());

		if (!optionalUser.isPresent()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User doesn't exist!");
		}

		if (rezDTO.getFilijalaDTO()==null) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Branch not defined!");
		}

		if (rezDTO.getVoziloDTO()==null) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Branch not defined!");
		}

		Optional<Filijala> filijalaOptional = filijalaService.findOne(rezDTO.getFilijalaDTO().getId());
		Optional<Vozilo> voziloOptional = voziloService.findOne(rezDTO.getVoziloDTO().getId());

		Optional<Filijala> filijalaDropOptional = filijalaService.findOne(rezDTO.getFilijalaDTO().getId());

		if (!filijalaOptional.isPresent()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not present!");
		}

		if (!voziloOptional.isPresent()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Vehicle not present!");
		}

		if (!filijalaDropOptional.isPresent()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Branch not present!");
		}

		RezervacijaRentACar rez = new RezervacijaRentACar();
		Rezervacija rezervacija = new Rezervacija();

		rez.setDatumRez(new Date());
		rez.setDatumPreuz (rezDTO.getDatumPreuz());
		rez.setDatumVracanja (rezDTO.getDatumVracanja());
		rez.setCena (rezDTO.getCena());
		rez.setOtkazana(false);
		rez.setRezervacija(filijalaOptional.get());
		rez.setRezervacijaDrop(filijalaDropOptional.get());

		rez.setStatus(StatusRes.Reserved);
		rez.setVozilo(voziloOptional.get());

		rezervacija.setDatumVremeP(rezDTO.getDatumPreuz());
		rezervacija.setDatumVremeS(rezDTO.getDatumVracanja());
		rezervacija.setCena(rezDTO.getCena());
		rezervacija.setUser(optionalUser.get());
		rezervacija.setRezervacijaRentACar(rez);

		rezervacija.setKarta(null);
		rezervacija.setRezervacijaSobe(null);

		save(rez);

		rezervacijaService.save(rezervacija);


		return new RezervacijaDTO(rezervacija);

	}

	public ResponseMessage cancelStatus(Long id){

		Optional<RezervacijaRentACar> rezOptional  = findOne(id);

		if (!rezOptional .isPresent()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Reservation doesn't exist!");
		}

		Calendar cal = Calendar.getInstance();


		if (( rezOptional.get().getDatumPreuz().getTime() - cal.getTime().getTime() ) <= 0) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Reservation can't be canceled , cancelation has to be made 48 h prior to the Pick-up date !");
		}

		return new ResponseMessage(" Reservation can be canceled , cancelation has to be made 48 h prior to the Pick-up date !" );

	}

	public ResponseMessage cancelRes(RezervacijaRentACarDTO rezDTO){

		if (rezDTO.getFilijalaDTO()==null) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Branch not defined!");
		}

		if (rezDTO.getVoziloDTO()==null) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Vehicle not defined!");
		}


		Optional<Filijala> filijalaOptional = filijalaService.findOne(rezDTO.getFilijalaDTO().getId());
		Optional<Vozilo> voziloOptional = voziloService.findOne(rezDTO.getVoziloDTO().getId());


		if (!filijalaOptional.isPresent()) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
		}

		if (!voziloOptional.isPresent()) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
		}



		Optional<RezervacijaRentACar> rezOptional = findOne(rezDTO.getId());

		Calendar cal = Calendar.getInstance();


		if (( rezOptional.get().getDatumPreuz().getTime() - cal.getTime().getTime() ) <= 0) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Reservation can't be canceled , cancelation has to be made 48 h prior to the Pick-up date !");
		}

		RezervacijaRentACar rez = rezOptional.get();

		rez.setOtkazana(true);
		rez.setStatus(StatusRes.Canceled);

		rez = save(rez);

		return new ResponseMessage("Reservation canceled!");

	}





}
