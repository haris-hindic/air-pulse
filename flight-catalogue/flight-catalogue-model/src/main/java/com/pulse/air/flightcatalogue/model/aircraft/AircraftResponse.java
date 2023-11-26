package com.pulse.air.flightcatalogue.model.aircraft;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

import lombok.Data;

@Data
public class AircraftResponse implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id;
	private BigDecimal averageSpeed;
	private LocalDateTime buildDate;
	private LocalDateTime created;
	private String createdBy;
	private String manufacturer;
	private String model;
	private LocalDateTime modified;
	private String modifiedBy;
	private String status;

//	private List<AircraftSeatEntity> aircraftSeats;
//	private List<RouteEntity> routes;
//	private List<StaffEntity> staff;

}

