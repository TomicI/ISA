package com.service.aviokompanija;

import com.dto.RezervacijaDTO;
import com.dto.aviokompanija.KartaDTO;
import com.model.Rezervacija;
import com.model.aviokompanija.Karta;
import com.model.aviokompanija.Let;
import com.model.aviokompanija.Sediste;
import com.model.user.User;
import com.repository.RezervacijaRepository;
import com.repository.UserRepository;
import com.repository.aviokompanija.KartaRepository;
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
public class KartaService {

	@Autowired
	private KartaRepository kartaRepository;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private SedisteRepository sedisteRepository;

	@Autowired
	private RezervacijaRepository rezervacijaRepository;

	private ListeDTO liste = new ListeDTO();


	public List<KartaDTO> brzeRezervacije(){
		List<Karta> karte = kartaRepository.findAll();
		List<Karta> brzeRezervacije = new ArrayList<>();
		for(Karta karta : karte){
			if(karta.getRezervacija() == null)
				brzeRezervacije.add(karta);
		}

		return liste.karte(brzeRezervacije);
	}

	public RezervacijaDTO create(Long id, List<Long> sedista){
		Optional<User> user = userRepository.findById(id);
		if(!user.isPresent())
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User ne postoji");

		Rezervacija rezervacija = new Rezervacija();
		Karta karta = new Karta();
		karta.setCena(0);
		Let let = new Let();
		for(Long idSedista : sedista){
			Optional<Sediste> sediste = sedisteRepository.findById(idSedista);
			if(!sediste.isPresent())
				throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Sediste ne postoji");
			if(sediste.get().getZauzeto())
				throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Sediste je zauzeto");
			let = sediste.get().getLet();
			karta.getSedista().add(sediste.get());
			karta.setLet(let);
			karta=kartaRepository.save(karta);
			sediste.get().setZauzeto(true);
			sediste.get().setKarta(karta);
			sedisteRepository.save(sediste.get());
			karta.setCena(karta.getCena() + sediste.get().getSegment().getKategorija().getCena());
		}
		karta=kartaRepository.save(karta);
		rezervacija.setKarta(karta);

		rezervacija.setCena(karta.getCena());
		rezervacija.setDatumVremeP(let.getVremePolaska());
		rezervacija.setDatumVremeS(let.getVremeDolaska());
		rezervacija.setUser(user.get());
		rezervacija=rezervacijaRepository.save(rezervacija);

		karta.setRezervacija(rezervacija);
		kartaRepository.save(karta);

		return new RezervacijaDTO(rezervacija);
	}
}
