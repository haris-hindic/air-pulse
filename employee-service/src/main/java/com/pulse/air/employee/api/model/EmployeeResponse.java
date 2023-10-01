package com.pulse.air.employee.api.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EmployeeResponse implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long employeeId;
	private LocalDateTime created;
	private String createdBy;
	private LocalDateTime dateOfBirth;
	private String email;
	private String firstName;
	private LocalDateTime hireDate;
	private String lastName;
	private LocalDateTime modified;
	private String modifiedBy;
	private String phoneNumber;
	private BigDecimal salary;
	private String status;
}
