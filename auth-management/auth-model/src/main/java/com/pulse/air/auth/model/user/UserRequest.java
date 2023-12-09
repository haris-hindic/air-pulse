package com.pulse.air.auth.model.user;

import java.io.Serializable;
import java.time.LocalDateTime;

import lombok.Data;

@Data
public class UserRequest implements Serializable {
	private static final long serialVersionUID = 1L;

	private LocalDateTime dateOfBirth;
	private String email;
	private String firstName;
	private String username;
	private String password;
	private String lastName;
	private String phoneNumber;
	private String imageData;
}
