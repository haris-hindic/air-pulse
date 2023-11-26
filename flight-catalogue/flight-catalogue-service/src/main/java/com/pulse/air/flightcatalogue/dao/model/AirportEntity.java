package com.pulse.air.flightcatalogue.dao.model;

import java.io.Serializable;
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
 * The persistent class for the "fc_airport" database table.
 * 
 */
@Data
@Entity
@Table(name = "fc_airport")
@NamedQuery(name = "AirportEntity.findAll", query = "SELECT f FROM AirportEntity f")
public class AirportEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name = "FC_AIRPORT_ID_GENERATOR", sequenceName = "FC_AIRPORT_SEQ", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "FC_AIRPORT_ID_GENERATOR")
	private Long id;

	private String city;

	private String country;

	private LocalDateTime created;

	@Column(name = "created_by")
	private String createdBy;

	@Column(name = "iata_code")
	private String iataCode;

	@Column(name = "icao_code")
	private String icaoCode;

	private LocalDateTime modified;

	@Column(name = "modified_by")
	private String modifiedBy;

	private String name;

	private String status;

	// bi-directional many-to-one association to RouteEntity
	@OneToMany(mappedBy = "departureAirport")
	private List<RouteEntity> departures;

	// bi-directional many-to-one association to RouteEntity
	@OneToMany(mappedBy = "arrivalAirport")
	private List<RouteEntity> arrivals;

}
