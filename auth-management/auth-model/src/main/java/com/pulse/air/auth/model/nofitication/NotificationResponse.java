package com.pulse.air.auth.model.nofitication;

import java.io.Serializable;
import java.time.LocalDateTime;

import lombok.Data;

@Data
public class NotificationResponse implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id;
	private LocalDateTime created;
	private String createdBy;
	private String topic;
	private String message;
	private String userId;
	private String role;
	private LocalDateTime modified;
	private String modifiedBy;
	private String status;
	private Long bussinessKey;
}
