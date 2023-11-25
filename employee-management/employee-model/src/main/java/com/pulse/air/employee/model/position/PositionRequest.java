package com.pulse.air.employee.model.position;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class PositionRequest implements Serializable {
	private static final long serialVersionUID = 1L;

	@NotNull(message = "Employee is mandatory!")
	private Long employeeId;
	private LocalDateTime endDate;
	@NotNull(message = "Job Type is mandatory!")
	private Long jobTypeId;
	@NotNull(message = "Salary is mandatory!")
	private BigDecimal salary;
	@NotNull(message = "Start date is mandatory!")
	private LocalDateTime startDate;
}
