package com.service.aviokompanija;

import com.dto.aviokompanija.OcenaDTO;
import com.model.Rezervacija;
import com.model.aviokompanija.Ocena;
import com.repository.aviokompanija.OcenaRepository;
import com.service.FilijalaService;
import com.service.RezervacijaService;
import com.service.UserService;
import com.service.VoziloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
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

    public Optional<Ocena> findByRezervacijaAndVozilo(Long rezervacija_id,Long vozilo_id){
        return ocenaRepository.findByRezervacijaIdAndVoziloId(rezervacija_id,vozilo_id);
    }

    public Optional<Ocena> findByRezervacijaAndFilijala(Long rezervacija_id,Long filijala_id){
        return ocenaRepository.findByRezervacijaIdAndFilijalaId(rezervacija_id,filijala_id);
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

    public void saveOcenaRentACar(List<OcenaDTO> ocenaDTOS){

        if (ocenaDTOS.isEmpty()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "List is empty!");
        }

        voziloService.rateVehicle(ocenaDTOS.get(0));

        filijalaService.rateFilijala(ocenaDTOS.get(1));
    }

    public List<OcenaDTO> getOcenaRentACar(OcenaDTO ocenaDTO){

        if (ocenaDTO==null){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }

        Optional<Ocena> ocena = findByRezervacijaAndFilijala(ocenaDTO.getRezervacijaDTO().getId(),ocenaDTO.getFilijalaDTO().getId());

        if (!ocena.isPresent()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Ocena not found!");
        }

        List<OcenaDTO> ocenaDTOS = new ArrayList<>();

        OcenaDTO ocenaTemp = new OcenaDTO();

        ocenaTemp.setOcena(ocena.get().getOcena());
        ocenaTemp.setFilijalaDTO(ocenaDTO.getFilijalaDTO());


        ocenaDTOS.add(ocenaDTO);

        ocena = findByRezervacijaAndVozilo(ocenaDTO.getRezervacijaDTO().getId(),ocenaDTO.getVoziloDTO().getId());

        if (!ocena.isPresent()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Ocena not found!");
        }

        ocenaTemp.setOcena(ocena.get().getOcena());
        ocenaTemp.setVoziloDTO(ocenaDTO.getVoziloDTO());
        ocenaTemp.setFilijalaDTO(null);

        ocenaDTOS.add(ocenaDTO);

        return ocenaDTOS;

    }

}
