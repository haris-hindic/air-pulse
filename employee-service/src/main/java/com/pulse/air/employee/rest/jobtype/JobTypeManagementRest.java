package com.pulse.air.employee.rest.jobtype;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pulse.air.commons.contract.BaseCRUDService;
import com.pulse.air.commons.rest.BaseCRUDController;
import com.pulse.air.employee.model.jobtype.JobTypeRequest;
import com.pulse.air.employee.model.jobtype.JobTypeResponse;

@RestController
@RequestMapping("job-type")
public class JobTypeManagementRest extends BaseCRUDController<JobTypeResponse, JobTypeRequest> {

	public JobTypeManagementRest(final BaseCRUDService<JobTypeResponse, JobTypeRequest> service) {
		super(service);
	}

}
