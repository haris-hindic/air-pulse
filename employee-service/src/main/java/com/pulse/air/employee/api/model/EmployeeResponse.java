package com.pulse.air.employee.api.model;

import java.io.Serializable;
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

	private Long id;
	private LocalDateTime created;
	private String createdBy;
	private LocalDateTime dateOfBirth;
	private String email;
	private String firstName;
	private String gender;
	private String lastName;
	private LocalDateTime modified;
	private String modifiedBy;
	private String phoneNumber;
	private String status;

//	private List<AbsenceEntity> absences;
//	private List<PositionEntity> positions;
//	private List<QualificationEntity> qualifications;
}
