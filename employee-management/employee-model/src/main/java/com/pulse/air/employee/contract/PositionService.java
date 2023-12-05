package com.pulse.air.employee.contract;

import com.pulse.air.common.model.ApiException;
import com.pulse.air.common.model.ApiListResponse;
import com.pulse.air.common.model.ApiRequest;
import com.pulse.air.commons.contract.BaseCRUDService;
import com.pulse.air.employee.model.position.PositionRequest;
import com.pulse.air.employee.model.position.PositionResponse;

public interface PositionService extends BaseCRUDService<PositionResponse, PositionRequest> {

	public ApiListResponse<PositionResponse> findByEmployeeId(ApiRequest<Long> request) throws ApiException;
}
