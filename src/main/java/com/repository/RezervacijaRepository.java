package com.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.model.Rezervacija;

import java.util.Optional;

public interface RezervacijaRepository extends JpaRepository<Rezervacija, Long> {

    Optional<Rezervacija> findByRezervacijaRentACarId(Long id);
}
