package com.pulse.air.flightcatalogue.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pulse.air.flightcatalogue.dao.model.StaffEntity;

public interface StaffRepository extends JpaRepository<StaffEntity, Long> {

	List<StaffEntity> findByAircraftId(Long aircraftId);
}
