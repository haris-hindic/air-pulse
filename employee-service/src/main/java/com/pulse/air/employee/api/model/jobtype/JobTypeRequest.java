package com.pulse.air.employee.api.model.jobtype;

import java.io.Serializable;
import java.math.BigDecimal;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class JobTypeRequest implements Serializable {
	private static final long serialVersionUID = 1L;

	@NotNull(message = "Rsponsibilities are mandatory!")
	private String responsibilities;
	@NotNull(message = "Minimal salary is mandatory!")
	@Min(value = 1)
	private BigDecimal salaryMin;
	@NotNull(message = "Maximal salary is mandatory!")
	@Min(value = 1)
	private BigDecimal salaryMax;
	@NotNull(message = "Title is mandatory!")
	private String title;
}