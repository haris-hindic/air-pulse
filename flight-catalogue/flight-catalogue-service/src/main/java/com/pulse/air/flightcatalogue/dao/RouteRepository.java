package com.pulse.air.flightcatalogue.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pulse.air.flightcatalogue.dao.model.RouteEntity;

public interface RouteRepository extends JpaRepository<RouteEntity, Long> {

	RouteEntity findByArrivalAirportIdAndDepartureAirportId(Long arrivalAirportId, Long departureAirportId);
}
