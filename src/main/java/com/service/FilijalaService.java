package com.service;

import java.lang.reflect.Array;
import java.util.*;

import com.dto.FilijalaDTO;
import com.dto.RezervacijaRentACarDTO;
import com.dto.VoziloDTO;
import com.dto.aviokompanija.OcenaDTO;
import com.model.*;
import com.model.aviokompanija.Lokacija;
import com.model.aviokompanija.Ocena;
import com.model.user.User;
import com.repository.aviokompanija.LokacijaRepository;
import com.security.ResponseMessage;
import com.service.aviokompanija.LokacijaService;
import com.service.aviokompanija.OcenaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.parameters.P;
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

    @Autowired
    private LokacijaService lokacijaService;



    public Optional<Filijala> findOne(Long id) {
        return filijalaRepository.findById(id);
    }

    public List<Filijala> findByRentACar(Long id){
        return filijalaRepository.findByRentACarId(id);
    }

    public Filijala getOne(Long id){
        Optional<Filijala> filijalaOptional = findOne(id);

        if (!filijalaOptional.isPresent()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Filijala doesn't exist!");
        }

        return filijalaOptional.get();
    }

    public List<VoziloDTO> findOneVeh(Long id) {

        List<Vozilo> vozila = getOne(id).getVozila();

        List<VoziloDTO> vozilaDTO = new ArrayList<VoziloDTO>();


        for (Vozilo v : vozila) {
            VoziloDTO voziloDTO = new VoziloDTO(v);

            voziloDTO.setProsecnaOcena(average(v));

            vozilaDTO.add(voziloDTO);
        }

        return vozilaDTO;

    }


    public List<RezervacijaRentACarDTO> search(
            String locationp,
            String bring,
            Date pickUp,
            Date dropOff,
            String range,
            String peo,
            Menjac gear,
            String group) {


        ArrayList<String> groups = new ArrayList<>();
        int fromP = 0;
        int toP = 0;

        double fromPrice = 0.0;
        double toPrice = 0.0;

        if (!lokacijaService.existsByAdresa(locationp)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Branch doesn't exist!");
        }

        if (!lokacijaService.existsByAdresa(bring)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Branch doesn't exist!");
        }

        if (group!=null){
            String[] gtemp= group.split("-");

            for (String s:gtemp){
                groups.add(s);
            }


        }

        if (range!=null){
            if (range.equals("0 - 30")){

                fromPrice = 0;
                toPrice = 30;

            }else if (range.equals("30 - 60")){
                fromPrice = 30;
                toPrice = 60;

            }else if(range.equals("60 - 100")){

                fromPrice = 60;
                toPrice = 100;

            }else if(range.equals("Greater than 100")){

                fromPrice = 100;

            }
        }

        if (peo!=null){
            if (peo.equals("2 - 4")){

                fromP = 2;
                toP = 4;

            }else if (peo.equals("5 - 7")){
                fromP = 5;
                toP = 7;
            }
        }


        Lokacija lokSearch = lokacijaService.findByAdresa(locationp);

        Lokacija lokBring = lokacijaService.findByAdresa(bring);


        Filijala filSearch =findByLokacija(lokSearch.getId());

        Filijala filBring = findByLokacija(lokBring.getId());

        RentACar r = rentACarService.findByNaziv(filSearch.getRentACar().getNaziv());


        List<CenovnikRentACar> cenovnikServisa = cenovnikRentACarService.findByServis(r);

        List<VoziloDTO> vozilaPretrage = new ArrayList<VoziloDTO>();

        List<FilijalaDTO> filijalaPretrage = new ArrayList<FilijalaDTO>();

        List<RezervacijaRentACarDTO> rezervacijePretrage = new ArrayList<RezervacijaRentACarDTO>();


        List<Vozilo> vozila = voziloService.findAll();

        for (Vozilo v : vozila) {
            if (v.getFilijala().equals(filSearch)) {

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

                        if (range!=null){
                            if (range.equals("Greater than 100") && c.getCena() < fromPrice){
                                continue;
                            }else if (c.getCena() < fromPrice || c.getCena() > toPrice) {
                                continue;
                            }
                        }

                        if (temp.before(c.getOdDatuma())){
                            continue;
                        }

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

                                if (gear!=null && peo!=null && group!=null){
                                    System.out.println("SVA TRI");
                                    if (v.getMenjac()==gear && v.getBrojSedista()>=fromP && v.getBrojSedista()<=toP){
                                        for (String s:groups){
                                            if (v.getGrupa().equals(s)){
                                                saveDTO(v,rezervacijePretrage,pickUp,dropOff,cen,filSearch,filBring);
                                            }
                                        }

                                    }
                                }else if (gear!=null && peo!=null){
                                    System.out.println("Gear peo");
                                    if (v.getMenjac()==gear && v.getBrojSedista()>=fromP && v.getBrojSedista()<=toP){
                                        saveDTO(v,rezervacijePretrage,pickUp,dropOff,cen,filSearch,filBring);
                                    }
                                }else if (gear!=null && group!=null){
                                    System.out.println("Gear Group");
                                    if (v.getMenjac()==gear ){
                                        for (String s:groups){
                                            if (v.getGrupa().equals(s)){
                                                saveDTO(v,rezervacijePretrage,pickUp,dropOff,cen,filSearch,filBring);
                                            }
                                        }
                                    }
                                } else if (peo != null && group != null) {
                                    System.out.println("Peo Group");
                                    if (v.getBrojSedista()>=fromP && v.getBrojSedista()<=toP){
                                        for (String s:groups){
                                            if (v.getGrupa().equals(s)){
                                                saveDTO(v,rezervacijePretrage,pickUp,dropOff,cen,filSearch,filBring);
                                            }
                                        }
                                    }

                                } else if (gear != null) {
                                    System.out.println("Gear ");
                                    if (v.getMenjac() == gear) {
                                        saveDTO(v,rezervacijePretrage,pickUp,dropOff,cen,filSearch,filBring);
                                    }
                                } else if (peo != null) {
                                    System.out.println("peo");
                                    System.out.println(fromP+" "+toP);
                                    System.out.println(v.getBrojSedista());
                                    if (v.getBrojSedista() >= fromP && v.getBrojSedista() <= toP) {
                                        saveDTO(v,rezervacijePretrage,pickUp,dropOff,cen,filSearch,filBring);
                                    }
                                } else if (group != null) {
                                    System.out.println("Group");
                                    for (String s : groups) {
                                        if (v.getGrupa().equals(s)) {
                                            saveDTO(v,rezervacijePretrage,pickUp,dropOff,cen,filSearch,filBring);
                                        }
                                    }
                                }else{
                                    System.out.println("Filter )=0");
                                    saveDTO(v,rezervacijePretrage,pickUp,dropOff,cen,filSearch,filBring);
                                }



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

    public void saveDTO(Vozilo v,List<RezervacijaRentACarDTO> rezervacijePretrage,Date pickUp,Date dropOff,Double cen,Filijala filSearch,Filijala filBring){
        VoziloDTO voziloDTO = new VoziloDTO(v);
        voziloDTO.setProsecnaOcena(average(v));

        RezervacijaRentACarDTO rezTemp = new RezervacijaRentACarDTO(null, null, pickUp, dropOff, cen,0.0, false,false,StatusRes.Reserved, new FilijalaDTO(filSearch), new FilijalaDTO(filBring), voziloDTO);
        rezervacijePretrage.add(rezTemp);
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

    @Transactional(isolation = Isolation.SERIALIZABLE, propagation = Propagation.REQUIRED)
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


    @Transactional(isolation = Isolation.SERIALIZABLE, propagation = Propagation.REQUIRED)
    public Filijala insert(FilijalaDTO filijalaDTO){

        if (filijalaDTO.getRentACarDTO()==null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Branch can't be null!");
        }

        RentACar rentACar = rentACarService.getOne(filijalaDTO.getRentACarDTO().getId());

        Lokacija lokacija = lokacijaService.create(filijalaDTO.getLokacijaDTO());

        Filijala filijala = new Filijala();
        filijala.setRentACar(rentACar);
        filijala.setLokacija(lokacija);

        return save(filijala);
    }


    @Transactional(isolation = Isolation.SERIALIZABLE, propagation = Propagation.REQUIRED)
    public Filijala edit(FilijalaDTO filijalaDTO){
        Filijala filijala = getOne(filijalaDTO.getId());

        lokacijaService.update(filijalaDTO.getLokacijaDTO());

        return save(filijala);
    }

    public List<Filijala> findAll() {
        return filijalaRepository.findAll();
    }

    public List<FilijalaDTO> getAll() {

        List<Filijala> filijalas = findAll();

        List<FilijalaDTO> filijalaDTOS = new ArrayList<>();

        for(Filijala f:filijalas){

            filijalaDTOS.add(new FilijalaDTO(f));

        }
        return filijalaDTOS;
    }

    public double getIncome(Date from,Date to){

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.getByUsername(auth.getName());

        RentACar rentACar = rentACarService.getOne(user.getRentACar().getId());

        System.out.println("Od " +from);
        System.out.println("Do " +to);

        double income = 0.0;

        for (Filijala f:rentACar.getFilijale()){
            for(RezervacijaRentACar r:f.getRezervacije()){
                if (!r.getOtkazana()){

                    if (r.getDatumPreuz().after(from) && r.getDatumVracanja().before(to) ){
                        System.out.println("Cena " +r.getCena());

                        income+=r.getCena();

                    }
                }
            }
        }

        return income;


    }

    public int getDailyVeh(Date pick,int opt){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.getByUsername(auth.getName());

        RentACar rentACar = rentACarService.getOne(user.getRentACar().getId());

        int num = 0 ;

        Calendar calendar = Calendar.getInstance();

        calendar.setTime(pick);

        if (opt==2){
            calendar.add(Calendar.WEEK_OF_MONTH, 1);

        }else if (opt==3){

            calendar.add(Calendar.MONTH, 1);
        }


        Date to = calendar.getTime();

        System.out.println(to);

        for (Filijala f:rentACar.getFilijale()){
            for(RezervacijaRentACar r:f.getRezervacije()){
                if (!r.getOtkazana()){

                    if (opt == 0){
                        if (pick.equals(r.getDatumPreuz()) || pick.after(r.getDatumPreuz()) ){
                            if (pick.before(r.getDatumVracanja()) || pick.equals(r.getDatumVracanja())){

                                num+=1;

                            }
                        }
                    }else {

                        if (r.getDatumPreuz().after(pick) && r.getDatumVracanja().before(to)) {

                            num += 1;

                        }
                    }

                }
            }
        }

        return num;

    }



    public Filijala save(Filijala f) {
        return filijalaRepository.save(f);
    }

    public void remove(Long id) {
        filijalaRepository.deleteById(id);
    }


    public Filijala findByLokacija(Long id){
        return filijalaRepository.findByLokacijaId(id);
    }




}
