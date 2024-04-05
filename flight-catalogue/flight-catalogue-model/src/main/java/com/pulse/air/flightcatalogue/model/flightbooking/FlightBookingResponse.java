package com.pulse.air.flightcatalogue.model.flightbooking;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.pulse.air.flightcatalogue.model.flight.FlightResponse;

import lombok.Data;

@Data
public class FlightBookingResponse implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id;
	private BigDecimal totalPrice;
	private LocalDateTime created;
	private String createdBy;
	private LocalDateTime modified;
	private String modifiedBy;
	private Long userId;
	private String status;
	private Long flightId;
	private Long returnFlightId;
	private String userinfo;
	private String luggage;
	private String seatClass;
	private FlightResponse flight;
	private FlightResponse returnFlight;

}
