package com.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import com.dto.VoziloDTO;
import com.dto.aviokompanija.OcenaDTO;
import com.model.Rezervacija;
import com.model.RezervacijaRentACar;
import com.model.aviokompanija.Ocena;
import com.model.user.User;
import com.security.ResponseMessage;
import com.service.aviokompanija.OcenaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.model.Vozilo;
import com.repository.VoziloRepository;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

@Service
public class VoziloService {
	
	@Autowired
	private VoziloRepository voziloRepository;

	@Autowired
	private RezervacijaService rezervacijaService;

	@Autowired
	private UserService userService;

	@Autowired
	private OcenaService ocenaService;
	
	public Optional<Vozilo> findOne(Long id){
		return voziloRepository.findById(id);
	}

	public Vozilo getOne(Long id){

		Optional<Vozilo> optionalVozilo = findOne(id);
		if (!optionalVozilo.isPresent()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Vehicle doesn't exist!");
		}
		return optionalVozilo.get();

	}
	
	public List<Vozilo> findAll(){
		return voziloRepository.findAll();
	}
	
	public Vozilo save(Vozilo vozilo) {
		return voziloRepository.save(vozilo);
	}
	
	public void remove(Long id) {
		voziloRepository.deleteById(id);
	}

	public boolean ratePermission(Long resid,Long vehid){

        Rezervacija rezervacija = rezervacijaService.getOne(resid);
        Vozilo vozilo = getOne(vehid);
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.getByUsername(auth.getName());

        if (rezervacija.getDatumVremeS().after(new Date())){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Reservation didn't end!");
        }

        if (ocenaService.findByRezervacijaAndVozilo(rezervacija.getId(),vozilo.getId(),user.getId()).isPresent()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Vehicle already rated!");
        }

        return true;

    }

    @Transactional(isolation = Isolation.SERIALIZABLE, propagation = Propagation.REQUIRES_NEW)
	public ResponseMessage rateVehicle(OcenaDTO ocenaDTO){

		Rezervacija rezervacija = rezervacijaService.getOne(ocenaDTO.getRezervacijaDTO().getId());
		Vozilo vozilo = getOne(ocenaDTO.getVoziloDTO().getId());

		if (rezervacija.getDatumVremeS().after(new Date())){
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Reservation didn't end!");
		}

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.getByUsername(auth.getName());

		if (ocenaService.findByRezervacijaAndVozilo(rezervacija.getId(),vozilo.getId(),user.getId()).isPresent()){
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Vehicle already rated!");
		}


		Ocena ocena = new Ocena();

		ocena.setOcena(ocenaDTO.getOcena());
		ocena.setUser(user);
		ocena.setOcDate(new Date());
		ocena.setRezervacija(rezervacija);
		ocena.setVozilo(vozilo);

		ocenaService.saveOcena(ocena);
		return new ResponseMessage("Vehicle rate saved!");
	}

	public OcenaDTO getRate(Long resid,Long vehid){

        Rezervacija rezervacija = rezervacijaService.getOne(resid);
        Vozilo vozilo = getOne(vehid);
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.getByUsername(auth.getName());

        Optional<Ocena> optionalOcena = ocenaService.findByRezervacijaAndVozilo(rezervacija.getId(),vozilo.getId(),user.getId());

        if (optionalOcena.isPresent()){

            OcenaDTO ocenaDTO = new OcenaDTO();
            ocenaDTO.setOcena(optionalOcena.get().getOcena());
            ocenaDTO.setVoziloDTO(new VoziloDTO(vozilo));

            return ocenaDTO;
        }

        return null;

    }



}
