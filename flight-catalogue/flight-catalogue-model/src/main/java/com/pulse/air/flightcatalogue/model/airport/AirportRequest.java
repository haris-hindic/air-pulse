package com.pulse.air.flightcatalogue.model.airport;

import java.io.Serializable;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class AirportRequest implements Serializable {
	private static final long serialVersionUID = 1L;

	@NotBlank(message = "City is mandatory!")
	private String city;
	@NotBlank(message = "Country is mandatory!")
	private String country;
	@NotBlank(message = "IATA code is mandatory!")
	private String iataCode;
	@NotBlank(message = "ICAO is mandatory!")
	private String icaoCode;
	@NotBlank(message = "Name is mandatory!")
	private String name;
}