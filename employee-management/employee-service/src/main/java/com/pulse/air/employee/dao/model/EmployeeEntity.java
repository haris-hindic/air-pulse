package com.pulse.air.employee.dao.model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.Data;

/**
 * The persistent class for the "ems_employee" database table.
 * 
 */
@Data
@Entity
@Table(name = "ems_employee")
@NamedQuery(name = "EmployeeEntity.findAll", query = "SELECT e FROM EmployeeEntity e")
public class EmployeeEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name = "EMS_EMPLOYEE_ID_GENERATOR", sequenceName = "EMS_EMPLOYEE_SEQ", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "EMS_EMPLOYEE_ID_GENERATOR")
	private Long id;

	private LocalDateTime created;

	@Column(name = "created_by")
	private String createdBy;

	@Column(name = "date_of_birth")
	private LocalDateTime dateOfBirth;

	private String email;

	@Column(name = "first_name")
	private String firstName;

	private String gender;

	@Column(name = "last_name")
	private String lastName;

	private LocalDateTime modified;

	@Column(name = "modified_by")
	private String modifiedBy;

	@Column(name = "phone_number")
	private String phoneNumber;

	private String status;

	private String image;

	// bi-directional many-to-one association to AbsenceEntity
	@OneToMany(mappedBy = "employee", cascade = CascadeType.REMOVE)
	private List<AbsenceEntity> absences;

	// bi-directional many-to-one association to PositionEntity
	@OneToMany(mappedBy = "employee", cascade = CascadeType.REMOVE)
	private List<PositionEntity> positions;

	// bi-directional many-to-one association to QualificationEntity
	@OneToMany(mappedBy = "employee", cascade = CascadeType.REMOVE)
	private List<QualificationEntity> qualifications;

}
