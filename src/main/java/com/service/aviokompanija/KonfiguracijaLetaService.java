package com.service.aviokompanija;

import com.dto.aviokompanija.KategorijaSedistaDTO;
import com.dto.aviokompanija.KonfiguracijaLetaDTO;
import com.dto.aviokompanija.SegmentDTO;
import com.model.aviokompanija.KategorijaSedista;
import com.model.aviokompanija.KonfiguracijaLeta;
import com.model.aviokompanija.Segment;
import com.repository.aviokompanija.KategorijaSedistaRepository;
import com.repository.aviokompanija.KonfiguracijaLetaRepository;
import com.repository.aviokompanija.SegmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@Service
public class KonfiguracijaLetaService {

    @Autowired
    private KonfiguracijaLetaRepository konfiguracijaLetaRepository;

    @Autowired
    private SegmentRepository segmentRepository;

    @Autowired
    private KategorijaSedistaRepository kategorijaSedistaRepository;

    private ListeDTO liste = new ListeDTO();

    public List<KonfiguracijaLetaDTO> getAll(){
        List<KonfiguracijaLeta> konfiguracije = konfiguracijaLetaRepository.findAll();

        if(konfiguracije.isEmpty())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Konfiguracije letova ne postoje");

        return liste.konfiguracijeLetova(konfiguracije);
    }

    public KonfiguracijaLetaDTO findById(Long id){
        Optional<KonfiguracijaLeta> konfiguracijaLeta = konfiguracijaLetaRepository.findById(id);

        if(konfiguracijaLeta.isPresent())
            return new KonfiguracijaLetaDTO(konfiguracijaLeta.get());

        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Konfiguracija leta ne postoji");
    }

    public KonfiguracijaLetaDTO create(KonfiguracijaLetaDTO konfiguracijaLetaDTO){
        KonfiguracijaLeta konfiguracijaLeta = new KonfiguracijaLeta();
        konfiguracijaLeta.setNaziv(konfiguracijaLetaDTO.getNaziv());

        konfiguracijaLetaRepository.save(konfiguracijaLeta);
        return konfiguracijaLetaDTO;
    }

    public KonfiguracijaLetaDTO update(KonfiguracijaLetaDTO konfiguracijaLetaDTO){
        Optional<KonfiguracijaLeta> konfiguracijaLeta = konfiguracijaLetaRepository.findById(konfiguracijaLetaDTO.getId());
        if (!konfiguracijaLeta.isPresent())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Konfiguracija leta ne postoji");

        konfiguracijaLetaRepository.save(konfiguracijaLeta.get());
        return new KonfiguracijaLetaDTO(konfiguracijaLeta.get());
    }

    public void delete(Long id){
        Optional<KonfiguracijaLeta> konfiguracijaLeta = konfiguracijaLetaRepository.findById(id);
        if (!konfiguracijaLeta.isPresent())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Konfiguracija leta ne postoji");

        konfiguracijaLetaRepository.deleteById(id);
    }

    public SegmentDTO createSegment(Long id, SegmentDTO segmentDTO){
        Optional<KonfiguracijaLeta> konfiguracijaLeta = konfiguracijaLetaRepository.findById(id);
        if(!konfiguracijaLeta.isPresent())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Konfiguracija leta ne postoji");

        Segment segment = new Segment();
        segment.setDuzina(segmentDTO.getDuzina());
        segment.setSirina(segmentDTO.getSirina());
        segment.setRedniBroj(konfiguracijaLeta.get().getSegmenti().size() + 1);
        if(segmentDTO.getKategorija().getId() != null){
            Optional<KategorijaSedista> kategorijaSedista = kategorijaSedistaRepository.findById(segmentDTO.getKategorija().getId());
            if(!kategorijaSedista.isPresent())
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Kategorija sedista ne postoji");
            kategorijaSedista.get().getSegmenti().add(segment);
            segment.setKategorija(kategorijaSedista.get());
            kategorijaSedistaRepository.save(kategorijaSedista.get());
        }

        segment.setKonfiguracija(konfiguracijaLeta.get());
        konfiguracijaLeta.get().getSegmenti().add(segment);

        return segmentDTO;
    }
    public List<SegmentDTO> segmenti(Long id){
        Optional<KonfiguracijaLeta> konfiguracijaLeta = konfiguracijaLetaRepository.findById(id);
        List<SegmentDTO> segmentDTOS=new ArrayList<>();
        if(!konfiguracijaLeta.isPresent())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Konfiguracija leta ne postoji");

        if(konfiguracijaLeta.get().getSegmenti().isEmpty())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Segmenti konfiguracije ne postoje");

        for(Segment s: konfiguracijaLeta.get().getSegmenti()){
            SegmentDTO segmentDTO=new SegmentDTO(s);
            segmentDTO.setKategorija(new KategorijaSedistaDTO(s.getKategorija()));
            segmentDTO.setKonfiguracija(new KonfiguracijaLetaDTO(s.getKonfiguracija()));
            segmentDTOS.add(segmentDTO);
        }
        return segmentDTOS;
    }
}
