package com.pulse.air.flightcatalogue.contract;

import com.pulse.air.common.model.ApiException;
import com.pulse.air.common.model.ApiListResponse;
import com.pulse.air.common.model.ApiRequest;
import com.pulse.air.common.model.BaseSearchRequest;
import com.pulse.air.commons.contract.BaseCRUDService;
import com.pulse.air.flightcatalogue.model.aircraftseat.AircraftSeatRequest;
import com.pulse.air.flightcatalogue.model.aircraftseat.AircraftSeatResponse;

public interface AircraftSeatService
		extends BaseCRUDService<AircraftSeatResponse, AircraftSeatRequest, BaseSearchRequest> {

	public ApiListResponse<AircraftSeatResponse> findByAircraftId(ApiRequest<Long> request) throws ApiException;
}
