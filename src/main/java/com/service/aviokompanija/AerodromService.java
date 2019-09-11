package com.service.aviokompanija;

import com.dto.aviokompanija.AerodromDTO;
import com.dto.aviokompanija.LetDTO;
import com.dto.aviokompanija.LokacijaDTO;
import com.model.aviokompanija.Aerodrom;
import com.model.aviokompanija.Let;
import com.model.aviokompanija.Lokacija;
import com.model.user.User;
import com.repository.UserRepository;
import com.repository.aviokompanija.AerodromRepository;
import com.repository.aviokompanija.LetRepository;
import com.repository.aviokompanija.LokacijaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
public class AerodromService {

	@Autowired
	private AerodromRepository aerodromRepository;

	@Autowired
	private LetRepository letRepository;

	@Autowired
	private LokacijaRepository lokacijaRepository;

	@Autowired
	private UserRepository userRepository;
	private ListeDTO liste = new ListeDTO();

	public List<AerodromDTO> getAll(){
		List<Aerodrom> aerodromi = aerodromRepository.findAll();

		if(aerodromi.isEmpty())
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Aerodromi ne postoji");

		return liste.aerodromi(aerodromi);
	}

	public AerodromDTO findById(Long id){
		Optional<Aerodrom> aerodrom = aerodromRepository.findById(id);

		if(aerodrom.isPresent())
			return new AerodromDTO(aerodrom.get());

		throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Aerodrom ne postoji");
	}

	public AerodromDTO create(AerodromDTO aerodromDTO, String username){
		Optional<User> user=userRepository.findByUsername(username);
		if(!user.isPresent())
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User ne postoji");

		if(user.get().getAviokompanija()==null)
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "User nije admin aviokompanije");

		Aerodrom aerodrom = new Aerodrom();
		aerodrom.setNaziv(aerodromDTO.getNaziv());

		Lokacija l=new Lokacija();
		l.setGrad(aerodromDTO.getLokacija().getGrad());
		l.setAdresa(aerodromDTO.getLokacija().getAdresa());
		l.setDrzava(aerodromDTO.getLokacija().getDrzava());
		l=lokacijaRepository.save(l);
		aerodrom.setLokacija(l);
		aerodrom.setAviokompanija(user.get().getAviokompanija());

		aerodromRepository.save(aerodrom);
		return aerodromDTO;
	}

	public AerodromDTO update(AerodromDTO aerodromDTO, String username){
		Optional<User> user=userRepository.findByUsername(username);
		if(!user.isPresent())
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User ne postoji");



		Optional<Aerodrom> aerodrom = aerodromRepository.findById(aerodromDTO.getId());
		if (!aerodrom.isPresent())
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Aerodrom ne postoji");


		if(user.get().getAviokompanija()!=aerodrom.get().getAviokompanija())
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "User nije admin ove aviokompanije");

		aerodrom.get().setNaziv(aerodromDTO.getNaziv());

		if(aerodromDTO.getLokacija()!= null ){
			Optional<Lokacija> lokacija = lokacijaRepository.findById(aerodromDTO.getLokacija().getId());
			if(!lokacija.isPresent())
				throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Lokacija ne postoji");
			lokacija.get().setAdresa(aerodromDTO.getLokacija().getAdresa());
			lokacija.get().setDrzava(aerodromDTO.getLokacija().getDrzava());
			lokacija.get().setGrad(aerodromDTO.getLokacija().getGrad());
			lokacijaRepository.save(lokacija.get());
		}


		return new AerodromDTO(aerodromRepository.save(aerodrom.get()));
	}

	public void delete(Long id){
		Optional<Aerodrom> aerodrom = aerodromRepository.findById(id);
		if (!aerodrom.isPresent())
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Aerodrom ne postoji");

		aerodromRepository.deleteById(id);
	}

	public List<LetDTO> letovi(Long id){
		Optional<Aerodrom> aerodrom = aerodromRepository.findById(id);
		if(!aerodrom.isPresent())
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Aerodrom ne postoji");

		if(aerodrom.get().getLetovi().isEmpty())
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Letovi ne postoje");

		return liste.letovi(new ArrayList<>(aerodrom.get().getLetovi()));
	}

	public LetDTO let(Long id, LetDTO letDTO){
		Optional<Aerodrom> aerodrom = aerodromRepository.findById(id);
		if(!aerodrom.isPresent())
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Aerodrom ne postoji");

		Let let = new Let();
		let.setVremePutovanja(letDTO.getVremePutovanja());
		let.setPresedanja(letDTO.getPresedanja());
		let.setOpis(letDTO.getOpis());
		let.setDuzinaPutovanja(letDTO.getDuzinaPutovanja());
		let.setBrojPresedanja(let.getBrojPresedanja());
		let.setProsecnaOcena(2.5);
		let.setAerodrom(aerodrom.get());

		letRepository.save(let);

		aerodrom.get().getLetovi().add(let);
		aerodromRepository.save(aerodrom.get());

		return new LetDTO(let);
	}

	public LokacijaDTO postaviLokaciju(Long id, Long lokacijaId){
		Optional<Aerodrom> aerodrom = aerodromRepository.findById(id);
		if(!aerodrom.isPresent())
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Aerodrom ne postoji");

		Optional<Lokacija> lokacija = lokacijaRepository.findById(id);
		if(!lokacija.isPresent())
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Lokacija ne postoji");

		aerodrom.get().setLokacija(lokacija.get());
		lokacija.get().getAerodrom().add(aerodrom.get());

		aerodromRepository.save(aerodrom.get());
		lokacijaRepository.save(lokacija.get());

		return new LokacijaDTO(lokacija.get());
	}
}
