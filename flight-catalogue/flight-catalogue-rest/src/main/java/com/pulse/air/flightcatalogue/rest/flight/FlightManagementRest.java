package com.pulse.air.flightcatalogue.rest.flight;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.pulse.air.common.model.ApiException;
import com.pulse.air.common.model.ApiListResponse;
import com.pulse.air.common.model.ApiRequest;
import com.pulse.air.commons.rest.BaseCRUDController;
import com.pulse.air.flightcatalogue.contract.FlightService;
import com.pulse.air.flightcatalogue.model.flight.FindReturnFlightRequest;
import com.pulse.air.flightcatalogue.model.flight.FlightRequest;
import com.pulse.air.flightcatalogue.model.flight.FlightResponse;
import com.pulse.air.flightcatalogue.model.flight.FlightSearchRequest;

@RestController
@RequestMapping("flight")
public class FlightManagementRest extends BaseCRUDController<FlightResponse, FlightRequest, FlightSearchRequest> {

	private FlightService flightService;

	public FlightManagementRest(final FlightService service) {
		super(service);
		this.flightService = service;
	}

	@GetMapping("return")
	public ApiListResponse<FlightResponse> findReturnFligtsByRouteId(@RequestParam final Long departureAirportId,
			@RequestParam final Long arrivalAirportId, @RequestParam(required = false) final String departOn,
			@RequestParam(required = false) final String flightAfter,
			@RequestHeader("AP_USER") final String user) throws ApiException {
		return flightService.findReturnFligtsByRouteId(
				new ApiRequest<>(user,
						new FindReturnFlightRequest(departureAirportId, arrivalAirportId, departOn, flightAfter)));
	}
}
