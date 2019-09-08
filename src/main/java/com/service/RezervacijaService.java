package com.service;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import com.dto.InviteDTO;
import com.dto.RezervacijaDTO;
import com.dto.UserDTO;
import com.dto.aviokompanija.PutnikDTO;
import com.model.Invite;
import com.model.aviokompanija.Karta;
import com.model.aviokompanija.Putnik;
import com.model.aviokompanija.Sediste;
import com.model.user.User;
import com.repository.aviokompanija.KartaRepository;
import com.repository.aviokompanija.SedisteRepository;
import com.service.aviokompanija.KartaService;
import com.service.aviokompanija.PutnikService;
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
	InviteService inviteService;

	@Autowired
	PutnikService putnikService;

	@Autowired
	KartaRepository kartaRepository;

	@Autowired
	SedisteRepository sedisteRepository;

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


	public RezervacijaDTO getReservation(Long id){
		Optional<Rezervacija> r=rezervacijaRepository.findById(id);

		if (!r.isPresent()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Reservation doesn't exist!");
		}

		
		return new RezervacijaDTO(r.get());
	}

	public InviteDTO inviteFriend(InviteDTO inviteDTO, String username){
		Invite invite=new Invite();

		Optional<User> userS=this.userService.findByUsername(username);
		Optional<User> userR=this.userService.findByUsername(inviteDTO.userReceive.getUsername());
		Optional<Rezervacija> r= rezervacijaRepository.findById(inviteDTO.reservation.getId());
		if(userS.isPresent() && userR.isPresent() && r.isPresent()) {
			invite.setUserSent(userS.get());
			invite.setUserReceive(userR.get());
			invite.setDateSent(new Date());
			invite.setReservation(r.get());
			invite = this.inviteService.save(invite);

			PutnikDTO p=new PutnikDTO();
			p.setUser(new UserDTO(userR.get()));
			p.setPrezime(userR.get().getLastName());
			p.setIme(userR.get().getFirstName());
			p.setBrojPasosa(userR.get().getBrojPasosa());
			putnikService.create(r.get().getKarta().getId(), p);
		}
		return new InviteDTO(invite);
	}

	public void eFriend(InviteDTO inviteDTO, String username){
		Optional<Invite> invite=this.inviteService.findByID(inviteDTO.getId());

		if(invite.isPresent()) {
			Optional<User> userR = this.userService.findByUsername(inviteDTO.userReceive.getUsername());
			Optional<Rezervacija> r = rezervacijaRepository.findById(inviteDTO.reservation.getId());
			if ( userR.isPresent() && r.isPresent() ) {
				for(Sediste s: r.get().getKarta().getSedista()){
					if(s.getPutnik().getUser()==userR.get()) {
						s.setPutnik(null);
						s.setZauzeto(false);
						s.setKarta(null);
						this.sedisteRepository.save(s);
						break;
					}
				}
			}
		}
		this.inviteService.delete(invite.get());
	}
}
