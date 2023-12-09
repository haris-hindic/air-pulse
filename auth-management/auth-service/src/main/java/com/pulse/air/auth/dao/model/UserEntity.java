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
@Table(name = "as_user")
@NamedQuery(name = "UserEntity.findAll", query = "SELECT e FROM UserEntity e")
public class UserEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name = "AS_USER_ID_GENERATOR", sequenceName = "AS_USER_SEQ", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "AS_USER_ID_GENERATOR")
	private Long id;

	private LocalDateTime created;

	@Column(name = "created_by")
	private String createdBy;

	@Column(name = "date_of_birth")
	private LocalDateTime dateOfBirth;

	private String email;

	@Column(name = "first_name")
	private String firstName;

	private String username;

	private String password;

	@Column(name = "last_name")
	private String lastName;

	private LocalDateTime modified;

	@Column(name = "modified_by")
	private String modifiedBy;

	@Column(name = "phone_number")
	private String phoneNumber;

	private String status;

	private String role;

	private String image;
}
