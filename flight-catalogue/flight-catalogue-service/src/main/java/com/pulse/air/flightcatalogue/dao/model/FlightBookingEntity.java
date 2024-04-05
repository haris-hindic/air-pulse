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
@Table(name = "fc_flight_booking")
@NamedQuery(name = "FlightBookingEntity.findAll", query = "SELECT f FROM FlightBookingEntity f")
public class FlightBookingEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name = "FC_FLIGHT_BOOKING_ID_GENERATOR", sequenceName = "fc_flight_booking_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "FC_FLIGHT_BOOKING_ID_GENERATOR")
	private Long id;

	@Column(name = "total_price")
	private BigDecimal totalPrice;

	private LocalDateTime created;

	@Column(name = "created_by")
	private String createdBy;

	private LocalDateTime modified;

	@Column(name = "modified_by")
	private String modifiedBy;

	@Column(name = "user_id")
	private Long userId;

	@Column(name = "flight_id")
	private Long flightId;

	@Column(name = "return_flight_id")
	private Long returnFlightId;

	private String status;

	@Column(name = "seat_class")
	private String seatClass;

	private String luggage;

	private String userinfo;

	// bi-directional many-to-one association to FcRoute
	@ManyToOne
	@JoinColumn(name = "flight_id", insertable = false, updatable = false)
	private FlightEntity flight;

	// bi-directional many-to-one association to FcRoute
	@ManyToOne
	@JoinColumn(name = "return_flight_id", insertable = false, updatable = false)
	private FlightEntity returnFlight;

}
