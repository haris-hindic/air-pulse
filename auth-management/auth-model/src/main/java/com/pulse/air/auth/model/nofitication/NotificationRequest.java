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
	private Long bussinessKey;

	public NotificationRequest(final String topic, final String message, final String role, final Long bussinessKey) {
		super();
		this.topic = topic;
		this.message = message;
		this.role = role;
		this.bussinessKey = bussinessKey;
	}
}