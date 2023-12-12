package com.pulse.air.flightcatalogue.model.flight;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class FindReturnFlightRequest implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long departureAirportId;
	private Long arrivalAirportId;
	private String departOn;
	private String flightAfter;
}
