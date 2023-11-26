package com.pulse.air.flightcatalogue.dao.model;

import java.io.Serializable;
import java.math.BigDecimal;
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
 * The persistent class for the "fc_flight" database table.
 * 
 */
@Data
@Entity
@Table(name = "fc_flight")
@NamedQuery(name = "FlightEntity.findAll", query = "SELECT f FROM FlightEntity f")
public class FlightEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name = "FC_FLIGHT_ID_GENERATOR", sequenceName = "FC_FLIGHT_SEQ", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "FC_FLIGHT_ID_GENERATOR")
	private Long id;

	private LocalDateTime arrival;

	@Column(name = "base_price")
	private BigDecimal basePrice;

	private LocalDateTime created;

	@Column(name = "created_by")
	private String createdBy;

	private LocalDateTime departure;

	private LocalDateTime modified;

	@Column(name = "modified_by")
	private String modifiedBy;

	@Column(name = "route_id")
	private Long routeId;

	private String status;

	// bi-directional many-to-one association to FcRoute
	@ManyToOne
	@JoinColumn(name = "route_id", insertable = false, updatable = false)
	private RouteEntity route;

}
