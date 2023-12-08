package com.pulse.air.flightcatalogue.rest.staff;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pulse.air.common.model.ApiException;
import com.pulse.air.common.model.ApiListResponse;
import com.pulse.air.common.model.ApiRequest;
import com.pulse.air.commons.rest.BaseCRUDController;
import com.pulse.air.flightcatalogue.contract.StaffService;
import com.pulse.air.flightcatalogue.model.staff.StaffRequest;
import com.pulse.air.flightcatalogue.model.staff.StaffResponse;
import com.pulse.air.flightcatalogue.model.staff.StaffSearchRequest;

@RestController
@RequestMapping("staff")
public class StaffManagementRest extends BaseCRUDController<StaffResponse, StaffRequest, StaffSearchRequest> {

	private StaffService absenceService;

	public StaffManagementRest(final StaffService service) {
		super(service);
		this.absenceService = service;
	}

	@GetMapping("aircraft/{id}")
	public ApiListResponse<StaffResponse> findByAircraftId(@PathVariable final Long id,
			@RequestHeader("AP_USER") final String user) throws ApiException {
		return absenceService.findByAircraftId(new ApiRequest<>(user, id));
	}
}
