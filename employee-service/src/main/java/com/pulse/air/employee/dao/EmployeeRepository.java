package com.pulse.air.employee.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pulse.air.employee.dao.model.EmployeeEntity;

public interface EmployeeRepository extends JpaRepository<EmployeeEntity, Long> {

	List<EmployeeEntity> findByFirstName(String firstName);

}
