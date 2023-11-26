package com.pulse.air.employee.model.qualification;

import java.io.Serializable;
import java.time.LocalDateTime;

import lombok.Data;

@Data
public class QualificationResponse implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id;
	private LocalDateTime created;
	private String createdBy;
	private String educationLevel;
	private Long employeeId;
	private String languages;
	private String licences;
	private LocalDateTime modified;
	private String modifiedBy;
	private String skills;
	private String status;

	// private EmployeeEntity employee;

}
