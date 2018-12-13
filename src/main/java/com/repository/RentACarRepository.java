package com.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.model.RentACarService;

public interface RentACarRepository extends JpaRepository<RentACarService,Long> {

}
