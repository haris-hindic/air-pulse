package com.pulse.air.employee.rest.position;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pulse.air.commons.rest.BaseCRUDController;
import com.pulse.air.employee.contract.PositionService;
import com.pulse.air.employee.model.position.PositionRequest;
import com.pulse.air.employee.model.position.PositionResponse;

@RestController
@RequestMapping("position")
public class PositionManagementRest extends BaseCRUDController<PositionResponse, PositionRequest> {

	public PositionManagementRest(final PositionService service) {
		super(service);
	}

}
