package com.pulse.air.flightcatalogue.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.pulse.air.flightcatalogue.dao.model.RouteEntity;

public interface RouteRepository extends JpaRepository<RouteEntity, Long> {

	@Query(value = "SELECT r FROM RouteEntity r WHERE r.status = 'Active' and r.arrivalAirportId = :arrivalAirportId and r.departureAirportId = :departureAirportId")
	RouteEntity findRouteByDepartureAirportIdAndArrivalAirportId(@Param("arrivalAirportId") Long arrivalAirportId,
			@Param("departureAirportId") Long departureAirportId);
}
