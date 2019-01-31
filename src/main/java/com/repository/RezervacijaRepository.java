package com.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.model.Rezervacija;

public interface RezervacijaRepository extends JpaRepository<Rezervacija, Long> {

}
