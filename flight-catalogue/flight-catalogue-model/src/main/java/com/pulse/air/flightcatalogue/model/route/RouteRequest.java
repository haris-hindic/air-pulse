package com.pulse.air.flightcatalogue.model.route;

import java.io.Serializable;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class RouteRequest implements Serializable {
	private static final long serialVersionUID = 1L;

	@NotNull(message = "Aircraft is mandatory!")
	private Long aircraftId;
	@NotNull(message = "Arrival is mandatory!")
	private Long arrivalAirportId;
	@NotNull(message = "Departure is mandatory!")
	private Long departureAirportId;
	@NotNull(message = "Distance is mandatory!")
	private Long distance;
	@NotNull(message = "Duration is mandatory!")
	private Long duration;
	private String note;
	private String status;

}
