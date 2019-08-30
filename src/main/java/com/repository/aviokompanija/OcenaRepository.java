package com.repository.aviokompanija;

import com.model.aviokompanija.Ocena;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface OcenaRepository extends JpaRepository<Ocena, Long> {

    Optional<Ocena> findByRezervacijaIdAndVoziloIdAndUserId(Long rezervacija_id, Long vozilo_id,Long user_id);

    Optional<Ocena> findByRezervacijaIdAndFilijalaIdAndUserId(Long rezervacija_id, Long filijala_id,Long user_id);

    List<Ocena> findByRezervacijaIdAndUserId(Long rezervacija_id,Long user_id);


}
