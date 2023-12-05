package com.pulse.air.employee.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pulse.air.employee.dao.model.AbsenceEntity;

public interface AbsenceRepository extends JpaRepository<AbsenceEntity, Long> {

	List<AbsenceEntity> findByEmployeeId(Long employeeId);
}
