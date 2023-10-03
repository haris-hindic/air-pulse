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
 * The persistent class for the "ems_absence" database table.
 * 
 */
@Data
@Entity
@Table(name = "ems_absence")
@NamedQuery(name = "AbsenceEntity.findAll", query = "SELECT a FROM AbsenceEntity a")
public class AbsenceEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name = "EMS_ABSENCE_ID_GENERATOR", sequenceName = "EMS_ABSENCE_SEQ", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "EMS_ABSENCE_ID_GENERATOR")
	private Long id;

	private String comments;

	private LocalDateTime created;

	@Column(name = "created_by")
	private String createdBy;

	@Column(name = "employee_id")
	private Long employeeId;

	@Column(name = "end_date")
	private LocalDateTime endDate;

	private LocalDateTime modified;

	@Column(name = "modified_by")
	private String modifiedBy;

	private String reason;

	@Column(name = "start_date")
	private LocalDateTime startDate;

	private String status;

	private String type;

	// bi-directional many-to-one association to EmployeeEntity
	@ManyToOne
	@JoinColumn(name = "employee_id", insertable = false, updatable = false)
	private EmployeeEntity employee;

}
