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
 * The persistent class for the "fc_aircraft_seats" database table.
 * 
 */
@Data
@Entity
@Table(name = "fc_aircraft_seats")
@NamedQuery(name = "AircraftSeatEntity.findAll", query = "SELECT f FROM AircraftSeatEntity f")
public class AircraftSeatEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name = "FC_AIRCRAFT_SEATS_ID_GENERATOR", sequenceName = "FC_AIRCRAFT_SEATS_SEQ", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "FC_AIRCRAFT_SEATS_ID_GENERATOR")
	private Long id;

	@Column(name = "aircraft_id")
	private Long aircraftId;

	@Column(name = "class")
	private String seatClass;

	private LocalDateTime created;

	@Column(name = "created_by")
	private String createdBy;

	private LocalDateTime modified;

	@Column(name = "modified_by")
	private String modifiedBy;

	@Column(name = "price_modifier")
	private BigDecimal priceModifier;

	private Long quantity;

	private String status;

	// bi-directional many-to-one association to AircraftEntity
	@ManyToOne
	@JoinColumn(name = "aircraft_id", insertable = false, updatable = false)
	private AircraftEntity aircraft;

}
