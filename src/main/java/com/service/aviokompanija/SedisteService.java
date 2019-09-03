package com.service.aviokompanija;

import com.dto.aviokompanija.KartaDTO;
import com.dto.aviokompanija.SedisteDTO;
import com.model.aviokompanija.Karta;
import com.model.aviokompanija.Let;
import com.model.aviokompanija.Prtljag;
import com.model.aviokompanija.Sediste;
import com.repository.aviokompanija.KartaRepository;
import com.repository.aviokompanija.PrtljagRepository;
import com.repository.aviokompanija.SedisteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class SedisteService {

	@Autowired
	private SedisteRepository sedisteRepository;

	@Autowired
	private KartaRepository kartaRepository;

	@Autowired
	private PrtljagRepository prtljagRepository;

	private ListeDTO liste = new ListeDTO();

	public List<SedisteDTO> getAll(){
		List<Sediste> sedista = sedisteRepository.findAll();

		if(sedista.isEmpty())
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Sedista ne postoje");

		return liste.sedista(sedista);
	}



	public SedisteDTO findById(Long id){
		Optional<Sediste> sediste = sedisteRepository.findById(id);

		if(sediste.isPresent())
			return new SedisteDTO(sediste.get());

		throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Sediste ne postoji");
	}

	public SedisteDTO create(SedisteDTO sedisteDTO){
		Sediste sediste = new Sediste();

		sediste.setZauzeto(false);
		sediste.setRed(sedisteDTO.getRed());
		sediste.setKolona(sedisteDTO.getKolona());

		sedisteRepository.save(sediste);
		return sedisteDTO;
	}

	public SedisteDTO update(SedisteDTO sedisteDTO){
		Optional<Sediste> sediste = sedisteRepository.findById(sedisteDTO.getId());
		if (!sediste.isPresent())
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Sediste ne postoji");

		if(sedisteDTO.getKarta()!=null){
			Optional<Karta>karta=kartaRepository.findById(sedisteDTO.getKarta().getId());
			if(karta.isPresent()){
				sediste.get().setKarta(karta.get());
				karta.get().getSedista().add(sediste.get());
				kartaRepository.save(karta.get());
			}
		}

		if(sedisteDTO.getPrtljag()!=null){
			Optional<Prtljag>karta=prtljagRepository.findById(sedisteDTO.getKarta().getId());
			if(karta.isPresent()){
				sediste.get().setPrtljag(karta.get());
				karta.get().getSedista().add(sediste.get());
				prtljagRepository.save(karta.get());
			}
		}
		sediste.get().setZauzeto(sedisteDTO.getZauzeto());
		sediste.get().setRed(sedisteDTO.getRed());
		sediste.get().setKolona(sedisteDTO.getKolona());

		sedisteRepository.save(sediste.get());
		return new SedisteDTO(sediste.get());
	}

	public void delete(Long id){
		Optional<Sediste> sediste = sedisteRepository.findById(id);
		if (!sediste.isPresent())
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Sediste ne postoji");

		sedisteRepository.deleteById(id);
	}

	public KartaDTO napraviBrzuRezervaciju(Long id, double cena){
		Optional<Sediste> sediste = sedisteRepository.findById(id);
		if(!sediste.isPresent())
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Sediste ne postoji");

		if(sediste.get().getZauzeto())
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Sediste je vec zauzeto");

		if(sediste.get().getLet().getVremePolaska().after(new Date()))
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Avion je odleteo");

		Karta karta = new Karta();
		karta.getSedista().add(sediste.get());
		karta.setCena(cena);

		sediste.get().setKarta(karta);
		sediste.get().setZauzeto(true);

		kartaRepository.save(karta);
		sedisteRepository.save(sediste.get());

		return new KartaDTO(karta);

	}
}
