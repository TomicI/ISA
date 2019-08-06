package com.repository.aviokompanija;

import com.model.aviokompanija.Aviokompanija;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.CrossOrigin;

//@RepositoryRestResource
@CrossOrigin(origins = "http://localhost:4200")
public interface AviokompanijaRepository extends JpaRepository<Aviokompanija, Long> {

}
