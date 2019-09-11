package com.repository.aviokompanija;

import com.model.aviokompanija.KonfiguracijaLeta;
import com.model.aviokompanija.Let;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LetRepository extends JpaRepository<Let, Long> {

    List<Let> findByKonfiguracija(KonfiguracijaLeta konfiguracijaLeta);
}
