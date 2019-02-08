package com.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Set;

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
import com.model.Vozilo;
import com.repository.RentACarRepository;
import com.security.ResponseMessage;
import com.service.CenovnikRentACarService;
import com.service.RentACarService;
import com.service.RezervacijaRentACarService;
import com.service.RezervacijaService;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping(value = "api/rentacar")
public class RentACarController {

	@Autowired
	private RentACarService rentACarService;
	
	@Autowired
	private CenovnikRentACarService cenovnikService;
	
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

		Optional<RentACar> rentOptional = rentACarService.findOne(id);

		if (!rentOptional.isPresent()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

		Set<Filijala> filijale = rentOptional.get().getFilijale();

		List<FilijalaDTO> filijaleDTO = new ArrayList<>();

		for (Filijala f : filijale) {
			FilijalaDTO filijalaDTO = new FilijalaDTO(f);
			filijaleDTO.add(filijalaDTO);
		}

		return new ResponseEntity<>(filijaleDTO, HttpStatus.OK);
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
	
	@RequestMapping(value = "/searchFil", method = RequestMethod.GET)
	public ResponseEntity<List<FilijalaDTO>> searchFilijala(@RequestParam(value = "name")String name){
		
		
		
		
		
		return null;
		
		
	}
	
	
	@RequestMapping(value = "/search", method = RequestMethod.GET)
	public ResponseEntity<List<RezervacijaRentACarDTO>> search(@RequestParam(value = "search",required=false)String name,@RequestParam(value = "search") String search,@RequestParam(value = "pickup") Date pickUp , @RequestParam(value = "dropoff") Date dropOff) {

			
		if (!rentACarService.exists(search)){
			return new ResponseEntity<>(HttpStatus.OK);
		}
		
		RentACar r = rentACarService.findByNaziv(search);

		
		List<CenovnikRentACar> cenovnikServisa = cenovnikService.findByServis(r);
		
		List<VoziloDTO> vozilaPretrage = new ArrayList<VoziloDTO>();
		
		List<FilijalaDTO> filijalaPretrage = new ArrayList<FilijalaDTO>();
		
		List<RezervacijaRentACarDTO> rezervacijePretrage = new ArrayList<RezervacijaRentACarDTO>();
		
		
		for (CenovnikRentACar c:cenovnikServisa) {
			
			 int odDatuma = c.getOdDatuma().compareTo(pickUp);
			 int doDatuma = c.getDoDatuma().compareTo(dropOff);
			 
			 
			 if ( odDatuma == -1 || odDatuma == 0 ) {
				 if ( doDatuma == 1 || doDatuma == 0 ) {
					 System.out.println("Postoji cenovnik za dati RANGE");
					 
					 Vozilo v = c.getVozilo();
					 
					 System.out.println("VOZILO "+ v.getNaziv() );
					 
					 List<RezervacijaRentACar> rezervacija= rezService.findByVoz(v);
		
					 int prolaz = 1 ;
					 
					 for(RezervacijaRentACar rez :rezervacija) {
						 
						 int pickUpBetween1  = rez.getDatumPreuz().compareTo(pickUp);
						 int pickUpBetween2  = rez.getDatumVracanja().compareTo(pickUp);

						 int dropOffBetween1 = rez.getDatumPreuz().compareTo(dropOff);
						 int dropOffBetween2 = rez.getDatumVracanja().compareTo(dropOff);
						 
						 int pick = pickUpBetween1*pickUpBetween2;
						 int drop = dropOffBetween1*dropOffBetween2;
						 
						 System.out.println("PICKUP "+ pick);
						 
						 System.out.println("DROPOFF "+ drop);
						 
						 int proizvod = 1;
						 
						 if (pick==-1 && drop==-1) {
							 proizvod = -1;
						 }else {
							proizvod = pick*drop;
						 }
						 
						 prolaz *=proizvod;
						 
						 if ( rez.getOtkazana() ) {
							 prolaz*= -1;
						 }
						  
					 }
					 
					 if (prolaz == 1) { 
						
						 System.out.println("Prolazi "+v.getNaziv());
						 
						 FilijalaDTO filVoz = new FilijalaDTO(v.getVozilo());
						 filijalaPretrage.add(filVoz);
						 
						long brojDana = dropOff.getTime()-pickUp.getTime();
						long diffDana = brojDana / (24 * 60 * 60 * 1000);
						System.out.println(diffDana);
						
						double cena = diffDana*c.getCena();
						
						RezervacijaRentACarDTO rezTemp = new RezervacijaRentACarDTO(null,null, pickUp, dropOff, cena, false, new FilijalaDTO(v.getVozilo()), new VoziloDTO(v) , null);
						
						rezervacijePretrage.add(rezTemp);
					 }
					 
					 
				 }
			 }
			 
			 
		}
		
		
			
		
		return new ResponseEntity<>(rezervacijePretrage,HttpStatus.OK);

	}

	


}
