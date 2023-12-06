package com.pulse.air.employee.contract;

import com.pulse.air.common.model.ApiException;
import com.pulse.air.common.model.ApiListResponse;
import com.pulse.air.common.model.ApiRequest;
import com.pulse.air.common.model.BaseSearchRequest;
import com.pulse.air.commons.contract.BaseCRUDService;
import com.pulse.air.employee.model.absence.AbsenceRequest;
import com.pulse.air.employee.model.absence.AbsenceResponse;

public interface AbsenceService extends BaseCRUDService<AbsenceResponse, AbsenceRequest, BaseSearchRequest> {

	public ApiListResponse<AbsenceResponse> findByEmployeeId(ApiRequest<Long> request) throws ApiException;
}
