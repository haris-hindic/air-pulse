package com.pulse.air.flightcatalogue.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pulse.air.flightcatalogue.dao.model.AircraftSeatEntity;

public interface AircraftSeatRepository extends JpaRepository<AircraftSeatEntity, Long> {

}
