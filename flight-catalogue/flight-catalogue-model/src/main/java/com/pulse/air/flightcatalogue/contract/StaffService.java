package com.pulse.air.flightcatalogue.contract;

import com.pulse.air.common.model.ApiException;
import com.pulse.air.common.model.ApiListResponse;
import com.pulse.air.common.model.ApiRequest;
import com.pulse.air.commons.contract.BaseCRUDService;
import com.pulse.air.flightcatalogue.model.staff.StaffRequest;
import com.pulse.air.flightcatalogue.model.staff.StaffResponse;
import com.pulse.air.flightcatalogue.model.staff.StaffSearchRequest;

public interface StaffService extends BaseCRUDService<StaffResponse, StaffRequest, StaffSearchRequest> {

	public ApiListResponse<StaffResponse> findByAircraftId(ApiRequest<Long> request) throws ApiException;
}
