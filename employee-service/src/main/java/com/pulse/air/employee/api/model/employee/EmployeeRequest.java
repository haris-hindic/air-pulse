package com.pulse.air.employee.api.model.employee;

import java.io.Serializable;
import java.time.LocalDateTime;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class EmployeeRequest implements Serializable {
	private static final long serialVersionUID = 1L;

	@NotNull(message = "Date of birth is mandatory!")
	private LocalDateTime dateOfBirth;
	@NotNull(message = "Email is mandatory!")
	@Email
	private String email;
	@NotNull(message = "First name is mandatory!")
	private String firstName;
	private String gender;
	@NotNull(message = "Last name is mandatory!")
	private String lastName;
	@NotNull(message = "Phone number is mandatory!")
	@Pattern(regexp = "^[\\+]?[(]?[0-9]{3}[)]?[-\\s\\.]?[0-9]{3}[-\\s\\.]?[0-9]{4,6}$", message = "Must be a valid phone number (+919367788755,8989829304,786-307-3615)")
	private String phoneNumber;
}
