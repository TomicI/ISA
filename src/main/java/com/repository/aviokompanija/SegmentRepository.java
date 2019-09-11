package com.repository.aviokompanija;

import com.model.aviokompanija.KonfiguracijaLeta;
import com.model.aviokompanija.Segment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SegmentRepository extends JpaRepository<Segment, Long> {
}
