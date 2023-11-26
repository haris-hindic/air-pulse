package com.pulse.air.flightcatalogue.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pulse.air.flightcatalogue.dao.model.AircraftEntity;

public interface AircraftRepository extends JpaRepository<AircraftEntity, Long> {

}
