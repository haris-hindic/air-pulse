package com.pulse.air.auth.model.nofitication;

import java.io.Serializable;

import lombok.Data;

@Data
public class NotificationRequest implements Serializable {
	private static final long serialVersionUID = 1L;

	private String topic;
	private String message;
	private String userId;
	private String role;
}