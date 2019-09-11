package com.service;

import java.security.Principal;
import java.util.*;

import com.dto.InviteDTO;
import com.dto.RezervacijaDTO;
import com.dto.RezervacijaRentACarDTO;
import com.dto.UserDTO;
import com.dto.aviokompanija.PutnikDTO;
import com.model.Hotel;
import com.model.Invite;
import com.model.RezervacijaSobe;
import com.model.aviokompanija.Karta;
import com.model.aviokompanija.Putnik;
import com.model.aviokompanija.Sediste;
import com.model.user.User;
import com.repository.HotelRepository;
import com.repository.RezervacijaSobeRepository;
import com.repository.aviokompanija.KartaRepository;
import com.repository.aviokompanija.SedisteRepository;
import com.security.ResponseMessage;
import com.service.aviokompanija.KartaService;
import com.service.aviokompanija.PutnikService;
import net.bytebuddy.asm.Advice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;

import com.model.Rezervacija;
import com.repository.RezervacijaRepository;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
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

	@Autowired
	private RezervacijaService rezervacijaService;

	@Autowired
	private KartaService kartaService;

	@Autowired
	private RezervacijaRentACarService rezervacijaRentACarService;

	@Autowired
	private JavaMailSender mailSender;

	@Autowired
	private HotelRepository hotelRepository;

	@Autowired
	private RezervacijaSobeRepository rezervacijaSobeRep;
	
	public Optional<Rezervacija> findById(Long id) {
		return rezervacijaRepository.findById(id);
	}

	public Optional<Rezervacija> findByRezervacijaRent(Long id){return rezervacijaRepository.findByRezervacijaRentACarId(id);}

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

	@Transactional(isolation = Isolation.SERIALIZABLE, propagation = Propagation.REQUIRED)
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
			if (!inviteService.existsByResAndUser(id,optionalUser.get().getId(),true)){
				throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Wrong reservation id!");
			}
		}

		return new RezervacijaDTO(optionalRezervacija.get());

	}

	public List<RezervacijaDTO> findAllInvited(Principal user){

        Optional<User> optionalUser = userService.findByUsername(user.getName());

        if (!optionalUser.isPresent()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User doesn't exist!");
        }

        List<RezervacijaDTO> rezervacijaDTOS = new ArrayList<>();
        List<Invite> invites = inviteService.findByUserAndAcc(optionalUser.get().getId(),true);
        for (Invite i:invites){
            System.out.println(i.getId());
               rezervacijaDTOS.add(new RezervacijaDTO(i.getReservation()));
        }
	    return rezervacijaDTOS;
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
			invite.setAccepted(false);
			invite = this.inviteService.save(invite);

			PutnikDTO p=new PutnikDTO();
			p.setUser(new UserDTO(userR.get()));
			p.setPrezime(userR.get().getLastName());
			p.setIme(userR.get().getFirstName());
			p.setBrojPasosa(userR.get().getBrojPasosa());
			putnikService.create(r.get().getKarta().getId(), p);

			SimpleMailMessage message = new SimpleMailMessage();
			message.setTo(userR.get().getEmail());
			message.setSubject("You have invitation!");
			message.setText("You have new invitation from " + userS.get().getFirstName() + "  " + userS.get().getLastName()+ ". You can accept or eject here: http://localhost:4200/homeReg/friends. " );
			mailSender.send(message);
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
						s.setPrtljag(null);
						s.setDodatnaUslugaAviokompanija(null);
						this.sedisteRepository.save(s);
						break;
					}
				}
			}
		}
		this.inviteService.delete(invite.get());
	}

	public void aFriend(Long inviteId){
		Optional<Invite> invite=this.inviteService.findByID(inviteId);

		if (!invite.isPresent()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Wrong request id!");
        }

		invite.get().setAccepted(true);

		this.inviteService.save(invite.get());
	}

	public void sendEMail(RezervacijaDTO rezervacijaDTO){


		Optional<Rezervacija> r= rezervacijaRepository.findById(rezervacijaDTO.getId());
		if(r.isPresent()) {
			SimpleMailMessage message = new SimpleMailMessage();
			message.setTo(r.get().getUser().getEmail());
			message.setSubject("You reservation is created!");
			String textMessage="Reservation details: \n";

			if(r.get().getKarta()!=null) {
				if (r.get().getKarta().getPopust() > 0) {
					textMessage += "Old price: " + r.get().getKarta().getCena() + "\n";
					textMessage += "Price with discount: " + r.get().getKarta().getPopust() + "\n";
				} else {
					textMessage += "Price: " + r.get().getKarta().getCena() + "\n";
				}
				textMessage += "From: " + r.get().getKarta().getLet().getAerodrom().getNaziv() + " at " + r.get().getKarta().getLet().getVremePolaska() + "\n"
						+ "To: " + r.get().getKarta().getLet().getDestinacija().getGrad() + "," + r.get().getKarta().getLet().getDestinacija().getDrzava() + " at " + r.get().getKarta().getLet().getVremeDolaska() + "\n"
						+ "You have " + r.get().getKarta().getSedista().size();
				List<Sediste> sedistes = new ArrayList<>(r.get().getKarta().getSedista());
				int i = 1;
				if (r.get().getKarta().getSedista().size() > 1) {
					textMessage += " seats\n";
					for (Sediste s : sedistes) {
						textMessage += "Seat " + i + " with number " + s.getKolona() + "/" + s.getRed() + "/" + s.getSegment().getRedniBroj() + " for ";
						if (s.getPutnik().getUser() == r.get().getUser())
							textMessage += "you.\n";
						else if (s.getPutnik().getUser() == null)
							textMessage += s.getPutnik().getIme() + " " + s.getPutnik().getPrezime() + "\n";
						else if (s.getPutnik().getUser() != null)
							textMessage += "your friend " + s.getPutnik().getIme() + " " + s.getPutnik().getPrezime() + "\n";
						i++;
					}
				} else {

					textMessage += " seat with number " + sedistes.get(0).getKolona() + "/" + sedistes.get(0).getRed() + "/" + sedistes.get(0).getSegment().getRedniBroj() + "\n";
				}
			}
			if(r.get().getRezervacijaRentACar()!=null){
				textMessage+="\n";
				textMessage+="Car " + r.get().getRezervacijaRentACar().getVozilo().getMarka() + " " + r.get().getRezervacijaRentACar().getVozilo().getModel() +"\n";
				textMessage+="From " + r.get().getRezervacijaRentACar().getDatumPreuz() +" to: " + r.get().getRezervacijaRentACar().getDatumVracanja()+"\n";
				textMessage+="Price " + r.get().getRezervacijaRentACar().getCena()+"\n";
			}

			if(r.get().getRezervacijaSobe()!= null){
				textMessage+="\n";
				textMessage+="Hotel \n";
				textMessage+=r.get().getRezervacijaSobe().getSoba().getNaziv()+"\n";
				//textMessage+="From " + r.get().getRezervacijaSobe().getOdDatuma() + " to " + r.get().getRezervacijaSobe().getDoDatuma();
			}
			message.setText(textMessage);
			mailSender.send(message);
		}
	}

	public ResponseMessage cancelStatus(Long id){

		Rezervacija rezervacija = rezervacijaService.getOne(id);

		Calendar cal = Calendar.getInstance();

		if (rezervacija.isOtkazana()){
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Reservation already canceled !");

		}

		if (rezervacija.getDatumVremeP().getTime() - cal.getTime().getTime() < 3*3600*1000 ){
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Reservation can't be canceled , cancelation has to be made 3 h prior to the departure !");
		}

		return new ResponseMessage(" Reservation can be canceled , cancelation has to be made 3 h prior to the departure date !" );

	}

	public ResponseMessage cancelConfirm(Long id){

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.getByUsername(auth.getName());

		Rezervacija rezervacija = rezervacijaService.getOne(id);
		cancelStatus(id);

		if (!rezervacija.getUser().equals(user)){
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Reservation doesn't belong to this user!");
		}

		kartaService.cancel(rezervacija);

		if (rezervacija.getRezervacijaRentACar()!=null){

			rezervacijaRentACarService.cancelRes(new RezervacijaRentACarDTO(rezervacija.getRezervacijaRentACar()));
		}

		if (rezervacija.getRezervacijaSobe()!=null){


		}

		rezervacija.setOtkazana(true);
		save(rezervacija);


		return new ResponseMessage("Reservation cancelled");


	}

	public RezervacijaDTO reserveHotel( Long id, RezervacijaDTO rezervacijaDTO){
		Optional<Hotel> hotel=hotelRepository.findById(id);
		RezervacijaDTO rezDTO=new RezervacijaDTO();
		Optional<Rezervacija> rezervacija=rezervacijaRepository.findById(rezervacijaDTO.getId());
		if(rezervacija.isPresent()){
			if(hotel.isPresent()){
				RezervacijaSobe rezervacijaSobe=new RezervacijaSobe();
				rezervacijaSobe.setSoba(hotel.get());
				rezervacijaSobe=this.rezervacijaSobeRep.save(rezervacijaSobe);
				rezervacija.get().setRezervacijaSobe(rezervacijaSobe);
				rezDTO=new RezervacijaDTO(rezervacijaRepository.save(rezervacija.get()));
			}
		}



		return rezDTO;
	}


}
