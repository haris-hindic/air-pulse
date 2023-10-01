package com.pulse.air.employee.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pulse.air.employee.dao.model.PositionEntity;

public interface PositionRepository extends JpaRepository<PositionEntity, Long> {

}
