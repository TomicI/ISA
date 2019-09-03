package com.service.aviokompanija;

import com.dto.aviokompanija.LetDTO;
import com.dto.aviokompanija.OcenaDTO;
import com.model.aviokompanija.*;
import com.model.user.User;
import com.repository.UserRepository;
import com.repository.aviokompanija.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class LetService {

	@Autowired
	private LetRepository letRepository;

	@Autowired
	private LokacijaRepository lokacijaRepository;

	@Autowired
	private KonfiguracijaLetaRepository konfiguracijaLetaRepository;

	@Autowired
	private SedisteRepository sedisteRepository;

	@Autowired
	private SegmentRepository segmentRepository;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private OcenaRepository ocenaRepository;

	@Autowired
	private AerodromRepository aerodromRepository;

	private ListeDTO liste = new ListeDTO();

	public List<LetDTO> getAll(){
		List<Let> letovi = letRepository.findAll();

		if(letovi.isEmpty())
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Letovi ne postoji");

		return liste.letovi(letovi);
	}

	public LetDTO findById(Long id){
		Optional<Let> let = letRepository.findById(id);

		if(let.isPresent())
			return new LetDTO(let.get());

		throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Let ne postoji");
	}

	public LetDTO create(LetDTO letDTO){

		Let let = new Let();
		let.setBrojPresedanja(letDTO.getBrojPresedanja());
		let.setDuzinaPutovanja(letDTO.getDuzinaPutovanja());
		let.setOpis(letDTO.getOpis());
		let.setPresedanja(letDTO.getPresedanja());
		let.setVremePutovanja(letDTO.getVremePutovanja());
		let.setVremePolaska(letDTO.getVremePolaska());
		let.setVremeDolaska(letDTO.getVremeDolaska());
		let.setProsecnaOcena(0.0);
		let.setVrstaLeta(letDTO.getVrstaLeta());


		if(letDTO.getAerodrom() != null){
			Optional<Aerodrom> aerodrom = aerodromRepository.findById(letDTO.getAerodrom().getId());
			System.out.println("nije null aer");
			if(!aerodrom.isPresent())
				throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Aerodrom ne postoji");

			aerodrom.get().getLetovi().add(let);
			let.setAerodrom(aerodrom.get());
			aerodromRepository.save(aerodrom.get());
		}else{
			System.out.println("null je aer");
		}

		if(letDTO.getDestinacija() != null){
			Optional<Lokacija> lokacija = lokacijaRepository.findById(letDTO.getDestinacija().getId());
			System.out.println("nije null des");
			if(!lokacija.isPresent())
				throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Destinacija ne postoji");

			lokacija.get().getLetovi().add(let);
			let.setDestinacija(lokacija.get());
			lokacijaRepository.save(lokacija.get());
		}else{
			System.out.println("null je des");
		}

		if(letDTO.getKonfiguracijaLeta() != null){
			Optional<KonfiguracijaLeta> konfiguracija = konfiguracijaLetaRepository.findById(letDTO.getKonfiguracijaLeta().getId());
			System.out.println("nije null konf");
			if(!konfiguracija.isPresent())
				throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Konfiguracija ne postoji");

			konfiguracija.get().getLetovi().add(let);
			let.setKonfiguracija(konfiguracija.get());
			konfiguracijaLetaRepository.save(konfiguracija.get());


		}else{
			System.out.println("null je kof");
		}
		let=letRepository.save(let);
		formirajSedista(let);
		return new LetDTO(let);
	}

	public LetDTO update(LetDTO letDTO){
		Optional<Let> let = letRepository.findById(letDTO.getId());
		if (!let.isPresent())
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Let ne postoji");

		let.get().setBrojPresedanja(letDTO.getBrojPresedanja());
		let.get().setDuzinaPutovanja(letDTO.getDuzinaPutovanja());
		let.get().setOpis(letDTO.getOpis());
		let.get().setPresedanja(letDTO.getPresedanja());
		let.get().setVremePutovanja(letDTO.getVremePutovanja());
		let.get().setVremePolaska(letDTO.getVremePolaska());
		let.get().setVremeDolaska(letDTO.getVremeDolaska());
		let.get().setVrstaLeta(letDTO.getVrstaLeta());

		if(letDTO.getDestinacija()!= null && (letDTO.getDestinacija().getId() != let.get().getDestinacija().getId())){
			Optional<Lokacija> lokacija = lokacijaRepository.findById(letDTO.getDestinacija().getId());
			if(!lokacija.isPresent())
				throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Destinacija ne postoji");

			let.get().setDestinacija(lokacija.get());
			lokacija.get().getLetovi().add(let.get());
			lokacijaRepository.save(lokacija.get());
		}

		if(letDTO.getKonfiguracijaLeta()!= null && (letDTO.getKonfiguracijaLeta().getId() != let.get().getKonfiguracija().getId())){
			Optional<KonfiguracijaLeta> konfiguracijaLeta = konfiguracijaLetaRepository.findById(letDTO.getKonfiguracijaLeta().getId());
			if(!konfiguracijaLeta.isPresent())
				throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Konfiguracija ne postoji");

			let.get().setKonfiguracija(konfiguracijaLeta.get());
			konfiguracijaLeta.get().getLetovi().add(let.get());
			konfiguracijaLetaRepository.save(konfiguracijaLeta.get());

			formirajSedista(let.get());
		}

		letRepository.save(let.get());
		return new LetDTO(let.get());
	}

	public void delete(Long id){
		Optional<Let> let = letRepository.findById(id);
		if (!let.isPresent())
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Let ne postoji");

		letRepository.deleteById(id);
	}

	public OcenaDTO oceni(Long id, Long userId, Integer ocena){
		if(ocena < 1 || ocena > 5)
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Ocena nije dobro prosledjena!");

		Optional<Let> let = letRepository.findById(id);
		if(!let.isPresent())
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Aviokompanija ne postoji");

		Optional<User> user = userRepository.findById(userId);
		if(!user.isPresent())
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User ne postoji");

		for(Ocena oc : let.get().getOcene()){
			if(oc.getUser().getId() == userId){
				oc.setOcena(ocena);
				ocenaRepository.save(oc);
				let.get().setProsecnaOcena(proracunajSrednjuOcenu(let.get()));
				letRepository.save(let.get());
				return new OcenaDTO(oc);
			}
		}

		Ocena ocenaObj = new Ocena();
		ocenaObj.setLet(let.get());
		ocenaObj.setOcena(ocena);
		ocenaObj.setUser(user.get());
		ocenaRepository.save(ocenaObj);

		let.get().getOcene().add(ocenaObj);
		let.get().setProsecnaOcena(proracunajSrednjuOcenu(let.get()));
		letRepository.save(let.get());

		return new OcenaDTO(ocenaObj);
	}

	private void formirajSedista(Let let){
		for(Segment segment : let.getKonfiguracija().getSegmenti()){
			for(int i = 0; i < segment.getSirina(); i++){
				for(int j = 0; j < segment.getDuzina(); j++){
					Sediste sediste = new Sediste();
					sediste.setKolona(j);
					sediste.setRed(i);
					sediste.setZauzeto(false);
					sediste.setSegment(segment);
					sediste.setLet(let);
					sediste=sedisteRepository.save(sediste);
					segment.getSedista().add(sediste);
					let.getSedista().add(sediste);
				}
			}
			letRepository.save(let);
			segmentRepository.save(segment);
		}
	}

	private double proracunajSrednjuOcenu(Let let){
		double ocena = 0;
		for(Ocena oc : let.getOcene()){
			ocena += oc.getOcena();
		}

		return ocena / let.getOcene().size();
	}

	public List<LetDTO> pretraga(LetDTO letDTO) throws ParseException {
		List<LetDTO> letovi=new ArrayList<>();
		List<Let> svi=letRepository.findAll();
		System.out.println("aer " + letDTO.getOpis() + " dest " + letDTO.getPresedanja()+ "   vremep " + letDTO.getVremePolaska() + " vrem iz b " + svi.get(0).getVremePolaska());

		for(Let l: svi) {
			if(l.getAerodrom().getNaziv().equals(letDTO.getOpis()) && l.getDestinacija().getNaziv().equals(letDTO.getPresedanja()) && l.getVremePolaska().compareTo(letDTO.getVremePolaska())==0)  {
				LetDTO le=new LetDTO(l);
				System.out.println(" poklapa se " + l.getId());

				letovi.add(le);
			}
		}

		return letovi;
	}
}
