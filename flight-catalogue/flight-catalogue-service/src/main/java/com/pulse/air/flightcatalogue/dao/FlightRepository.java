package com.pulse.air.flightcatalogue.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pulse.air.flightcatalogue.dao.model.FlightEntity;

public interface FlightRepository extends JpaRepository<FlightEntity, Long> {

}
