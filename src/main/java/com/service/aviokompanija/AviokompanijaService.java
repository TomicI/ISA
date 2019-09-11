package com.service.aviokompanija;


import com.dto.aviokompanija.*;
import com.model.Rezervacija;
import com.model.aviokompanija.*;
import com.model.user.User;
import com.repository.UserRepository;
import com.repository.aviokompanija.*;
import com.service.RezervacijaService;
import com.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;


@Service
public class AviokompanijaService {
	
	@Autowired
	private AviokompanijaRepository aviokompanijaRepository;

	@Autowired
	private AerodromRepository aerodromRepository;

	@Autowired
	private PrtljagRepository prtljagRepository;

	@Autowired
	private KonfiguracijaLetaRepository konfiguracijaLetaRepository;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private OcenaRepository ocenaRepository;

	@Autowired
	private LokacijaRepository lokacijaRepository;

	@Autowired
	private DodatnaUslugaAviokompanijaRepository dodatnaUslugaAviokompanijaRepository;

	@Autowired
	private UserService userService;

	@Autowired
	private RezervacijaService rezervacijaService;

	private ListeDTO liste = new ListeDTO();

	public List<AviokompanijaDTO> getAll(){
		List<Aviokompanija> aviokompanije = aviokompanijaRepository.findAll();

		if(aviokompanije.isEmpty())
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Aviokompanije ne postoje");

		return liste.aviokompanije(aviokompanije);
	}

	public AviokompanijaDTO findById(Long id){
		Optional<Aviokompanija> aviokompanija = aviokompanijaRepository.findById(id);

		if(aviokompanija.isPresent())
			return new AviokompanijaDTO(aviokompanija.get());

		throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Aviokompanija ne postoji");
	}

	public Aviokompanija findOne(Long id){
		Optional<Aviokompanija> aviokompanija = aviokompanijaRepository.findById(id);

		if(aviokompanija.isPresent())
			return aviokompanija.get();

		throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Aviokompanija ne postoji");
	}


	public AviokompanijaDTO create(AviokompanijaDTO aviokompanijaDTO){
		Aviokompanija aviokompanija = new Aviokompanija();
		aviokompanija.setNaziv(aviokompanijaDTO.getNaziv());
		aviokompanija.setAdresa(aviokompanijaDTO.getAdresa());
		aviokompanija.setOpis(aviokompanijaDTO.getOpis());
		aviokompanija.setProsecnaOcena(2.5);

		if(aviokompanijaDTO.getLokacijaDTO()!= null){
			Optional<Lokacija> lokacija = lokacijaRepository.findById(aviokompanijaDTO.getLokacijaDTO().getId());
			if(!lokacija.isPresent())
				throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Lokacija ne postoji");

			aviokompanija.setLokacija(lokacija.get());
			lokacija.get().getAviokompanije().add(aviokompanija);
			lokacijaRepository.save(lokacija.get());
		}

		aviokompanijaRepository.save(aviokompanija);

		return aviokompanijaDTO;
	}

	public AviokompanijaDTO update(AviokompanijaDTO aviokompanijaDTO){
		Optional<Aviokompanija> aviokompanija = aviokompanijaRepository.findById(aviokompanijaDTO.getId());
		if (!aviokompanija.isPresent())
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Aviokompanija ne postoji");

		aviokompanija.get().setNaziv(aviokompanijaDTO.getNaziv());
		aviokompanija.get().setOpis(aviokompanijaDTO.getOpis());


		if(aviokompanijaDTO.getLokacijaDTO()!= null){
			Optional<Lokacija> lokacija = lokacijaRepository.findById(aviokompanijaDTO.getLokacijaDTO().getId());
			if(!lokacija.isPresent())
				throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Lokacija ne postoji");

			lokacija.get().setDrzava(aviokompanijaDTO.getLokacijaDTO().getDrzava());
			lokacija.get().setAdresa(aviokompanijaDTO.getLokacijaDTO().getAdresa());
			lokacija.get().setGrad(aviokompanijaDTO.getLokacijaDTO().getGrad());
			lokacijaRepository.save(lokacija.get());
		}


		return new AviokompanijaDTO(aviokompanijaRepository.save(aviokompanija.get()));
	}

	public void delete(Long id){
		Optional<Aviokompanija> aviokompanija = aviokompanijaRepository.findById(id);
		if (!aviokompanija.isPresent())
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Aviokompanija ne postoji");

		aviokompanijaRepository.deleteById(id);
	}

	public List<AerodromDTO> aerodromi(Long id){
		Optional<Aviokompanija> aviokompanija = aviokompanijaRepository.findById(id);
		if(!aviokompanija.isPresent())
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Aviokompanija ne postoji");
		return liste.aerodromi(new ArrayList<>(aviokompanija.get().getAerodromi()));
	}

	public AerodromDTO napraviAerodrom(Long id, AerodromDTO aerodromDTO){
		Optional<Aviokompanija> aviokompanija = aviokompanijaRepository.findById(id);
		if(!aviokompanija.isPresent())
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Aviokompanija ne postoji");

		Aerodrom aerodrom = new Aerodrom();
		aerodrom.setNaziv(aerodromDTO.getNaziv());
		aerodrom.setAviokompanija(aviokompanija.get());
		aerodromRepository.save(aerodrom);
		aviokompanija.get().getAerodromi().add(aerodrom);
		aviokompanijaRepository.save(aviokompanija.get());

		return new AerodromDTO(aerodrom);
	}

	public List<LokacijaDTO> destinacije(Long id){
		Optional<Aviokompanija> aviokompanija = aviokompanijaRepository.findById(id);
		if(!aviokompanija.isPresent())
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Aviokompanija ne postoji");

		List<Lokacija> destinacije = new ArrayList<>();

		for(Aerodrom aerodrom : aviokompanija.get().getAerodromi()){
			for(Let let : aerodrom.getLetovi()){
				if(!destinacije.contains(let.getDestinacija()))
					destinacije.add(let.getDestinacija());
			}
		}

		if(destinacije.isEmpty())
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Destinacije ne postoje");
		return liste.lokacije(destinacije);
	}

	public List<PrtljagDTO> prtljag(Long id){
		Optional<Aviokompanija> aviokompanija = aviokompanijaRepository.findById(id);
		if(!aviokompanija.isPresent())
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Aviokompanija ne postoji");

		if(aviokompanija.get().getPrtljag().isEmpty())
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Prtljag ne postoji");

		return liste.prtljag(new ArrayList<>(aviokompanija.get().getPrtljag()));
	}

	public List<DodatnaUslugaAviokompanijaDTO> dodatneUsluge(Long id){
		Optional<Aviokompanija> aviokompanija = aviokompanijaRepository.findById(id);
		if(!aviokompanija.isPresent())
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Aviokompanija ne postoji");

		if(aviokompanija.get().getDodatneUsluge().isEmpty())
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Dodatne usluge ne postoje");

		return liste.dodatneUsluge(new ArrayList<>(aviokompanija.get().getDodatneUsluge()));
	}

	public DodatnaUslugaAviokompanijaDTO napraviDodatnuUslugu(Long id, DodatnaUslugaAviokompanijaDTO dodatnaUslugaAviokompanijaDTO){
		Optional<Aviokompanija> aviokompanija = aviokompanijaRepository.findById(id);
		if(!aviokompanija.isPresent())
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Aviokompanija ne postoji");

		DodatnaUslugaAviokompanija dodatnaUslugaAviokompanija = new DodatnaUslugaAviokompanija();
		dodatnaUslugaAviokompanija.setCena(dodatnaUslugaAviokompanijaDTO.getCena());
		dodatnaUslugaAviokompanija.setNaziv(dodatnaUslugaAviokompanijaDTO.getNaziv());
		dodatnaUslugaAviokompanija.setOpis(dodatnaUslugaAviokompanijaDTO.getOpis());
		dodatnaUslugaAviokompanija.setAviokompanija(aviokompanija.get());
		dodatnaUslugaAviokompanijaRepository.save(dodatnaUslugaAviokompanija);
		aviokompanija.get().getDodatneUsluge().add(dodatnaUslugaAviokompanija);
		aviokompanijaRepository.save(aviokompanija.get());

		return new DodatnaUslugaAviokompanijaDTO(dodatnaUslugaAviokompanija);
	}

	public PrtljagDTO napraviPrtljag(Long id, PrtljagDTO prtljagmDTO){
		Optional<Aviokompanija> aviokompanija = aviokompanijaRepository.findById(id);
		if(!aviokompanija.isPresent())
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Aviokompanija ne postoji");

		Prtljag prtljag = new Prtljag();
		prtljag.setDuzina(prtljagmDTO.getDuzina());
		prtljag.setSirina(prtljagmDTO.getSirina());
		prtljag.setTezina(prtljagmDTO.getTezina());
		prtljag.setCena(prtljagmDTO.getCena());
		prtljagRepository.save(prtljag);
		aviokompanija.get().getPrtljag().add(prtljag);
		aviokompanijaRepository.save(aviokompanija.get());

		return new PrtljagDTO(prtljag);
	}

	public List<LetDTO> letovi(Long id){
		Optional<Aviokompanija> aviokompanija = aviokompanijaRepository.findById(id);
		if(!aviokompanija.isPresent())
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Aviokompanija ne postoji");

		List<Let> letovi = new ArrayList<>();

		for(Aerodrom aerodrom : aviokompanija.get().getAerodromi()){
			letovi.addAll(aerodrom.getLetovi());
		}

		if(letovi.isEmpty())
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Letovi ne postoje");

		return liste.letovi(letovi);
	}

	public List<KonfiguracijaLetaDTO> konfiguracije(Long id){
		Optional<Aviokompanija> aviokompanija = aviokompanijaRepository.findById(id);
		if(!aviokompanija.isPresent())
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Aviokompanija ne postoji");

		if(aviokompanija.get().getKonfiguracijaLeta().isEmpty())
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Konfiguracije ne postoje");

		return liste.konfiguracije(new ArrayList<>(aviokompanija.get().getKonfiguracijaLeta()));
	}

	public KonfiguracijaLetaDTO napraviKonfiguraciju(Long id, KonfiguracijaLetaDTO konfiguracijaLetaDTO){
		Optional<Aviokompanija> aviokompanija = aviokompanijaRepository.findById(id);
		if(!aviokompanija.isPresent())
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Aviokompanija ne postoji");

		KonfiguracijaLeta konfiguracijaLeta = new KonfiguracijaLeta();
		konfiguracijaLeta.setNaziv(konfiguracijaLetaDTO.getNaziv());
		konfiguracijaLeta.setAviokompanija(aviokompanija.get());
		konfiguracijaLetaRepository.save(konfiguracijaLeta);
		aviokompanija.get().getKonfiguracijaLeta().add(konfiguracijaLeta);
		aviokompanijaRepository.save(aviokompanija.get());

		return new KonfiguracijaLetaDTO(konfiguracijaLeta);
	}

	public long ratePermission(Rezervacija rezervacija){

		Aviokompanija aviokompanija = findOne(rezervacija.getKarta().getLet().getAerodrom().getAviokompanija().getId());

		return aviokompanija.getId();

	}

	//Ubaciti proveru da li je on poslovao sa tom kompanijom
	@Transactional(isolation = Isolation.SERIALIZABLE, propagation = Propagation.REQUIRED)
	public Ocena oceni(Long id, User user, Integer ocena,Long res_id){
		if(ocena < 1 || ocena > 5)
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Ocena nije dobro prosledjena!");

		Optional<Aviokompanija> aviokompanija = aviokompanijaRepository.findById(id);
		if(!aviokompanija.isPresent())
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Aviokompanija ne postoji");

		Rezervacija rezervacija = rezervacijaService.getOne(res_id);

		Ocena ocenaObj = new Ocena();
		ocenaObj.setAviokompanija(aviokompanija.get());
		ocenaObj.setOcena(ocena);
		ocenaObj.setUser(user);
		ocenaObj.setOcDate(new Date());
		ocenaObj.setRezervacija(rezervacija);
		ocenaRepository.save(ocenaObj);

		aviokompanija.get().getOcene().add(ocenaObj);
		aviokompanija.get().setProsecnaOcena(proracunajSrednjuOcenu(aviokompanija.get()));
		aviokompanijaRepository.save(aviokompanija.get());

		return ocenaObj;
	}

	public LokacijaDTO postaviLokaciju(Long id, Long lokacijaId){
		Optional<Aviokompanija> aviokompanija = aviokompanijaRepository.findById(id);
		if(!aviokompanija.isPresent())
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Aerodrom ne postoji");

		Optional<Lokacija> lokacija = lokacijaRepository.findById(id);
		if(!lokacija.isPresent())
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Lokacija ne postoji");

		aviokompanija.get().setLokacija(lokacija.get());
		lokacija.get().getAviokompanije().add(aviokompanija.get());

		aviokompanijaRepository.save(aviokompanija.get());
		lokacijaRepository.save(lokacija.get());

		return new LokacijaDTO(lokacija.get());
	}



	private double proracunajSrednjuOcenu(Aviokompanija aviokompanija){
		double ocena = 0;
		for(Ocena oc : aviokompanija.getOcene()){
			ocena += oc.getOcena();
		}

		return ocena / aviokompanija.getOcene().size();
	}

	public AviokompanijaDTO findByAdmin(String username){
		Optional<User> user=userRepository.findByUsername(username);
		if(user.isPresent() && user.get().getAviokompanija()!=null){
			return new AviokompanijaDTO(user.get().getAviokompanija());

		}


		throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Aviokompanija ne postoji");
	}

	public List<LetDTO> letoviAdmin(String username){
		Optional<User> user=userRepository.findByUsername(username);
		if(!user.isPresent())
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User ne postoji");

		if(user.get().getAviokompanija()==null)
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User nema aviokompaniju");

		Optional<Aviokompanija> aviokompanija = aviokompanijaRepository.findById(user.get().getAviokompanija().getId());
		if(!aviokompanija.isPresent())
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Aviokompanija ne postoji");

		List<Let> letovi = new ArrayList<>();


		for(KonfiguracijaLeta konfiguracijaLeta : aviokompanija.get().getKonfiguracijaLeta()){
			letovi.addAll(konfiguracijaLeta.getLetovi());
		}

		if(letovi.isEmpty())
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Letovi ne postoje");

		return liste.letovi(letovi);
	}
}
