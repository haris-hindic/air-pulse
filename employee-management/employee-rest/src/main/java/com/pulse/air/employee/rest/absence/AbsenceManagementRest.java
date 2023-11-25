package com.pulse.air.employee.rest.absence;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pulse.air.commons.contract.BaseCRUDService;
import com.pulse.air.commons.rest.BaseCRUDController;
import com.pulse.air.employee.model.absence.AbsenceRequest;
import com.pulse.air.employee.model.absence.AbsenceResponse;

@RestController
@RequestMapping("absence")
public class AbsenceManagementRest extends BaseCRUDController<AbsenceResponse, AbsenceRequest> {

	public AbsenceManagementRest(final BaseCRUDService<AbsenceResponse, AbsenceRequest> service) {
		super(service);
	}

}
