package com.service.aviokompanija;


import com.dto.aviokompanija.*;
import com.model.aviokompanija.*;
import com.model.user.User;
import com.repository.UserRepository;
import com.repository.aviokompanija.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
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
		aviokompanija.get().setAdresa(aviokompanijaDTO.getAdresa());
		aviokompanija.get().setOpis(aviokompanijaDTO.getOpis());
		aviokompanija.get().setProsecnaOcena(aviokompanijaDTO.getProsecnaOcena());

		if(aviokompanijaDTO.getLokacijaDTO()!= null && (aviokompanijaDTO.getLokacijaDTO().getId() != aviokompanija.get().getLokacija().getId())){
			Optional<Lokacija> lokacija = lokacijaRepository.findById(aviokompanijaDTO.getLokacijaDTO().getId());
			if(!lokacija.isPresent())
				throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Lokacija ne postoji");

			aviokompanija.get().setLokacija(lokacija.get());
			lokacija.get().getAviokompanije().add(aviokompanija.get());
			lokacijaRepository.save(lokacija.get());
		}

		aviokompanijaRepository.save(aviokompanija.get());
		return new AviokompanijaDTO(aviokompanija.get());
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
		konfiguracijaLetaRepository.save(konfiguracijaLeta);
		aviokompanija.get().getKonfiguracijaLeta().add(konfiguracijaLeta);
		aviokompanijaRepository.save(aviokompanija.get());

		return new KonfiguracijaLetaDTO(konfiguracijaLeta);
	}

	//Ubaciti proveru da li je on poslovao sa tom kompanijom
	public OcenaDTO oceni(Long id, Long userId, Integer ocena){
		if(ocena < 1 || ocena > 5)
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Ocena nije dobro prosledjena!");

		Optional<Aviokompanija> aviokompanija = aviokompanijaRepository.findById(id);
		if(!aviokompanija.isPresent())
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Aviokompanija ne postoji");

		Optional<User> user = userRepository.findById(userId);
		if(!user.isPresent())
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User ne postoji");

		for(Ocena oc : aviokompanija.get().getOcene()){
			if(oc.getUser().getId() == userId){
				oc.setOcena(ocena);
				ocenaRepository.save(oc);
				aviokompanija.get().setProsecnaOcena(proracunajSrednjuOcenu(aviokompanija.get()));
				aviokompanijaRepository.save(aviokompanija.get());
				return new OcenaDTO(oc);
			}
		}

		Ocena ocenaObj = new Ocena();
		ocenaObj.setAviokompanija(aviokompanija.get());
		ocenaObj.setOcena(ocena);
		ocenaObj.setUser(user.get());
		ocenaRepository.save(ocenaObj);

		aviokompanija.get().getOcene().add(ocenaObj);
		aviokompanija.get().setProsecnaOcena(proracunajSrednjuOcenu(aviokompanija.get()));
		aviokompanijaRepository.save(aviokompanija.get());

		return new OcenaDTO(ocenaObj);
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
}
