package com.service.aviokompanija;

import com.dto.aviokompanija.SegmentDTO;
import com.model.aviokompanija.KategorijaSedista;
import com.model.aviokompanija.Segment;
import com.repository.aviokompanija.KategorijaSedistaRepository;
import com.repository.aviokompanija.SegmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class SegmentService {

    @Autowired
    private SegmentRepository segmentRepository;

    @Autowired
    private KategorijaSedistaRepository kategorijaSedistaRepository;

    private ListeDTO liste = new ListeDTO();

    public List<SegmentDTO> getAll(){
        List<Segment> segmenti = segmentRepository.findAll();

        if(segmenti.isEmpty())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Segment ne postoji");

        return liste.segmenti(segmenti);
    }

    public SegmentDTO findById(Long id){
        Optional<Segment> segment = segmentRepository.findById(id);

        if(segment.isPresent())
            return new SegmentDTO(segment.get());

        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Segment ne postoji");
    }

    public SegmentDTO create(SegmentDTO segmentDTO){
        List<Segment> segmenti = segmentRepository.findAll();

        Segment segment = new Segment();
        segment.setDuzina(segmentDTO.getDuzina());
        segment.setSirina(segmentDTO.getSirina());
        segment.setRedniBroj(segmenti.size() + 1);
        if(segmentDTO.getKategorija().getId() != null){
            Optional<KategorijaSedista> kategorijaSedista = kategorijaSedistaRepository.findById(segmentDTO.getKategorija().getId());
            if(!kategorijaSedista.isPresent())
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Kategorija sedista ne postoji");
            kategorijaSedista.get().getSegmenti().add(segment);
            segment.setKategorija(kategorijaSedista.get());
            kategorijaSedistaRepository.save(kategorijaSedista.get());
        }

        segmentRepository.save(segment);
        return segmentDTO;
    }

    public SegmentDTO update(SegmentDTO segmentDTO){
        Optional<Segment> segment = segmentRepository.findById(segmentDTO.getId());
        if (!segment.isPresent())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Segment ne postoji");

        segment.get().setDuzina(segmentDTO.getDuzina());
        segment.get().setSirina(segmentDTO.getSirina());
        if(segmentDTO.getKategorija().getId() != null){
            Optional<KategorijaSedista> kategorijaSedista = kategorijaSedistaRepository.findById(segmentDTO.getKategorija().getId());
            if(!kategorijaSedista.isPresent())
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Kategorija sedista ne postoji");
            kategorijaSedista.get().getSegmenti().add(segment.get());
            segment.get().setKategorija(kategorijaSedista.get());
            kategorijaSedistaRepository.save(kategorijaSedista.get());
        }

        segmentRepository.save(segment.get());
        return new SegmentDTO(segment.get());
    }

    public void delete(Long id){
        Optional<Segment> aerodrom = segmentRepository.findById(id);
        if (!aerodrom.isPresent())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Segment ne postoji");

        segmentRepository.deleteById(id);
    }
}