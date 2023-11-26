package com.pulse.air.employee.model.position;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

import lombok.Data;

@Data
public class PositionResponse implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id;
	private LocalDateTime created;
	private String createdBy;
	private Long employeeId;
	private String employeeFullName;
	private LocalDateTime endDate;
	private Long jobTypeId;
	private String title;
	private LocalDateTime modified;
	private String modifiedBy;
	private BigDecimal salary;
	private LocalDateTime startDate;
	private String status;

	// private EmployeeEntity employee;
	// private JobTypeEntity jobType;

}
