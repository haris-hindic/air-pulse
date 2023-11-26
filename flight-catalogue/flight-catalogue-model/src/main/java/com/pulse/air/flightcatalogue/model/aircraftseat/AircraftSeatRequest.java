package com.pulse.air.flightcatalogue.model.aircraftseat;

import java.io.Serializable;
import java.math.BigDecimal;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class AircraftSeatRequest implements Serializable {
	private static final long serialVersionUID = 1L;

	@NotNull(message = "Aircraft is mandatory!")
	private Long aircraftId;
	@NotNull(message = "Class is mandatory!")
	private String seatClass;
	@NotNull(message = "Price modifier is mandatory!")
	private BigDecimal priceModifier;
	@NotNull(message = "Quantity is mandatory!")
	private Long quantity;

}