package com.pulse.air.flightcatalogue.contract;

import com.pulse.air.common.model.BaseSearchRequest;
import com.pulse.air.commons.contract.BaseCRUDService;
import com.pulse.air.flightcatalogue.model.flightbooking.FlightBookingRequest;
import com.pulse.air.flightcatalogue.model.flightbooking.FlightBookingResponse;

public interface FlightBookingService
		extends BaseCRUDService<FlightBookingResponse, FlightBookingRequest, BaseSearchRequest> {

}
