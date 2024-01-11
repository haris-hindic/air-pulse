package com.pulse.air.flightcatalogue.contract;

import com.pulse.air.common.model.ApiException;
import com.pulse.air.common.model.ApiRequest;
import com.pulse.air.common.model.ApiResponse;
import com.pulse.air.commons.contract.BaseCRUDService;
import com.pulse.air.flightcatalogue.model.flightbooking.FlightBookingRequest;
import com.pulse.air.flightcatalogue.model.flightbooking.FlightBookingResponse;
import com.pulse.air.flightcatalogue.model.flightbooking.FlightBookingSearchRequest;

public interface FlightBookingService
		extends BaseCRUDService<FlightBookingResponse, FlightBookingRequest, FlightBookingSearchRequest> {

	public ApiResponse<String> confirm(final ApiRequest<Long> request) throws ApiException;
}
