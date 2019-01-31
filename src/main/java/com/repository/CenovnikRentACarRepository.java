package com.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.model.CenovnikRentACar;

public interface CenovnikRentACarRepository extends JpaRepository<CenovnikRentACar, Long> {

}
