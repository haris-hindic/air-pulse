package com.pulse.air.flightcatalogue.model.airport;

import java.io.Serializable;
import java.time.LocalDateTime;

import lombok.Data;

@Data
public class AirportResponse implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id;
	private String city;
	private String country;
	private LocalDateTime created;
	private String createdBy;
	private String iataCode;
	private String icaoCode;
	private LocalDateTime modified;
	private String modifiedBy;
	private String name;
	private String status;

//	private List<RouteEntity> departures;
//	private List<RouteEntity> arrivals;

}
