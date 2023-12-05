package com.pulse.air.employee.rest.position;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pulse.air.common.model.ApiException;
import com.pulse.air.common.model.ApiListResponse;
import com.pulse.air.common.model.ApiRequest;
import com.pulse.air.commons.rest.BaseCRUDController;
import com.pulse.air.employee.contract.PositionService;
import com.pulse.air.employee.model.position.PositionRequest;
import com.pulse.air.employee.model.position.PositionResponse;

@RestController
@RequestMapping("position")
public class PositionManagementRest extends BaseCRUDController<PositionResponse, PositionRequest> {

	private PositionService positionService;

	public PositionManagementRest(final PositionService service) {
		super(service);
		this.positionService = service;
	}

	@GetMapping("employee/{id}")
	public ApiListResponse<PositionResponse> findByEmployeeId(@PathVariable final Long id,
			@RequestHeader("AP_USER") final String user) throws ApiException {
		return positionService.findByEmployeeId(new ApiRequest<>(user, id));
	}

}
