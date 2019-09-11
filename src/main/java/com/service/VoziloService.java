package com.service;

import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import com.dto.CenovnikRentACarDTO;
import com.dto.VoziloDTO;
import com.dto.aviokompanija.OcenaDTO;
import com.model.*;
import com.model.aviokompanija.Ocena;
import com.model.user.User;
import com.security.ResponseMessage;
import com.service.aviokompanija.OcenaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.repository.VoziloRepository;
import org.springframework.transaction.UnexpectedRollbackException;
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
	private RezervacijaRentACarService rezervacijaRentACarService;

	@Autowired
	private FilijalaService filijalaService;

	@Autowired
	private UserService userService;

	@Autowired
	private OcenaService ocenaService;
	
	public Optional<Vozilo> findOne(Long id){
		return voziloRepository.findById(id);
	}

	public List<Vozilo> findByFilijala(Long filijala_id){
		return voziloRepository.findByFilijalaId(filijala_id);
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
		try {
			voziloRepository.deleteById(id);
		}catch (Exception e){
				throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Vehicle can't be deleted!");
		}


	}

	public List<CenovnikRentACarDTO> getCenovnik(Long id){

		Vozilo vozilo = getOne(id);

		List<CenovnikRentACarDTO> cenovnikRentACarDTOS = new ArrayList<>();

		for (CenovnikRentACar c:vozilo.getCenovnik()) {
			CenovnikRentACarDTO cenovnikDTO = new CenovnikRentACarDTO(c);
			cenovnikRentACarDTOS.add(cenovnikDTO);
		}

		return cenovnikRentACarDTOS;

	}

	public void removeVehicle(Long id){

		getOne(id);
		remove(id);
	}

	public Vozilo insertVehicle(VoziloDTO voziloDTO) {

		if (voziloDTO.getFilijalaDTO()==null) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
		}

		Filijala filijala = filijalaService.getOne(voziloDTO.getFilijalaDTO().getId());

		Vozilo vozilo = new Vozilo();
        vozilo.setFilijala(filijala);

		saveFromDTO(voziloDTO,vozilo);

		return save(vozilo);

	}

	public Vozilo updateVehicle(VoziloDTO voziloDTO){
		Vozilo vozilo = getOne(voziloDTO.getId());
		Filijala filijala = filijalaService.getOne(voziloDTO.getFilijalaDTO().getId());

		List<RezervacijaRentACar> rezervacijaRentACars = rezervacijaRentACarService.findByVoz(vozilo);

		Date todayTemp = new Date();

		for(RezervacijaRentACar r:rezervacijaRentACars){
			if (!r.getOtkazana()){
				if(r.getDatumPreuz().after(todayTemp) || r.getDatumVracanja().after(todayTemp)){
					throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Vehicle has reservations!");
				}
			}
		}

		saveFromDTO(voziloDTO,vozilo);

		return save(vozilo);

	}


	public List<VoziloDTO> getAllVehicleRent(){

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.getByUsername(auth.getName());

		System.out.println("PREUZIMANJE KOLA " + user.getRentACar().getId());

		List<Filijala> filijale = filijalaService.findByRentACar(user.getRentACar().getId());

		List<VoziloDTO> vozilaDTO = new ArrayList<>();

		for (Filijala f:filijale){
			vozilaDTO.addAll(filijalaService.findOneVeh(f.getId()));
		}


		return vozilaDTO;
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

    @Transactional(isolation = Isolation.SERIALIZABLE, propagation = Propagation.REQUIRED)
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

    public void saveFromDTO(VoziloDTO voziloDTO,Vozilo vozilo){
		vozilo.setNaziv(voziloDTO.getNaziv());
		vozilo.setMarka(voziloDTO.getMarka());
		vozilo.setModel(voziloDTO.getModel());
		vozilo.setGrupa(voziloDTO.getGrupa());
		vozilo.setMenjac(voziloDTO.getMenjac());
		vozilo.setRezervoar(voziloDTO.getRezervoar());
		vozilo.setGorivo(voziloDTO.getGorivo());
		vozilo.setPotrosnja(voziloDTO.getPotrosnja());
		vozilo.setBrojSedista(voziloDTO.getBrojSedista());
		vozilo.setBrojTorbi(voziloDTO.getBrojTorbi());
		vozilo.setBrojVrata(voziloDTO.getBrojVrata());
		vozilo.setDodatniopis(voziloDTO.getDodatniopis());
		vozilo.setKlima(voziloDTO.getKlima());

	}



}
