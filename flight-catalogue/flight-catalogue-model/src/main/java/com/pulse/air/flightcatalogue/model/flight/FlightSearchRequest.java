package com.pulse.air.flightcatalogue.model.flight;

import java.time.LocalDateTime;

import com.pulse.air.common.model.BaseSearchRequest;

public class FlightSearchRequest extends BaseSearchRequest {
	private static final long serialVersionUID = 1L;

	private Long routeId;
	private LocalDateTime departOn;

	public Long getRouteId() {
		return routeId;
	}

	public void setRouteId(final Long routeId) {
		this.routeId = routeId;
	}

	public LocalDateTime getDepartOn() {
		return departOn;
	}

	public void setDepartOn(final LocalDateTime departOn) {
		this.departOn = departOn;
	}

}
