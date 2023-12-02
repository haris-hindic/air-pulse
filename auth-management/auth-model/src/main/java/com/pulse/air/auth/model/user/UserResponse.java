package com.pulse.air.auth.model.user;

import java.io.Serializable;
import java.time.LocalDateTime;

import lombok.Data;

@Data
public class UserResponse implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id;
	private LocalDateTime created;
	private String createdBy;
	private LocalDateTime dateOfBirth;
	private String email;
	private String firstName;
	private String lastName;
	private String username;
	private LocalDateTime modified;
	private String modifiedBy;
	private String phoneNumber;
	private String status;
	private String role;
}
