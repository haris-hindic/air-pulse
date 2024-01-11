package com.pulse.air.auth.rest.notification;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pulse.air.auth.model.nofitication.NotificationRequest;
import com.pulse.air.auth.model.nofitication.NotificationResponse;
import com.pulse.air.common.model.BaseSearchRequest;
import com.pulse.air.commons.contract.BaseCRUDService;
import com.pulse.air.commons.rest.BaseCRUDController;

@RestController
@RequestMapping("notification")
public class NotificationManagementRest
		extends BaseCRUDController<NotificationResponse, NotificationRequest, BaseSearchRequest> {

	public NotificationManagementRest(
			final BaseCRUDService<NotificationResponse, NotificationRequest, BaseSearchRequest> service) {
		super(service);
	}

}
