package com.pulse.air.flightcatalogue.dao.model;

import java.io.Serializable;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.Data;

/**
 * The persistent class for the "fc_staff" database table.
 * 
 */
@Data
@Entity
@Table(name = "fc_staff")
@NamedQuery(name = "StaffEntity.findAll", query = "SELECT f FROM StaffEntity f")
public class StaffEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name = "FC_STAFF_ID_GENERATOR", sequenceName = "FC_STAFF_SEQ", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "FC_STAFF_ID_GENERATOR")
	private Long id;

	@Column(name = "aircraft_id")
	private Long aircraftId;

	private LocalDateTime created;

	@Column(name = "created_by")
	private String createdBy;

	@Column(name = "employee_id")
	private Long employeeId;

	private LocalDateTime modified;

	@Column(name = "modified_by")
	private String modifiedBy;

	private String status;

	@Column(name = "valid_from")
	private LocalDateTime validFrom;

	@Column(name = "valid_to")
	private LocalDateTime validTo;

	// bi-directional many-to-one association to AircraftEntity
	@ManyToOne
	@JoinColumn(name = "aircraft_id", insertable = false, updatable = false)
	private AircraftEntity aircraft;

}
