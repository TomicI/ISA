package com.service.aviokompanija;

import com.dto.aviokompanija.PrtljagDTO;
import com.model.aviokompanija.Prtljag;
import com.repository.aviokompanija.PrtljagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class PrtljagService {

	@Autowired
	private PrtljagRepository prtljagRepository;

	private ListeDTO liste = new ListeDTO();

	public List<PrtljagDTO> getAll(){
		List<Prtljag> prtljag = prtljagRepository.findAll();

		if(prtljag.isEmpty())
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Prtljazi ne postoji");

		return liste.prtljag(prtljag);
	}

	public PrtljagDTO findById(Long id){
		Optional<Prtljag> prtljag = prtljagRepository.findById(id);

		if(prtljag.isPresent())
			return new PrtljagDTO(prtljag.get());

		throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Prtljag ne postoji");
	}

	public PrtljagDTO create(PrtljagDTO prtljagDTO){
		Prtljag prtljag = new Prtljag();
		prtljag.setCena(prtljagDTO.getCena());
		prtljag.setTezina(prtljagDTO.getTezina());
		prtljag.setSirina(prtljagDTO.getSirina());
		prtljag.setDuzina(prtljagDTO.getDuzina());

		prtljagRepository.save(prtljag);
		return prtljagDTO;
	}

	public PrtljagDTO update(PrtljagDTO prtljagDTO){
		Optional<Prtljag> prtljag = prtljagRepository.findById(prtljagDTO.getId());
		if (!prtljag.isPresent())
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Prtljag ne postoji");

		prtljag.get().setCena(prtljagDTO.getCena());
		prtljag.get().setTezina(prtljagDTO.getTezina());
		prtljag.get().setSirina(prtljagDTO.getSirina());
		prtljag.get().setDuzina(prtljagDTO.getDuzina());

		prtljagRepository.save(prtljag.get());
		return new PrtljagDTO(prtljag.get());
	}

	public void delete(Long id){
		Optional<Prtljag> prtljag = prtljagRepository.findById(id);
		if (!prtljag.isPresent())
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Prtljag ne postoji");

		prtljagRepository.deleteById(id);
	}

}
