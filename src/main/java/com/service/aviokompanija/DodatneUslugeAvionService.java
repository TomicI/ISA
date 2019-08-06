package com.service.aviokompanija;

import com.dto.aviokompanija.DodatnaUslugaAviokompanijaDTO;
import com.model.aviokompanija.DodatnaUslugaAviokompanija;
import com.repository.aviokompanija.DodatnaUslugaAviokompanijaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class DodatneUslugeAvionService {

	@Autowired
	private DodatnaUslugaAviokompanijaRepository dodatneUslugeAvionRepository;

	private ListeDTO liste = new ListeDTO();

	public List<DodatnaUslugaAviokompanijaDTO> getAll(){
		List<DodatnaUslugaAviokompanija> dodatneUsluge = dodatneUslugeAvionRepository.findAll();

		if(dodatneUsluge.isEmpty())
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Dodatne usluge ne postoje");

		return liste.dodatneUsluge(dodatneUsluge);
	}

	public DodatnaUslugaAviokompanijaDTO findById(Long id){
		Optional<DodatnaUslugaAviokompanija> dodatnaUsluga = dodatneUslugeAvionRepository.findById(id);

		if(dodatnaUsluga.isPresent())
			return new DodatnaUslugaAviokompanijaDTO(dodatnaUsluga.get());

		throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Dodatna usluga ne postoji");
	}

	public DodatnaUslugaAviokompanijaDTO create(DodatnaUslugaAviokompanijaDTO dodatnaUslugaDTO){
		DodatnaUslugaAviokompanija dodatnaUsluga = new DodatnaUslugaAviokompanija();
		dodatnaUsluga.setOpis(dodatnaUslugaDTO.getOpis());
		dodatnaUsluga.setNaziv(dodatnaUslugaDTO.getNaziv());
		dodatnaUsluga.setCena(dodatnaUslugaDTO.getCena());

		dodatneUslugeAvionRepository.save(dodatnaUsluga);

		return dodatnaUslugaDTO;
	}

	public DodatnaUslugaAviokompanijaDTO update(DodatnaUslugaAviokompanijaDTO dodatnaUslugaDTO){
		Optional<DodatnaUslugaAviokompanija> dodatnaUsluga = dodatneUslugeAvionRepository.findById(dodatnaUslugaDTO.getId());
		if (!dodatnaUsluga.isPresent())
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Dodatna usluga ne postoji");

		dodatnaUsluga.get().setOpis(dodatnaUslugaDTO.getOpis());
		dodatnaUsluga.get().setNaziv(dodatnaUslugaDTO.getNaziv());
		dodatnaUsluga.get().setCena(dodatnaUslugaDTO.getCena());

		dodatneUslugeAvionRepository.save(dodatnaUsluga.get());
		return new DodatnaUslugaAviokompanijaDTO(dodatnaUsluga.get());
	}

	public void delete(Long id){
		Optional<DodatnaUslugaAviokompanija> dodatnaUsluga = dodatneUslugeAvionRepository.findById(id);
		if (!dodatnaUsluga.isPresent())
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Dodatna usluga ne postoji");

		dodatneUslugeAvionRepository.deleteById(id);
	}

}
