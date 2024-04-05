package com.pulse.air.flightcatalogue.model.flightbooking;

import java.io.Serializable;
import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class FlightBookingRequest implements Serializable {
	private static final long serialVersionUID = 1L;

	private BigDecimal totalPrice;
	private Long userId;
	private String status;
	private Long flightId;
	private Long returnFlightId;
	private String luggage;
	private String seatClass;
}
