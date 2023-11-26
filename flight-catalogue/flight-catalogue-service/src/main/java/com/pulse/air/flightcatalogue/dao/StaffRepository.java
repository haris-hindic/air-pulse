package com.pulse.air.flightcatalogue.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pulse.air.flightcatalogue.dao.model.StaffEntity;

public interface StaffRepository extends JpaRepository<StaffEntity, Long> {

}
