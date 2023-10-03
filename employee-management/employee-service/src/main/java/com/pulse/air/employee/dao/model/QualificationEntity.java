package com.pulse.air.employee.dao.model;

import java.io.Serializable;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.Data;

/**
 * The persistent class for the "ems_qualification" database table.
 * 
 */
@Data
@Entity
@Table(name = "ems_qualification")
@NamedQuery(name = "QualificationEntity.findAll", query = "SELECT q FROM QualificationEntity q")
public class QualificationEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name = "EMS_QUALIFICATION_ID_GENERATOR", sequenceName = "EMS_QUALIFICATION_SEQ", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "EMS_QUALIFICATION_ID_GENERATOR")
	private Long id;

	private LocalDateTime created;

	@Column(name = "created_by")
	private String createdBy;

	@Column(name = "education_level")
	private String educationLevel;

	@Column(name = "employee_id")
	private Long employeeId;

	private String languages;

	private String licences;

	private LocalDateTime modified;

	@Column(name = "modified_by")
	private String modifiedBy;

	private String skills;

	private String status;

	// bi-directional many-to-one association to EmployeeEntity
	@ManyToOne
	@JoinColumn(name = "employee_id", insertable = false, updatable = false)
	private EmployeeEntity employee;

}
