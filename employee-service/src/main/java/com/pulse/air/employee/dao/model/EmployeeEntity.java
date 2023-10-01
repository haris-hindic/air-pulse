package com.pulse.air.employee.dao.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * The persistent class for the "employee" database table.
 * 
 */
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "employee")
@NamedQuery(name = "EmployeeEntity.findAll", query = "SELECT e FROM EmployeeEntity e")
public class EmployeeEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "employee_id")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "id_Sequence")
	@SequenceGenerator(name = "id_Sequence", sequenceName = "employee_seq", allocationSize = 1)
	private Long employeeId;

	@Column(name = "created")
	private LocalDateTime created;

	@Column(name = "created_by")
	private String createdBy;

	@Column(name = "date_of_birth")
	private LocalDateTime dateOfBirth;

	@Column(name = "email")
	private String email;

	@Column(name = "first_name")
	private String firstName;

	@Column(name = "hire_date")
	private LocalDateTime hireDate;

	@Column(name = "last_name")
	private String lastName;

	@Column(name = "modified")
	private LocalDateTime modified;

	@Column(name = "modified_by")
	private String modifiedBy;

	@Column(name = "phone_number")
	private String phoneNumber;

	@Column(name = "salary")
	private BigDecimal salary;

	@Column(name = "status")
	private String status;

}
