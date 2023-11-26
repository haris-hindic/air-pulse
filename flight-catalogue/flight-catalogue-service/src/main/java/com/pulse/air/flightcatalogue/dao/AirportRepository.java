package com.pulse.air.flightcatalogue.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pulse.air.flightcatalogue.dao.model.AirportEntity;

public interface AirportRepository extends JpaRepository<AirportEntity, Long> {

}
