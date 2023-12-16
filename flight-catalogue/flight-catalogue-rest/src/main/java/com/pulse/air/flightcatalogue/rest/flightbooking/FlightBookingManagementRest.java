package com.pulse.air.flightcatalogue.rest.flightbooking;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pulse.air.common.model.BaseSearchRequest;
import com.pulse.air.commons.rest.BaseCRUDController;
import com.pulse.air.flightcatalogue.contract.FlightBookingService;
import com.pulse.air.flightcatalogue.model.flightbooking.FlightBookingRequest;
import com.pulse.air.flightcatalogue.model.flightbooking.FlightBookingResponse;

@RestController
@RequestMapping("flight-booking")
public class FlightBookingManagementRest
		extends BaseCRUDController<FlightBookingResponse, FlightBookingRequest, BaseSearchRequest> {

	public FlightBookingManagementRest(final FlightBookingService service) {
		super(service);
	}
}