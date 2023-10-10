package com.pulse.air.auth.model.auth;

import java.io.Serializable;

import lombok.Data;

@Data
public class LoginRequest implements Serializable {
	private static final long serialVersionUID = 1L;

	private String username;
	private String password;
}
