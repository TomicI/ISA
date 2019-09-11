package com.repository.aviokompanija;

import com.model.aviokompanija.KategorijaSedista;
import com.model.aviokompanija.KonfiguracijaLeta;
import com.model.aviokompanija.Segment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface KategorijaSedistaRepository extends JpaRepository<KategorijaSedista, Long> {

}
