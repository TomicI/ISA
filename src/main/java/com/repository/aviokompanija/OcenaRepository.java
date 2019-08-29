package com.repository.aviokompanija;

import com.model.aviokompanija.Ocena;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface OcenaRepository extends JpaRepository<Ocena, Long> {

    Optional<Ocena> findByRezervacijaIdAndVoziloId(Long rezervacija_id, Long vozilo_id);

    Optional<Ocena> findByRezervacijaIdAndFilijalaId(Long rezervacija_id, Long filijala_id);

}
