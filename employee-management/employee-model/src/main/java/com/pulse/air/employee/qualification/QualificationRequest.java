package com.pulse.air.employee.qualification;

import java.io.Serializable;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class QualificationRequest implements Serializable {
	private static final long serialVersionUID = 1L;

	@NotNull(message = "Education is mandatory!")
	private String educationLevel;
	@NotNull(message = "Employee is mandatory!")
	private Long employeeId;
	@NotNull(message = "Language is mandatory!")
	private String languages;
	private String licences;
	@NotNull(message = "Skills is mandatory!")
	private String skills;
}
