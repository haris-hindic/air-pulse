package com.pulse.air.flightcatalogue.model.flight;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

import lombok.Data;

@Data
public class FlightResponse implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id;
	private LocalDateTime arrival;
	private BigDecimal basePrice;
	private LocalDateTime created;
	private String createdBy;
	private LocalDateTime departure;
	private LocalDateTime modified;
	private String modifiedBy;
	private Long routeId;
	private String status;

	// private RouteEntity route;

}
