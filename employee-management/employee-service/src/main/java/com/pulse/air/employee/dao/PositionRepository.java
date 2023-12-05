package com.pulse.air.employee.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pulse.air.employee.dao.model.PositionEntity;

public interface PositionRepository extends JpaRepository<PositionEntity, Long> {

	List<PositionEntity> findByEmployeeId(Long employeeId);
}
