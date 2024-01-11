package com.pulse.air.flightcatalogue.rest.flightbooking;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pulse.air.common.model.ApiException;
import com.pulse.air.common.model.ApiRequest;
import com.pulse.air.common.model.ApiResponse;
import com.pulse.air.commons.rest.BaseCRUDController;
import com.pulse.air.flightcatalogue.contract.FlightBookingService;
import com.pulse.air.flightcatalogue.model.flightbooking.FlightBookingRequest;
import com.pulse.air.flightcatalogue.model.flightbooking.FlightBookingResponse;
import com.pulse.air.flightcatalogue.model.flightbooking.FlightBookingSearchRequest;

@RestController
@RequestMapping("flight-booking")
public class FlightBookingManagementRest
		extends BaseCRUDController<FlightBookingResponse, FlightBookingRequest, FlightBookingSearchRequest> {

	private FlightBookingService flightBookingService;

	public FlightBookingManagementRest(final FlightBookingService service) {
		super(service);
		this.flightBookingService = service;
	}

	@PutMapping(value = "confirm/{bookingId}")
	public ApiResponse<String> confirm(@PathVariable final Long bookingId, @RequestHeader("AP_USER") final String user)
			throws ApiException {
		return flightBookingService.confirm(new ApiRequest<>(user, bookingId));

	}
}