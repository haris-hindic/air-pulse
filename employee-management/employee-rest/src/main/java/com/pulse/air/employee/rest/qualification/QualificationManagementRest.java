package com.pulse.air.employee.rest.qualification;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pulse.air.commons.rest.BaseCRUDController;
import com.pulse.air.employee.contract.QualificationService;
import com.pulse.air.employee.model.qualification.QualificationRequest;
import com.pulse.air.employee.model.qualification.QualificationResponse;

@RestController
@RequestMapping("qualification")
public class QualificationManagementRest extends BaseCRUDController<QualificationResponse, QualificationRequest> {

	public QualificationManagementRest(final QualificationService service) {
		super(service);
	}

}
