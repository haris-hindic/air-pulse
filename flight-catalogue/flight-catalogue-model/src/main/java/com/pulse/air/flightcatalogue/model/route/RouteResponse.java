package com.pulse.air.flightcatalogue.model.route;

import java.io.Serializable;
import java.time.LocalDateTime;

import lombok.Data;

@Data
public class RouteResponse implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id;
	private Long aircraftId;
	private Long arrivalAirportId;
	private LocalDateTime created;
	private String createdBy;
	private Long departureAirportId;
	private Long distance;
	private Long duration;
	private LocalDateTime modified;
	private String modifiedBy;
	private String note;
	private String status;

	// private List<FlightEntity> flights;
//	private AircraftEntity aircraft;
//	private AirportEntity arrivalAirport;
//	private AirportEntity departureAirport;

}
