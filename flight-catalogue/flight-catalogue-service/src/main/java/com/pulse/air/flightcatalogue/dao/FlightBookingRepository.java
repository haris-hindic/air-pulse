package com.pulse.air.flightcatalogue.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pulse.air.flightcatalogue.dao.model.FlightBookingEntity;

public interface FlightBookingRepository extends JpaRepository<FlightBookingEntity, Long> {

	List<FlightBookingEntity> findByUserId(Long userId);
}
