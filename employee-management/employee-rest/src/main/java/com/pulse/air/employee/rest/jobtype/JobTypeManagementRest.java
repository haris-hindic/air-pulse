package com.pulse.air.employee.rest.jobtype;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pulse.air.common.model.BaseSearchRequest;
import com.pulse.air.commons.rest.BaseCRUDController;
import com.pulse.air.employee.contract.JobTypeService;
import com.pulse.air.employee.model.jobtype.JobTypeRequest;
import com.pulse.air.employee.model.jobtype.JobTypeResponse;

@RestController
@RequestMapping("job-type")
public class JobTypeManagementRest extends BaseCRUDController<JobTypeResponse, JobTypeRequest, BaseSearchRequest> {

	public JobTypeManagementRest(final JobTypeService service) {
		super(service);
	}

}
