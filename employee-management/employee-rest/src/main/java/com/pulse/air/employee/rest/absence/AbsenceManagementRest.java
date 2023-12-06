package com.pulse.air.employee.rest.absence;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pulse.air.common.model.ApiException;
import com.pulse.air.common.model.ApiListResponse;
import com.pulse.air.common.model.ApiRequest;
import com.pulse.air.common.model.BaseSearchRequest;
import com.pulse.air.commons.rest.BaseCRUDController;
import com.pulse.air.employee.contract.AbsenceService;
import com.pulse.air.employee.model.absence.AbsenceRequest;
import com.pulse.air.employee.model.absence.AbsenceResponse;

@RestController
@RequestMapping("absence")
public class AbsenceManagementRest extends BaseCRUDController<AbsenceResponse, AbsenceRequest, BaseSearchRequest> {

	private AbsenceService absenceService;

	public AbsenceManagementRest(final AbsenceService service) {
		super(service);
		this.absenceService = service;
	}

	@GetMapping("employee/{id}")
	public ApiListResponse<AbsenceResponse> findByEmployeeId(@PathVariable final Long id,
			@RequestHeader("AP_USER") final String user) throws ApiException {
		return absenceService.findByEmployeeId(new ApiRequest<>(user, id));
	}
}
