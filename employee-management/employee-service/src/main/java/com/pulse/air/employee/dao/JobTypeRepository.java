package com.pulse.air.employee.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pulse.air.employee.dao.model.JobTypeEntity;

public interface JobTypeRepository extends JpaRepository<JobTypeEntity, Long> {

}
