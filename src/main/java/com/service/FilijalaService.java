package com.service;

import java.util.*;

import com.dto.FilijalaDTO;
import com.dto.RezervacijaRentACarDTO;
import com.dto.VoziloDTO;
import com.model.*;
import com.model.aviokompanija.Ocena;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.repository.FilijalaRepository;
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

    public Optional<Filijala> findOne(Long id) {
        return filijalaRepository.findById(id);
    }

    public List<VoziloDTO> findOneVeh(Long id) {

        Optional<Filijala> filijalaOptional = findOne(id);

        if (!filijalaOptional.isPresent()) {

        }

        Set<Vozilo> vozila = filijalaOptional.get().getVozila();

        List<VoziloDTO> vozilaDTO = new ArrayList<VoziloDTO>();

        Double sumOcena;
        int broj;

        for (Vozilo v : vozila) {
            List<Ocena> ocene = v.getOcene();
            sumOcena = 0.0;
            broj = ocene.size();
            for (Ocena o : ocene) {
                sumOcena += o.getOcena();
            }
            VoziloDTO voziloDTO = new VoziloDTO(v);
            if (sumOcena > 0)
                voziloDTO.setProsecnaOcena(sumOcena / broj);
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
                                RezervacijaRentACarDTO rezTemp = new RezervacijaRentACarDTO(null, null, pickUp, dropOff, cen, false, StatusRes.Reserved, new FilijalaDTO(filSearch), new FilijalaDTO(filBring), new VoziloDTO(v));
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
