package com.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import com.dto.CenovnikRentACarDTO;
import com.dto.FilijalaDTO;
import com.dto.RentACarDTO;
import com.model.CenovnikRentACar;
import com.model.Filijala;
import com.model.aviokompanija.Lokacija;
import com.model.aviokompanija.Ocena;
import com.repository.aviokompanija.LokacijaRepository;
import com.security.ResponseMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.model.RentACar;
import com.repository.RentACarRepository;
import org.springframework.web.server.ResponseStatusException;

@Service
public class RentACarService {
	
	@Autowired
	private RentACarRepository rentACarRepository;

	@Autowired
	private LokacijaRepository lokacijaRepository;

	@Autowired
	private FilijalaService filijalaService;
	
	public	RentACar findByNaziv(String naziv) {
		return rentACarRepository.findOneByNaziv(naziv);
	}

	public Optional<RentACar> findOne(Long id){
		return rentACarRepository.findById(id);
	}

	public RentACar getOne(Long id){
		Optional<RentACar> rentOptional =findOne(id);

		if (!rentOptional.isPresent()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "RentACar doesn't exist!");
		}

		return rentOptional.get();
	}

	public List<FilijalaDTO> findOneFilijala(Long id) {


		List<Filijala> filijale = getOne(id).getFilijale();

		List<FilijalaDTO> filijaleDTO = new ArrayList<>();

		Double sumOcena;
		int broj;

		for (Filijala f : filijale) {
			FilijalaDTO filijalaDTO = new FilijalaDTO(f);
			List<Ocena> ocene = f.getOcene();
			sumOcena = 0.0;
			broj = ocene.size();
			for(Ocena o : ocene){
				sumOcena += o.getOcena();
			}
			if (sumOcena > 0)
				filijalaDTO.setProsecnaOcena(sumOcena/broj);
			else
				filijalaDTO.setProsecnaOcena(0.0);
			filijaleDTO.add(filijalaDTO);
		}

		return filijaleDTO;

	}
	
	public List<RentACar> findAll(){
		return rentACarRepository.findAll();
	}

	public List<RentACarDTO> getAll() {

		List<RentACar> rentACar = findAll();

		List<RentACarDTO> rentACarDTO = new ArrayList<>();

		for (RentACar r : rentACar) {
			rentACarDTO.add(new RentACarDTO(r));
		}

		return rentACarDTO;
	}
	
	public RentACar save(RentACar rentACar) {
		return rentACarRepository.save(rentACar);
	}

	public RentACar saveRentACar(RentACarDTO rentACarDTO){
		RentACar rentACar = new RentACar();
		rentACar.setId(rentACarDTO.getId());
		rentACar.setNaziv(rentACarDTO.getNaziv());
		rentACar.setOpis(rentACarDTO.getOpis());

		return save(rentACar);

	}
	
	public void remove(Long id) {

		rentACarRepository.deleteById(id);
	}

	public List<CenovnikRentACarDTO> getCenovnici(Long id){
		List<CenovnikRentACar> cenovnik = getOne(id).getCenovnici();

		List<CenovnikRentACarDTO> cenovnikDTO = new ArrayList<CenovnikRentACarDTO>();

		for (CenovnikRentACar c : cenovnik) {
			System.out.println(c.getOdDatuma());
			CenovnikRentACarDTO tempCDTO = new CenovnikRentACarDTO(c);
			cenovnikDTO.add(tempCDTO);
		}

		return cenovnikDTO;
	}

	public RentACar edit(RentACarDTO rentACarDTO){

		if (exists(rentACarDTO.getNaziv())) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Name is not available!");
		}

		if (rentACarDTO.getOpis().length() < 5) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Minimum 5 characters!");
		}


		RentACar rentaACar = getOne(rentACarDTO.getId());

		rentaACar.setNaziv(rentACarDTO.getNaziv());
		rentaACar.setOpis(rentACarDTO.getOpis());

		return save(rentaACar);
	}


	public boolean exists(String name){
		return rentACarRepository.existsByNaziv(name);
	}




	public List<RentACarDTO> findByLokacijaFilijale(String grad, String drzava){
		List<Lokacija> lokacijas=lokacijaRepository.findByGradAndDrzava(grad, drzava);
		List<RentACar> servisi=new ArrayList<>();
		List<RentACarDTO> servisiDTO=new ArrayList<>();

		for(Lokacija l :lokacijas){
			Filijala filijala=filijalaService.findByLokacija(l.getId());
			if(filijala!=null && !servisi.contains(filijala.getRentACar())){
				servisi.add(filijala.getRentACar());
				servisiDTO.add(new RentACarDTO(filijala.getRentACar()));
			}

		}

		return servisiDTO;
	}

}
