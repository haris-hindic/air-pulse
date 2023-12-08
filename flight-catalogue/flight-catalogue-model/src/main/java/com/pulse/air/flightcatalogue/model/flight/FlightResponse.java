package com.pulse.air.flightcatalogue.model.flight;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.pulse.air.flightcatalogue.model.route.RouteResponse;

import lombok.Data;

@Data
public class FlightResponse implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id;
	private LocalDateTime arrival;
	private LocalDateTime departure;
	private BigDecimal basePrice;
	private LocalDateTime created;
	private String createdBy;
	private LocalDateTime modified;
	private String modifiedBy;
	private String status;
	private Long routeId;

	private RouteResponse route;
}
