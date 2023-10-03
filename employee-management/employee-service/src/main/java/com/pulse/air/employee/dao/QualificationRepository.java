package com.pulse.air.employee.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pulse.air.employee.dao.model.QualificationEntity;

public interface QualificationRepository extends JpaRepository<QualificationEntity, Long> {

}
