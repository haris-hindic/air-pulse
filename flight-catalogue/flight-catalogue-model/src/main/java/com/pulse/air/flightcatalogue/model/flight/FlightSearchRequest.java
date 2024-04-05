package com.pulse.air.flightcatalogue.model.flight;

import java.util.List;

import com.pulse.air.common.model.BaseSearchRequest;

public class FlightSearchRequest extends BaseSearchRequest {
	private static final long serialVersionUID = 1L;

	private Long routeId;
	private List<String> departOn;
	private String flightAfter;

	public Long getRouteId() {
		return routeId;
	}

	public void setRouteId(final Long routeId) {
		this.routeId = routeId;
	}

	public List<String> getDepartOn() {
		return departOn;
	}

	public void setDepartOn(final List<String> departOn) {
		this.departOn = departOn;
	}

	public String getFlightAfter() {
		return flightAfter;
	}

	public void setFlightAfter(final String flightAfter) {
		this.flightAfter = flightAfter;
	}

}
