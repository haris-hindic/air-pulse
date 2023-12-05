package com.pulse.air.employee.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pulse.air.employee.dao.model.QualificationEntity;

public interface QualificationRepository extends JpaRepository<QualificationEntity, Long> {

	List<QualificationEntity> findByEmployeeId(Long employeeId);
}
