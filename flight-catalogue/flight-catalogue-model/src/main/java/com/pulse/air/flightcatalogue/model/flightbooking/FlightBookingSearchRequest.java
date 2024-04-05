package com.pulse.air.flightcatalogue.model.flightbooking;

import com.pulse.air.common.model.BaseSearchRequest;

public class FlightBookingSearchRequest extends BaseSearchRequest {
	private static final long serialVersionUID = 1L;

	private Long userId;
	private Long flightId;

	public Long getUserId() {
		return userId;
	}

	public void setUserId(final Long userId) {
		this.userId = userId;
	}

	public Long getFlightId() {
		return flightId;
	}

	public void setFlightId(final Long flightId) {
		this.flightId = flightId;
	}

}
