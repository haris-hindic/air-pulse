package com.pulse.air.auth.dao.model;

import java.io.Serializable;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.Data;

/**
 * The persistent class for the "ems_employee" database table.
 * 
 */
@Data
@Entity
@Table(name = "notification")
@NamedQuery(name = "NotificationEntity.findAll", query = "SELECT e FROM NotificationEntity e")
public class NotificationEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name = "NOTIFICATION_ID_GENERATOR", sequenceName = "NOTIFICATION_SEQ", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "NOTIFICATION_ID_GENERATOR")
	private Long id;

	private LocalDateTime created;

	@Column(name = "created_by")
	private String createdBy;

	private String topic;

	private String message;

	@Column(name = "user_id")
	private String userId;

	private String role;

	private LocalDateTime modified;

	@Column(name = "modified_by")
	private String modifiedBy;

	private String status;

	@Column(name = "bussiness_key")
	private Long bussinessKey;
}
