package com.pulse.air.flightcatalogue.contract;

import com.pulse.air.common.model.ApiException;
import com.pulse.air.common.model.ApiListResponse;
import com.pulse.air.common.model.ApiRequest;
import com.pulse.air.common.model.BaseSearchRequest;
import com.pulse.air.commons.contract.BaseCRUDService;
import com.pulse.air.flightcatalogue.model.staff.StaffRequest;
import com.pulse.air.flightcatalogue.model.staff.StaffResponse;

public interface StaffService extends BaseCRUDService<StaffResponse, StaffRequest, BaseSearchRequest> {

	public ApiListResponse<StaffResponse> findByAircraftId(ApiRequest<Long> request) throws ApiException;
}
