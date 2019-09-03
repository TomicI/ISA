package com.repository.aviokompanija;

import com.model.aviokompanija.Aviokompanija;
import com.model.aviokompanija.Prtljag;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PrtljagRepository extends JpaRepository<Prtljag, Long>{
    List<Prtljag> findByAviokompanija(Aviokompanija aviokompanija);
}
