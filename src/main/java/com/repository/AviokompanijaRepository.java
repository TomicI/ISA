package com.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.CrossOrigin;


import com.model.Aviokompanija;

//@RepositoryRestResource
@CrossOrigin(origins = "http://localhost:4200")
public interface AviokompanijaRepository extends JpaRepository<Aviokompanija, Long> {

}
