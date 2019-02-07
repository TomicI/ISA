package com.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

import com.model.Filijala;

public interface FilijalaRepository extends JpaRepository<Filijala,Long> {
    List<Filijala> findByAdresa(String adresa);
}
