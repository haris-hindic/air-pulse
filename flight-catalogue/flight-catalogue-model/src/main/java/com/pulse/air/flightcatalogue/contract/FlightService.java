package com.pulse.air.flightcatalogue.contract;

import com.pulse.air.common.model.ApiException;
import com.pulse.air.common.model.ApiListResponse;
import com.pulse.air.common.model.ApiRequest;
import com.pulse.air.common.model.ApiResponse;
import com.pulse.air.commons.contract.BaseCRUDService;
import com.pulse.air.flightcatalogue.model.flight.ChartData;
import com.pulse.air.flightcatalogue.model.flight.FindReturnFlightRequest;
import com.pulse.air.flightcatalogue.model.flight.FlightRequest;
import com.pulse.air.flightcatalogue.model.flight.FlightResponse;
import com.pulse.air.flightcatalogue.model.flight.FlightSearchRequest;

public interface FlightService extends BaseCRUDService<FlightResponse, FlightRequest, FlightSearchRequest> {

	public ApiListResponse<FlightResponse> findReturnFligtsByRouteId(ApiRequest<FindReturnFlightRequest> request)
			throws ApiException;

	public ApiResponse<ChartData> flightsByMonths() throws ApiException;

	public ApiResponse<ChartData> flightsByCity() throws ApiException;
}
