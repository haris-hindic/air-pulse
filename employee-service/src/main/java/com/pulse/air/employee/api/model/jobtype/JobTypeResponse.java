package com.pulse.air.employee.api.model.jobtype;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

import lombok.Data;

@Data
public class JobTypeResponse implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id;
	private LocalDateTime created;
	private String createdBy;
	private LocalDateTime modified;
	private String modifiedBy;
	private String responsibilities;
	private BigDecimal salaryMax;
	private BigDecimal salaryMin;
	private String status;
	private String title;
}
