package com.repository.aviokompanija;

import com.model.aviokompanija.Lokacija;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LokacijaRepository extends JpaRepository<Lokacija, Long> {

    boolean existsByAdresa(String adresa);

    Lokacija findByAdresa(String adresa);

    List<Lokacija> findByGradAndDrzava(String grad, String drzava);

}
