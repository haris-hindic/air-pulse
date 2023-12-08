package com.pulse.air.flightcatalogue.model.route;

import java.io.Serializable;
import java.time.LocalDateTime;

import lombok.Data;

@Data
public class RouteResponse implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id;
	private Long aircraftId;
	private String aircraftDetails;
	private Long arrivalAirportId;
	private String arrivalAirportDetails;
	private LocalDateTime created;
	private String createdBy;
	private Long departureAirportId;
	private String departureAirportDetails;
	private Long distance;
	private Long duration;
	private LocalDateTime modified;
	private String modifiedBy;
	private String note;
	private String status;

}
