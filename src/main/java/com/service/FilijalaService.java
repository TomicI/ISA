package com.service;

import java.util.*;

import com.dto.FilijalaDTO;
import com.dto.RezervacijaRentACarDTO;
import com.dto.VoziloDTO;
import com.dto.aviokompanija.OcenaDTO;
import com.model.*;
import com.model.aviokompanija.Ocena;
import com.model.user.User;
import com.security.ResponseMessage;
import com.service.aviokompanija.OcenaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.repository.FilijalaRepository;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;


@Service
public class FilijalaService {

    @Autowired
    private FilijalaRepository filijalaRepository;

    @Autowired
    private RentACarService rentACarService;

    @Autowired
    private VoziloService voziloService;

    @Autowired
    private CenovnikRentACarService cenovnikRentACarService;

    @Autowired
    private RezervacijaRentACarService rezervacijaRentACarService;

    @Autowired
    private RezervacijaService rezervacijaService;

    @Autowired
    private UserService userService;

    @Autowired
    private OcenaService ocenaService;

    public Optional<Filijala> findOne(Long id) {
        return filijalaRepository.findById(id);
    }

    public Filijala getOne(Long id){
        Optional<Filijala> filijalaOptional = findOne(id);

        if (!filijalaOptional.isPresent()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Filijala doesn't exist!");
        }

        return filijalaOptional.get();
    }

    public List<VoziloDTO> findOneVeh(Long id) {

        Set<Vozilo> vozila = getOne(id).getVozila();

        List<VoziloDTO> vozilaDTO = new ArrayList<VoziloDTO>();



        for (Vozilo v : vozila) {
            VoziloDTO voziloDTO = new VoziloDTO(v);

            voziloDTO.setProsecnaOcena(average(v));

            vozilaDTO.add(voziloDTO);
        }

        return vozilaDTO;

    }


    public List<RezervacijaRentACarDTO> search(String locationp, String bring, Date pickUp, Date dropOff) {

        if (!existsByAdresa(locationp)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Branch doesn't exist!");
        }

        if (!existsByAdresa(bring)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Branch doesn't exist!");
        }

        Filijala filSearch = find(locationp);

        Filijala filBring = find(bring);

        RentACar r = rentACarService.findByNaziv(filSearch.getFilijala().getNaziv());


        List<CenovnikRentACar> cenovnikServisa = cenovnikRentACarService.findByServis(r);

        List<VoziloDTO> vozilaPretrage = new ArrayList<VoziloDTO>();

        List<FilijalaDTO> filijalaPretrage = new ArrayList<FilijalaDTO>();

        List<RezervacijaRentACarDTO> rezervacijePretrage = new ArrayList<RezervacijaRentACarDTO>();


        List<Vozilo> vozila = voziloService.findAll();

        for (Vozilo v : vozila) {
            if (v.getVozilo().equals(filSearch)) {

                int prolaz = 1;

                List<RezervacijaRentACar> listRezervacija = rezervacijaRentACarService.findByVoz(v);

                for (RezervacijaRentACar rez : listRezervacija) {

                    if (!rez.getOtkazana()) {
                        int pickUpBetween1 = rez.getDatumPreuz().compareTo(pickUp);
                        int pickUpBetween2 = rez.getDatumVracanja().compareTo(pickUp);

                        int dropOffBetween1 = rez.getDatumPreuz().compareTo(dropOff);
                        int dropOffBetween2 = rez.getDatumVracanja().compareTo(dropOff);

                        int pick = pickUpBetween1 * pickUpBetween2;
                        int drop = dropOffBetween1 * dropOffBetween2;

                        System.out.println("PICKUP " + pick);

                        System.out.println("DROPOFF " + drop);

                        int proizvod = 1;

                        if (pick == -1 && drop == -1) {
                            proizvod = -1;
                        } else {
                            proizvod = pick * drop;
                        }

                        prolaz *= proizvod;

                    }

                }

                if (prolaz == 1) {
                    System.out.println("AUTO " + v.getNaziv());

                    List<CenovnikRentACar> cenovnikV = v.getCenovnik();

                    Double cen = 0.0;

                    Calendar cal = Calendar.getInstance();
                    cal.setTime(pickUp);
                    Date temp = cal.getTime();

                    for (CenovnikRentACar c : cenovnikV) {

                        System.out.println("Od datuma " + c.getOdDatuma());
                        System.out.println("Do datuma " + c.getDoDatuma());

                        System.out.println("Temp " + temp);
                        while (temp.before(c.getDoDatuma()) && temp.before(dropOff)) {
                            System.out.println("Cena " + temp + " " + c.getCena());
                            cen += c.getCena();

                            cal.setTime(temp);
                            cal.add(Calendar.DATE, 1);
                            temp = cal.getTime();
                            System.out.println("TEMPO " + temp);
                            System.out.println(temp.compareTo(dropOff));

                            if (temp.compareTo(dropOff) == 0 || temp.compareTo(dropOff) == 1) {
                                System.out.println("CENA " + cen);

                                VoziloDTO voziloDTO = new VoziloDTO(v);
                                voziloDTO.setProsecnaOcena(average(v));

                                RezervacijaRentACarDTO rezTemp = new RezervacijaRentACarDTO(null, null, pickUp, dropOff, cen, false, StatusRes.Reserved, new FilijalaDTO(filSearch), new FilijalaDTO(filBring), voziloDTO);
                                rezervacijePretrage.add(rezTemp);
                                break;
                            }
                        }
                    }
                }


            }
        }


/*		for (CenovnikRentACar c:cenovnikServisa) {

			 int odDatuma = c.getOdDatuma().compareTo(pickUp);
			 int doDatuma = c.getDoDatuma().compareTo(dropOff);


			 if ( odDatuma == -1 || odDatuma == 0 ) {
				 if ( doDatuma == 1 || doDatuma == 0 ) {
					 System.out.println("Postoji cenovnik za dati RANGE");

					 if (c.getVozilo().getVozilo().equals(filSearch)) {

					 Vozilo v = c.getVozilo();

					 System.out.println("VOZILO "+ v.getNaziv() );

					 List<RezervacijaRentACar> rezervacija= rezService.findByVoz(v);

					 int prolaz = 1 ;

					 for(RezervacijaRentACar rez :rezervacija) {

						 int pickUpBetween1  = rez.getDatumPreuz().compareTo(pickUp);
						 int pickUpBetween2  = rez.getDatumVracanja().compareTo(pickUp);

						 int dropOffBetween1 = rez.getDatumPreuz().compareTo(dropOff);
						 int dropOffBetween2 = rez.getDatumVracanja().compareTo(dropOff);

						 int pick = pickUpBetween1*pickUpBetween2;
						 int drop = dropOffBetween1*dropOffBetween2;

						 System.out.println("PICKUP "+ pick);

						 System.out.println("DROPOFF "+ drop);

						 int proizvod = 1;

						 if (pick==-1 && drop==-1) {
							 proizvod = -1;
						 }else {
							proizvod = pick*drop;
						 }

						 prolaz *=proizvod;

						 if ( rez.getOtkazana() ) {
							 prolaz*= -1;
						 }

					 }

					 if (prolaz == 1) {

						 System.out.println("Prolazi "+v.getNaziv());

						 FilijalaDTO filVoz = new FilijalaDTO(v.getVozilo());
						 filijalaPretrage.add(filVoz);

						long brojDana = dropOff.getTime()-pickUp.getTime();
						System.out.println("BrojDana " + brojDana);
						long diffDana = brojDana / (24 * 60 * 60 * 1000);

						double cena = diffDana*c.getCena();

						RezervacijaRentACarDTO rezTemp = new RezervacijaRentACarDTO(null,null,pickUp,dropOff,cena,false,StatusRes.Reserved,new FilijalaDTO(filSearch),new FilijalaDTO(filBring),new VoziloDTO(v), null);


						rezervacijePretrage.add(rezTemp);
					 }

					}

				 }
			 }


		}*/


        return rezervacijePretrage;

    }

    public double average(Vozilo v){

        Double sumOcena;
        int broj;

        List<Ocena> ocene = v.getOcene();
        sumOcena = 0.0;
        broj = ocene.size();
        for (Ocena o : ocene) {
            sumOcena += o.getOcena();
        }

        if (sumOcena > 0)
            return (sumOcena / broj);

        return 0;
    }

    public boolean ratePermission(Long resid,Long filid){

        Rezervacija rezervacija = rezervacijaService.getOne(resid);
        Filijala filijala = getOne(filid);
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.getByUsername(auth.getName());

        if (rezervacija.getDatumVremeS().after(new Date())){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Reservation didn't end!");
        }

        if (ocenaService.findByRezervacijaAndFilijala(rezervacija.getId(),filijala.getId(),user.getId()).isPresent()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Branch already rated!");
        }

        return true;

    }

    @Transactional(isolation = Isolation.SERIALIZABLE, propagation = Propagation.REQUIRES_NEW)
    public ResponseMessage rateFilijala(OcenaDTO ocenaDTO){

        Rezervacija rezervacija = rezervacijaService.getOne(ocenaDTO.getRezervacijaDTO().getId());
        Filijala filijala = getOne(ocenaDTO.getFilijalaDTO().getId());

        if (rezervacija.getDatumVremeS().after(new Date())){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Reservation didn't end!");
        }

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.getByUsername(auth.getName());

        if (ocenaService.findByRezervacijaAndFilijala(rezervacija.getId(),filijala.getId(),user.getId()).isPresent()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Branch already rated!");
        }

        Ocena ocena = new Ocena();

        ocena.setOcena(ocenaDTO.getOcena());
        ocena.setUser(user);
        ocena.setOcDate(new Date());
        ocena.setRezervacija(rezervacija);
        ocena.setFilijala(filijala);

        ocenaService.saveOcena(ocena);

        return new ResponseMessage("Branch rate saved!");

    }

    public OcenaDTO getRate(Long resid,Long filid){

        Rezervacija rezervacija = rezervacijaService.getOne(resid);
        Filijala filijala = getOne(filid);


        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.getByUsername(auth.getName());

        Optional<Ocena> optionalOcena = ocenaService.findByRezervacijaAndFilijala(rezervacija.getId(),filijala.getId(),user.getId());

        if (optionalOcena.isPresent()){

            OcenaDTO ocenaDTO = new OcenaDTO();
            ocenaDTO.setOcena(optionalOcena.get().getOcena());
            ocenaDTO.setFilijalaDTO(new FilijalaDTO(filijala));

            return ocenaDTO;
        }

        return null;

    }


    public Filijala insert(FilijalaDTO filijalaDTO){

        if (filijalaDTO.getRentACarDTO()==null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Branch can't be null!");
        }

        RentACar rentACar = rentACarService.getOne(filijalaDTO.getRentACarDTO().getId());

        Filijala filijala = new Filijala();
        filijala.setAdresa(filijalaDTO.getAdresa());
        filijala.setFilijala(rentACar);

        return save(filijala);
    }



    public Filijala edit(FilijalaDTO filijalaDTO){
        Filijala filijala = getOne(filijalaDTO.getId());

        filijala.setAdresa(filijalaDTO.getAdresa());

        return save(filijala);
    }

    public List<Filijala> findAll() {
        return filijalaRepository.findAll();
    }

    public Filijala save(Filijala f) {
        return filijalaRepository.save(f);
    }

    public void remove(Long id) {
        filijalaRepository.deleteById(id);
    }

    public Filijala find(String adresa) {
        return filijalaRepository.findByAdresa(adresa);
    }

    public boolean existsByAdresa(String adresa) {
        return this.filijalaRepository.existsByAdresa(adresa);
    }


}
