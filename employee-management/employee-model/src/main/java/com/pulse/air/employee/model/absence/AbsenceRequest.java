package com.pulse.air.employee.model.absence;

import java.io.Serializable;
import java.time.LocalDateTime;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class AbsenceRequest implements Serializable {
	private static final long serialVersionUID = 1L;

	private String comments;
	@NotNull(message = "Employee is mandatory!")
	private Long employeeId;
	@NotNull(message = "End date is mandatory!")
	private LocalDateTime endDate;
	@NotNull(message = "Reason is mandatory!")
	private String reason;
	@NotNull(message = "Start date is mandatory!")
	private LocalDateTime startDate;
	@NotNull(message = "Type is mandatory!")
	private String type;
	@NotNull(message = "Status is mandatory!")
	private String status;
}
