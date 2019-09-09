package com.service;

import java.security.Principal;
import java.util.*;

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
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
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

	@Transactional(isolation = Isolation.SERIALIZABLE, propagation = Propagation.REQUIRED)
	public RezervacijaDTO addToReservation(RezervacijaDTO rezervacijaDTO,Principal user){

		User userSignedIn = userService.getByUsername(user.getName());

		Rezervacija rezervacija = rezervacijaService.getOne(rezervacijaDTO.getId());

		User userRes = rezervacija.getUser();


		if(!userSignedIn.equals(userRes)){
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Wrong Reservation!");
		}

		RezervacijaRentACarDTO rezDTO  = rezervacijaDTO.getRezervacijaRentACarDTO();

		if (rezDTO==null){
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
		}

		Date today = new Date();

		if (rezDTO.getDatumPreuz().before(today) || rezDTO.getDatumPreuz().equals(today)){
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Pick-up time not valid!");
		}

		if (rezDTO.getFilijalaDTO()==null) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Branch not defined!");
		}

		if (rezDTO.getFilijalaDropDTO()==null) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Branch not defined!");
		}

		Optional<Filijala> filijalaOptional = filijalaService.findOne(rezDTO.getFilijalaDTO().getId());
		Optional<Filijala> filijalaDropOptional = filijalaService.findOne(rezDTO.getFilijalaDTO().getId());

		if (!filijalaOptional.isPresent()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not present!");
		}

		if (!filijalaDropOptional.isPresent()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Branch not present!");
		}

		Optional<Vozilo> voziloOptional = voziloService.findOne(rezDTO.getVoziloDTO().getId());

		if (rezDTO.getVoziloDTO()==null) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Vehicle not defined!");
		}


		if (!voziloOptional.isPresent()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Vehicle not present!");
		}


		RezervacijaRentACar rez = new RezervacijaRentACar();

		rez.setDatumRez(new Date());
		rez.setRezervacija(filijalaOptional.get());
		rez.setRezervacijaDrop(filijalaDropOptional.get());
		rez.setDatumPreuz (rezDTO.getDatumPreuz());
		rez.setDatumVracanja (rezDTO.getDatumVracanja());
		rez.setCena (rezDTO.getCena());
		rez.setOtkazana(false);
		rez.setVozilo(voziloOptional.get());
		rez.setStatus(StatusRes.Reserved);
		rez.setNaPopustu(false);
		rez.setPopust(0.0);
		save(rez);

		rezervacija.setRezervacijaRentACar(rez);

		rezervacijaService.save(rezervacija);

		return new RezervacijaDTO(rezervacija);

	}

	@Transactional(isolation = Isolation.SERIALIZABLE, propagation = Propagation.REQUIRED)
	public RezervacijaDTO saveRentReservation(RezervacijaRentACarDTO rezDTO,Principal user){

		Optional<User> optionalUser = userService.findByUsername(user.getName());

		Date today = new Date();

		if (rezDTO.getDatumPreuz().before(today) || rezDTO.getDatumPreuz().equals(today)){
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Pick-up time not valid!");
		}

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


		rez.setDatumPreuz (rezDTO.getDatumPreuz());
		rez.setDatumVracanja (rezDTO.getDatumVracanja());
		rez.setCena (rezDTO.getCena());
		rez.setOtkazana(false);
		rez.setRezervacija(filijalaOptional.get());
		rez.setRezervacijaDrop(filijalaDropOptional.get());

		rez.setVozilo(voziloOptional.get());

        rez.setDatumRez(new Date());
        rez.setStatus(StatusRes.Reserved);

		if (!rezDTO.getNaPopustu()){


            rezervacija.setDatumVremeP(rezDTO.getDatumPreuz());
            rezervacija.setDatumVremeS(rezDTO.getDatumVracanja());
            rezervacija.setCena(rezDTO.getCena());
            rezervacija.setUser(optionalUser.get());
            rezervacija.setRezervacijaRentACar(rez);

			rez.setNaPopustu(false);
			rez.setPopust(0.0);

            rezervacija.setKarta(null);
            rezervacija.setRezervacijaSobe(null);
            rezervacijaService.save(rezervacija);
            save(rez);
            return new RezervacijaDTO(rezervacija);
        }else{

		    rez.setNaPopustu(true);
		    rez.setPopust(rezDTO.getPopust());
            save(rez);

        }



		return null;

	}

	public ResponseMessage cancelStatus(Long id){

		Optional<RezervacijaRentACar> rezOptional  = findOne(id);

		if (!rezOptional .isPresent()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Reservation doesn't exist!");
		}

		Calendar cal = Calendar.getInstance();

		System.out.println(rezOptional.get().getDatumPreuz().getTime() - cal.getTime().getTime());


		if (( rezOptional.get().getDatumPreuz().getTime() - cal.getTime().getTime() ) <= 48*3600*1000) {
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

	public List<RezervacijaRentACarDTO> getDealRes(Long rentId,Date pickUp,Date dropOff){

		List<Filijala> filijale = filijalaService.findByRentACar(rentId);

		Date te = new Date(pickUp.getTime());

		System.out.println(te);

		System.out.println(pickUp);
		System.out.println(dropOff);


		ArrayList<RezervacijaRentACarDTO> rezervacijaRentACarDTOS = new ArrayList<>();

		for (Filijala f :filijale){


			List<RezervacijaRentACar> rezList = f.getRezervacije();

			for (RezervacijaRentACar r:rezList){

					if (r.getNaPopustu()){



						System.out.println("Rez na popustu");
						System.out.println(r.getDatumPreuz());
						System.out.println(r.getDatumVracanja());



						if ( r.getDatumPreuz().after(pickUp) || r.getDatumPreuz().equals(pickUp) ){
							System.out.println("Pickup Prosao");
							if ( r.getDatumVracanja().before(dropOff) ||  r.getDatumVracanja().equals(dropOff) ){
								System.out.println("Drop prosao");
								RezervacijaRentACarDTO rezervacijaRentACarDTO  = new RezervacijaRentACarDTO(r);

								rezervacijaRentACarDTOS.add(rezervacijaRentACarDTO);
							}
						}

					}
				}

			}


        return rezervacijaRentACarDTOS;
	}

	public List<RezervacijaRentACarDTO> getAllAdmin(boolean res , Principal username){

		System.out.println(res);

		User user = userService.getByUsername(username.getName());

		List<Filijala> filijale = filijalaService.findByRentACar(user.getRentACar().getId());

		ArrayList<RezervacijaRentACarDTO> rezervacijaRentACarDTOS = new ArrayList<>();


		for (Filijala f :filijale){

			List<RezervacijaRentACar> rezList = f.getRezervacije();

			for (RezervacijaRentACar r:rezList){

				if (res){
					if (!r.getNaPopustu()){
						RezervacijaRentACarDTO rezervacijaRentACarDTO  = new RezervacijaRentACarDTO(r);

						rezervacijaRentACarDTOS.add(rezervacijaRentACarDTO);
					}
				}else{
					if (r.getNaPopustu()){

						RezervacijaRentACarDTO rezervacijaRentACarDTO  = new RezervacijaRentACarDTO(r);

						rezervacijaRentACarDTOS.add(rezervacijaRentACarDTO);
					}
				}



			}

		}

		return rezervacijaRentACarDTOS;

	}



	public RezervacijaRentACarDTO changeStatus(RezervacijaRentACarDTO rezervacijaRentACarDTO){

		RezervacijaRentACar rezervacijaRentACar = getOneRes(rezervacijaRentACarDTO.getId());


		rezervacijaRentACar.setStatus(rezervacijaRentACar.getStatus());

		save(rezervacijaRentACar);

		return new RezervacijaRentACarDTO(rezervacijaRentACar);
	}







}
