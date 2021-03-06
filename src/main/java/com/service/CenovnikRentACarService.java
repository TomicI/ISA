package com.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import com.dto.CenovnikRentACarDTO;
import com.model.Filijala;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.model.CenovnikRentACar;
import com.model.RentACar;
import com.model.Vozilo;
import com.repository.CenovnikRentACarRepository;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

@Service
public class CenovnikRentACarService {
	
	@Autowired
	private CenovnikRentACarRepository cenovnikRentACarRepository;

	@Autowired(required = true)
	private RentACarService rentACarService;

	@Autowired
	private VoziloService voziloService;
	
	public List<CenovnikRentACar> findAll(){
		return cenovnikRentACarRepository.findAll();
	}
	
	public Optional<CenovnikRentACar> findOne(Long id){
		return cenovnikRentACarRepository.findById(id);
	}
	
	public CenovnikRentACar save(CenovnikRentACar c) {
		return cenovnikRentACarRepository.save(c);
	}
	
	public void remove(Long id) {
		cenovnikRentACarRepository.deleteById(id);
	}
	
	public List<CenovnikRentACar> findByVozilo(Vozilo v){
		return cenovnikRentACarRepository.findByVozilo(v);
	}

	public List<CenovnikRentACar> findByVoziloId(Long id){
		return cenovnikRentACarRepository.findByVoziloId(id);
	}
	
	public List<CenovnikRentACar> findByServis(RentACar r){
		return cenovnikRentACarRepository.findByServis(r);
	}


	@Transactional(isolation = Isolation.SERIALIZABLE, propagation = Propagation.REQUIRES_NEW)
	public CenovnikRentACar insertCenovnik(CenovnikRentACarDTO cenovnikDTO){


		if (cenovnikDTO.getVoziloDTO()==null) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Cenovnik can't be null!");
		}

		if (cenovnikDTO.getRentACarDTO()==null){
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "RentACar can't be null!");
		}

		Vozilo vozilo = voziloService.getOne(cenovnikDTO.getVoziloDTO().getId());


		RentACar rentACar = rentACarService.getOne(cenovnikDTO.getRentACarDTO().getId());


		Filijala f = vozilo.getFilijala();

		RentACar r = f.getRentACar();

		if (rentACar.getId()!=r.getId()){
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Vehicle doesn't belong to this branch!");
		}

		CenovnikRentACar rent = new CenovnikRentACar();

		System.out.println(cenovnikDTO.getOdDatuma());

		rent.setOdDatuma(cenovnikDTO.getOdDatuma());
		rent.setDoDatuma(cenovnikDTO.getDoDatuma());
		rent.setCena(cenovnikDTO.getCena());
		rent.setVozilo(vozilo);
		rent.setServis(rentACar);

		return save(rent);

	}

	public CenovnikRentACar getOne(Long id){

		Optional<CenovnikRentACar> optionalCenovnik = findOne(id);

		if (!optionalCenovnik.isPresent()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Pricelist not found !");
		}

		return optionalCenovnik.get();
	}

	public Date getDate(Long id) {

		List<CenovnikRentACar> cenovnikRentACars = findByVoziloId(id);

		Date early = new Date();

		for (CenovnikRentACar c : cenovnikRentACars) {

			if (c.getDoDatuma().after(early)) {
				early = c.getDoDatuma();
			}

		}

		System.out.println(early);
		return early;
	}

	@Transactional(isolation = Isolation.SERIALIZABLE, propagation = Propagation.REQUIRES_NEW)
	public CenovnikRentACarDTO edit(CenovnikRentACarDTO cenovnikDTO){

		CenovnikRentACar rent = getOne(cenovnikDTO.getId());

		rent.setOdDatuma(cenovnikDTO.getOdDatuma());
		rent.setDoDatuma(cenovnikDTO.getDoDatuma());
		rent.setCena(cenovnikDTO.getCena());

		rent = save(rent);

		return new CenovnikRentACarDTO(rent);

	}

	public void deleteCenovnik(Long id ){

		getOne(id);

		remove(id);

	}

}
