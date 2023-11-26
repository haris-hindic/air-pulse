package com.pulse.air.flightcatalogue.rest.staff;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pulse.air.commons.rest.BaseCRUDController;
import com.pulse.air.flightcatalogue.contract.StaffService;
import com.pulse.air.flightcatalogue.model.staff.StaffRequest;
import com.pulse.air.flightcatalogue.model.staff.StaffResponse;

@RestController
@RequestMapping("staff")
public class StaffManagementRest extends BaseCRUDController<StaffResponse, StaffRequest> {

	public StaffManagementRest(final StaffService service) {
		super(service);
	}

}
