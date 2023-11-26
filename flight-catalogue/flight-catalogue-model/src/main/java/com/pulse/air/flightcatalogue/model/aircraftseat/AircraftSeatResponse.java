package com.pulse.air.flightcatalogue.model.aircraftseat;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

import lombok.Data;

@Data
public class AircraftSeatResponse implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id;
	private Long aircraftId;
	private String seatClass;
	private LocalDateTime created;
	private String createdBy;
	private LocalDateTime modified;
	private String modifiedBy;
	private BigDecimal priceModifier;
	private Long quantity;
	private String status;

	// private AircraftEntity aircraft;

}
