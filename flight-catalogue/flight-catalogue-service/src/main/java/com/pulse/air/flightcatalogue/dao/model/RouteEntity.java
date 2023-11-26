package com.pulse.air.flightcatalogue.dao.model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.Data;

/**
 * The persistent class for the "fc_route" database table.
 * 
 */
@Data
@Entity
@Table(name = "fc_route")
@NamedQuery(name = "RouteEntity.findAll", query = "SELECT f FROM RouteEntity f")
public class RouteEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name = "FC_ROUTE_ID_GENERATOR", sequenceName = "FC_ROUTE_SEQ", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "FC_ROUTE_ID_GENERATOR")
	private Long id;

	@Column(name = "aircraft_id")
	private Long aircraftId;

	@Column(name = "arrival_airport_id")
	private Long arrivalAirportId;

	private LocalDateTime created;

	@Column(name = "created_by")
	private String createdBy;

	@Column(name = "departure_airport_id")
	private Long departureAirportId;

	private Long distance;

	private Long duration;

	private LocalDateTime modified;

	@Column(name = "modified_by")
	private String modifiedBy;

	private String note;

	private String status;

	// bi-directional many-to-one association to FlightEntity
	@OneToMany(mappedBy = "route")
	private List<FlightEntity> flights;

	// bi-directional many-to-one association to AircraftEntity
	@ManyToOne
	@JoinColumn(name = "aircraft_id", insertable = false, updatable = false)
	private AircraftEntity aircraft;

	// bi-directional many-to-one association to AirportEntity
	@ManyToOne
	@JoinColumn(name = "arrival_airport_id", insertable = false, updatable = false)
	private AirportEntity arrivalAirport;

	// bi-directional many-to-one association to AirportEntity
	@ManyToOne
	@JoinColumn(name = "departure_airport_id", insertable = false, updatable = false)
	private AirportEntity departureAirport;

}
