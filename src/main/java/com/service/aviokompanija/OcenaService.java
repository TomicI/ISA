package com.service.aviokompanija;

import com.dto.RateDTO;
import com.dto.aviokompanija.OcenaDTO;
import com.model.Rezervacija;
import com.model.aviokompanija.Ocena;
import com.model.user.User;
import com.repository.aviokompanija.OcenaRepository;
import com.service.FilijalaService;
import com.service.RezervacijaService;
import com.service.UserService;
import com.service.VoziloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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
public class OcenaService {

    @Autowired
    private OcenaRepository ocenaRepository;

    @Autowired
    private VoziloService voziloService;

    @Autowired
    private FilijalaService filijalaService;

    @Autowired
    private UserService userService;

    @Autowired
    private AviokompanijaService aviokompanijaService;

    @Autowired
    private LetService letService;

    @Autowired
    private RezervacijaService rezervacijaService;

    private ListeDTO liste = new ListeDTO();

    public List<OcenaDTO> getAll(){
        List<Ocena> ocene = ocenaRepository.findAll();

        if(ocene.isEmpty())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Ocene ne postoje");

        return liste.ocene(ocene);
    }

    public OcenaDTO findById(Long id){
        Optional<Ocena> ocena = ocenaRepository.findById(id);

        if(ocena.isPresent())
            return new OcenaDTO(ocena.get());

        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Ocena ne postoji");
    }

    public Optional<Ocena> findByRezervacijaAndVozilo(Long rezervacija_id,Long vozilo_id,Long user_id){
        return ocenaRepository.findByRezervacijaIdAndVoziloIdAndUserId(rezervacija_id,vozilo_id,user_id);
    }

    public Optional<Ocena> findByRezervacijaAndFilijala(Long rezervacija_id,Long filijala_id,Long user_id){
        return ocenaRepository.findByRezervacijaIdAndFilijalaIdAndUserId(rezervacija_id,filijala_id,user_id);
    }

    public Optional<Ocena> findByRezervacijaIdAndAviokompanijaIdAndUserId(Long rezervacija_id,Long aviokompanija_id,Long user_id){
        return ocenaRepository.findByRezervacijaIdAndAviokompanijaIdAndUserId(rezervacija_id,aviokompanija_id,user_id);
    }
    public Optional<Ocena> findByRezervacijaIdAndLetIdAndUserId(Long rezervacija_id,Long let_id,Long user_id){
        return ocenaRepository.findByRezervacijaIdAndLetIdAndUserId(rezervacija_id,let_id,user_id);
    }

    public List<Ocena> findByRezervavijaAndUser(Long rezervacija_id,Long user_id){
        return ocenaRepository.findByRezervacijaIdAndUserId(rezervacija_id,user_id);
    }

    public Ocena saveOcena(Ocena ocena){
        return ocenaRepository.save(ocena);
    }

    public OcenaDTO create(OcenaDTO ocenaDTO){

        Ocena ocena = new Ocena();
        ocena.setOcena(ocenaDTO.getOcena());

        ocenaRepository.save(ocena);
        return ocenaDTO;
    }

    public OcenaDTO update(OcenaDTO ocenaDTO){
        Optional<Ocena> ocena = ocenaRepository.findById(ocenaDTO.getId());
        if (!ocena.isPresent())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Ocena ne postoji");

        ocena.get().setOcena(ocenaDTO.getOcena());

        ocenaRepository.save(ocena.get());
        return new OcenaDTO(ocena.get());
    }

    public void delete(Long id){
        Optional<Ocena> ocena = ocenaRepository.findById(id);
        if (!ocena.isPresent())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Ocena ne postoji");

        ocenaRepository.deleteById(id);
    }

    public RateDTO getRates(Long rezervacija_id){

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.getByUsername(auth.getName());

        List<Ocena> ocene = findByRezervavijaAndUser(rezervacija_id,user.getId());

        RateDTO rateDTO = new RateDTO();

        for (Ocena o:ocene){
            if (o.getAviokompanija()!=null){
                rateDTO.setAvio(o.getOcena());
            }else if (o.getLet()!=null){
                rateDTO.setLetoc(o.getOcena());
            }else if (o.getVozilo()!=null){
                rateDTO.setVoz(o.getOcena());
            }else if (o.getFilijala()!=null){
                rateDTO.setFil(o.getOcena());
            }
        }

        return rateDTO;
    }

    public void getPermissionRent(Long res_id,Long veh_id,Long fil_id){

        voziloService.ratePermission(res_id,veh_id);
        filijalaService.ratePermission(res_id,fil_id);

    }

    @Transactional(isolation = Isolation.SERIALIZABLE, propagation = Propagation.REQUIRED)
    public void getPermisionAvio(Long res_id){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.getByUsername(auth.getName());

        Rezervacija rezervacija = rezervacijaService.getOne(res_id);

        if (rezervacija.getDatumVremeS().after(new Date())){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Reservation didn't end!");
        }

        long avio_id = aviokompanijaService.ratePermission(rezervacija);

        if(findByRezervacijaIdAndAviokompanijaIdAndUserId(res_id,avio_id,user.getId()).isPresent()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Airline already rated!");
        }

        long let_id = letService.ratePermission(rezervacija);

        if(findByRezervacijaIdAndLetIdAndUserId(res_id,let_id,user.getId()).isPresent()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Airline already rated!");
        }


    }
    @Transactional(isolation = Isolation.SERIALIZABLE, propagation = Propagation.REQUIRES_NEW)
    public void saveOcenaRentACar(List<OcenaDTO> ocenaDTOS){

        if (ocenaDTOS.isEmpty()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "List is empty!");
        }

        voziloService.rateVehicle(ocenaDTOS.get(0));

        filijalaService.rateFilijala(ocenaDTOS.get(1));
    }


    @Transactional(isolation = Isolation.SERIALIZABLE, propagation = Propagation.REQUIRES_NEW)
    public void saveRateAir(List<OcenaDTO> ocenaDTOS){

        if (ocenaDTOS.isEmpty()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "List is empty!");
        }
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.getByUsername(auth.getName());

        getPermisionAvio(ocenaDTOS.get(0).getRezervacijaDTO().getId());

        OcenaDTO ocenaDTOAvio = ocenaDTOS.get(0);
        OcenaDTO ocenaDTOFlight = ocenaDTOS.get(1);


        aviokompanijaService.oceni(ocenaDTOAvio.getAviokompanijaDTO().getId(),user,ocenaDTOAvio.getOcena(),ocenaDTOAvio.getRezervacijaDTO().getId());
        letService.oceni(ocenaDTOFlight.getLetDTO().getId(),user,ocenaDTOFlight.getOcena(),ocenaDTOFlight.getRezervacijaDTO().getId());

    }

    public List<OcenaDTO> getOcenaRentACar(Long res_id,Long veh_id,Long fil_id){

        List<OcenaDTO> ocenaDTOS = new ArrayList<>();

        if (veh_id!=null){
            OcenaDTO ocenaDTOVozilo = voziloService.getRate(res_id,veh_id);

            if (ocenaDTOVozilo!=null){
                ocenaDTOS.add(ocenaDTOVozilo);
            }
        }

        if (fil_id!=null){

            OcenaDTO ocenaDTOFil = filijalaService.getRate(res_id,fil_id);

            if (ocenaDTOFil!=null){
                ocenaDTOS.add(ocenaDTOFil);
            }

        }


        return ocenaDTOS;

    }

}
