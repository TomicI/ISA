package com.repository.aviokompanija;

import com.model.aviokompanija.Putnik;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PuntikRepository extends JpaRepository<Putnik, Long> {
}
