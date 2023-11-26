package com.pulse.air.flightcatalogue.model.airport;

import java.io.Serializable;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class AirportRequest implements Serializable {
	private static final long serialVersionUID = 1L;

	@NotNull(message = "City is mandatory!")
	private String city;
	@NotNull(message = "Country is mandatory!")
	private String country;
	@NotNull(message = "IATA code is mandatory!")
	private String iataCode;
	@NotNull(message = "ICAO is mandatory!")
	private String icaoCode;
	@NotNull(message = "Name is mandatory!")
	private String name;
}