package com.pulse.air.flightcatalogue.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pulse.air.flightcatalogue.dao.model.FlightBookingEntity;

public interface FlightBookingRepository extends JpaRepository<FlightBookingEntity, Long> {

}
