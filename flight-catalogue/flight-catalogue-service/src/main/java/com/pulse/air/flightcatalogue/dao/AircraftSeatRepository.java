package com.pulse.air.flightcatalogue.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pulse.air.flightcatalogue.dao.model.AircraftSeatEntity;

public interface AircraftSeatRepository extends JpaRepository<AircraftSeatEntity, Long> {

	List<AircraftSeatEntity> findByAircraftId(Long aircraftId);
}
