package com.pulse.air.flightcatalogue.model.staff;

import java.io.Serializable;
import java.time.LocalDateTime;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class StaffRequest implements Serializable {
	private static final long serialVersionUID = 1L;

	@NotNull(message = "Aircraft is mandatory!")
	private Long aircraftId;
	@NotNull(message = "Employee is mandatory!")
	private Long employeeId;
	@NotNull(message = "Valid from is mandatory!")
	private LocalDateTime validFrom;
	private LocalDateTime validTo;

}