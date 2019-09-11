package com.service.aviokompanija;

import com.dto.RezervacijaDTO;
import com.dto.aviokompanija.KartaDTO;
import com.dto.aviokompanija.PrtljagDTO;
import com.dto.aviokompanija.SedisteDTO;
import com.model.Rezervacija;
import com.model.aviokompanija.*;
import com.model.user.User;
import com.repository.RezervacijaRepository;
import com.repository.UserRepository;
import com.repository.aviokompanija.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.*;

@Service
public class KartaService {

	@Autowired
	private KartaRepository kartaRepository;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private SedisteRepository sedisteRepository;

	@Autowired
	private RezervacijaRepository rezervacijaRepository;

	@Autowired
	private PuntikRepository puntikRepository;

	@Autowired
	private AviokompanijaRepository aviokompanijaRepository;

	@Autowired
	private PrtljagRepository prtljagRepository;

	@Autowired
	private DodatnaUslugaAviokompanijaRepository dodatnaUslugaAviokompanijaRepository;

	private ListeDTO liste = new ListeDTO();


	public List<KartaDTO> brzeRezervacije(Long id){
		List<Karta> karte = kartaRepository.findAll();
		Optional<Aviokompanija> aviokompanija=aviokompanijaRepository.findById(id);
		List<Karta> brzeRezervacije = new ArrayList<>();
		if(aviokompanija.isPresent()) {
			for (Karta karta : karte) {
				if (karta.getRezervacija() == null && (karta.getLet().getKonfiguracija().getAviokompanija() == aviokompanija.get()) && karta.getSedista().size()==1 && karta.getPopust()>0)
					brzeRezervacije.add(karta);
			}
		}
		return liste.karte(brzeRezervacije);
	}

	@Transactional(isolation = Isolation.SERIALIZABLE, propagation = Propagation.REQUIRED)
	public RezervacijaDTO create(String username, List<SedisteDTO> sedista){
		Optional<User> user = userRepository.findByUsername(username);
		if(!user.isPresent())
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User ne postoji");

		Rezervacija rezervacija = new Rezervacija();
		Karta karta = new Karta();
		karta.setCena(0);
		double cena=0;
		boolean prvo=false;
		Let let = new Let();
		for(SedisteDTO sedisteDTO : sedista){
			Optional<Sediste> sediste = sedisteRepository.findById(sedisteDTO.getId());
			if(!sediste.isPresent())
				throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Sediste ne postoji");
			if(sediste.get().getZauzeto())
				throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Sediste je zauzeto");
			let = sediste.get().getLet();
			karta.getSedista().add(sediste.get());
			karta.setLet(let);
			karta=kartaRepository.save(karta);
			if(!prvo){

				Putnik p=new Putnik();
				Optional<Putnik> putnik=this.puntikRepository.findByUser(user.get());
				if(putnik.isPresent()){
					p=putnik.get();
				}else {
					p.setPrezime(user.get().getLastName());
					p.setIme(user.get().getFirstName());
					p.setBrojPasosa(user.get().getBrojPasosa());
					p.setUser(user.get());
				}
				if(p.getSedista()==null)
					p.setSedista(new HashSet<>());

				p.getSedista().add(sediste.get());
				prvo=true;
				p=puntikRepository.save(p);
				sediste.get().setPutnik(p);
			}
			if(sedisteDTO.getPrtljag()!=null){
				Optional<Prtljag> p=prtljagRepository.findById(sedisteDTO.getPrtljag().getId());
				if(p.isPresent()){
					sediste.get().setPrtljag(p.get());
					cena+=p.get().getCena();
				}
			}

			if(sedisteDTO.getDodatnaUslugaAviokompanija()!=null){
				Optional<DodatnaUslugaAviokompanija> p=dodatnaUslugaAviokompanijaRepository.findById(sedisteDTO.getDodatnaUslugaAviokompanija().getId());
				if(p.isPresent()){
					sediste.get().setDodatnaUslugaAviokompanija(p.get());
					cena+=p.get().getCena();
				}
			}
			sediste.get().setZauzeto(true);
			sediste.get().setKarta(karta);
			sedisteRepository.save(sediste.get());
			karta.setCena(karta.getCena() + sediste.get().getSegment().getKategorija().getCena()+cena);
		}
		karta=kartaRepository.save(karta);
		rezervacija.setKarta(karta);
		rezervacija.setOtkazana(false);
		rezervacija.setCena(karta.getCena());
		rezervacija.setDatumVremeP(let.getVremePolaska());
		rezervacija.setDatumVremeS(let.getVremeDolaska());
		rezervacija.setUser(user.get());
		rezervacija=rezervacijaRepository.save(rezervacija);


		karta.setRezervacija(rezervacija);
		kartaRepository.save(karta);

		return new RezervacijaDTO(rezervacija);
	}

	public List<SedisteDTO> getSedista(Long id){
		Optional<Karta> karta=kartaRepository.findById(id);

		if(!karta.isPresent())
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Karta ne postoji");
		if(karta.get().getSedista()==null)
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Sedista ne postoji");

		return liste.sedista(new ArrayList<>(karta.get().getSedista()));
	}

	public KartaDTO getKarta(Long id){
		Optional<Karta> karta=kartaRepository.findById(id);

		if(!karta.isPresent())
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Karta ne postoji");

		return new KartaDTO(karta.get());
	}

	@Transactional(isolation = Isolation.SERIALIZABLE, propagation = Propagation.REQUIRED)
	public KartaDTO createBR(String username, Long sedisteId, Double popust){
		Optional<User> user = userRepository.findByUsername(username);
		if(!user.isPresent())
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User ne postoji");


		Karta karta = new Karta();
		karta.setPopust(popust);
		Let let = new Let();
		Optional<Sediste> sediste = sedisteRepository.findById(sedisteId);
		if(!sediste.isPresent())
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Sediste ne postoji");
		if(sediste.get().getZauzeto())
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Sediste je zauzeto");
		let = sediste.get().getLet();
		karta.getSedista().add(sediste.get());
		karta.setLet(let);
		karta.setCena(sediste.get().getSegment().getKategorija().getCena());
		sediste.get().setPutnik(null);
		sediste.get().setZauzeto(true);

		karta.setRezervacija(null);
		karta=kartaRepository.save(karta);
		sediste.get().setKarta(karta);
		sedisteRepository.save(sediste.get());
		return new KartaDTO(karta);
	}

	public void delete(String username, Long kartaDTO){
		Optional<User> user = userRepository.findByUsername(username);
		if(!user.isPresent())
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User ne postoji");

		Optional<Karta> karta=kartaRepository.findById(kartaDTO);

		if(!karta.isPresent())
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Karta ne postoji");

		for(Sediste s: karta.get().getSedista()){
			s.setPutnik(null);
			s.setZauzeto(false);
			s.setKarta(null);
		}

		rezervacijaRepository.delete(karta.get().getRezervacija());
		kartaRepository.delete(karta.get());
	}

	public void cancel(Rezervacija rezervacija){

		Optional<Karta> karta=kartaRepository.findById(rezervacija.getKarta().getId());

		if(!karta.isPresent())
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Karta ne postoji");

		for(Sediste s: karta.get().getSedista()){
			s.setPutnik(null);
			s.setZauzeto(false);
			s.setKarta(null);
		}

		kartaRepository.save(karta.get());

	}


	@Transactional(isolation = Isolation.SERIALIZABLE, propagation = Propagation.REQUIRED)
	public RezervacijaDTO createRezB(String username, Long id){
		Optional<User> user=userRepository.findByUsername(username);
		if(!user.isPresent())
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User ne postoji");

		Optional<Karta> karta=kartaRepository.findById(id);

		if(!karta.isPresent())
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Karta ne postoji");

		if(karta.get().getSedista().size()!=1)
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Ovo nije brza rezervacija");

		Rezervacija rezervacija=new Rezervacija();
		rezervacija.setUser(user.get());
		rezervacija.setDatumVremeP(karta.get().getLet().getVremePolaska());
		rezervacija.setDatumVremeS(karta.get().getLet().getVremeDolaska());
		rezervacija.setKarta(karta.get());
		rezervacija=rezervacijaRepository.save(rezervacija);

		karta.get().setRezervacija(rezervacija);
		for(Sediste s: karta.get().getSedista()){

			if(s.getPutnik()!=null)
				throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Rezervisano vec");

			s.setKarta(karta.get());
			Putnik p=new Putnik();
			Optional<Putnik> putnik=this.puntikRepository.findByUser(user.get());
			if(putnik.isPresent()){
				p=putnik.get();
			}else {
				p.setPrezime(user.get().getLastName());
				p.setIme(user.get().getFirstName());
				p.setBrojPasosa(user.get().getBrojPasosa());
				p.setUser(user.get());
				p=puntikRepository.save(p);
			}
			s.setPutnik(p);
			sedisteRepository.save(s);
		}
		kartaRepository.save(karta.get());

		return new RezervacijaDTO(rezervacija);

	}
}
