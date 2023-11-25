package com.pulse.air.employee.model.absence;

import java.io.Serializable;
import java.time.LocalDateTime;

import lombok.Data;

@Data
public class AbsenceResponse implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id;
	private String comments;
	private LocalDateTime created;
	private String createdBy;
	private Long employeeId;
	private String employeeFirstName;
	private String employeeLastName;
	private LocalDateTime endDate;
	private LocalDateTime modified;
	private String modifiedBy;
	private String reason;
	private LocalDateTime startDate;
	private String status;
	private String type;

	// private EmployeeEntity employee;
}
