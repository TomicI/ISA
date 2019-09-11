package com.repository.aviokompanija;

import com.model.aviokompanija.Aerodrom;
import com.model.aviokompanija.Lokacija;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AerodromRepository extends JpaRepository<Aerodrom, Long>{
    List<Aerodrom> findByLokacija(Lokacija lokacija);
    Aerodrom findByNaziv(String naziv);
}
