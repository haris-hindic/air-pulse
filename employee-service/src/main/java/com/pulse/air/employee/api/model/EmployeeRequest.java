package com.pulse.air.employee.api.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class EmployeeRequest implements Serializable {
	private static final long serialVersionUID = 1L;

	@NotNull
	private LocalDateTime dateOfBirth;
	@NotNull(message = "Email is mandatory")
	private String email;
	@NotBlank(message = "First name is mandatory")
	private String firstName;
	private LocalDateTime hireDate;
	private String lastName;
	private String phoneNumber;
	private BigDecimal salary;
}
