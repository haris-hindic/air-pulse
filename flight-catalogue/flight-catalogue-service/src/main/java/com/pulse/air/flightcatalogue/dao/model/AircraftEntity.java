package com.pulse.air.flightcatalogue.dao.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.Data;

/**
 * The persistent class for the "fc_aircraft" database table.
 * 
 */
@Data
@Entity
@Table(name = "fc_aircraft")
@NamedQuery(name = "AircraftEntity.findAll", query = "SELECT f FROM AircraftEntity f")
public class AircraftEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name = "FC_AIRCRAFT_ID_GENERATOR", sequenceName = "FC_AIRCRAFT_SEQ", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "FC_AIRCRAFT_ID_GENERATOR")
	private Long id;

	@Column(name = "average_speed")
	private BigDecimal averageSpeed;

	@Column(name = "build_date")
	private LocalDateTime buildDate;

	private LocalDateTime created;

	@Column(name = "created_by")
	private String createdBy;

	private String manufacturer;

	private String model;

	private LocalDateTime modified;

	@Column(name = "modified_by")
	private String modifiedBy;

	private String status;

	// bi-directional many-to-one association to FcAircraftSeat
	@OneToMany(mappedBy = "aircraft")
	private List<AircraftSeatEntity> aircraftSeats;

	// bi-directional many-to-one association to RouteEntity
	@OneToMany(mappedBy = "aircraft")
	private List<RouteEntity> routes;

	// bi-directional many-to-one association to StaffEntity
	@OneToMany(mappedBy = "aircraft")
	private List<StaffEntity> staff;

}

