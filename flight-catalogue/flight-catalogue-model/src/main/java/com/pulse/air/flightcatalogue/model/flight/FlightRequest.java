package com.pulse.air.flightcatalogue.model.flight;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class FlightRequest implements Serializable {
	private static final long serialVersionUID = 1L;

	@NotNull(message = "Arrival is mandatory!")
	private LocalDateTime arrival;
	@NotNull(message = "Base price is mandatory!")
	private BigDecimal basePrice;
	@NotNull(message = "Depature is mandatory!")
	private LocalDateTime departure;
	@NotNull(message = "Route is mandatory!")
	private Long routeId;

}