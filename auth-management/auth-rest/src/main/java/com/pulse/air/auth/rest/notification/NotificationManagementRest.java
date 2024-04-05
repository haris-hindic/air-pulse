package com.pulse.air.auth.rest.notification;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pulse.air.auth.contract.NotificationService;
import com.pulse.air.auth.model.nofitication.NotificationRequest;
import com.pulse.air.auth.model.nofitication.NotificationResponse;
import com.pulse.air.auth.model.nofitication.NotificationSearchRequest;
import com.pulse.air.common.model.ApiException;
import com.pulse.air.common.model.ApiRequest;
import com.pulse.air.common.model.ApiResponse;
import com.pulse.air.commons.rest.BaseCRUDController;

@RestController
@RequestMapping("notification")
public class NotificationManagementRest
		extends BaseCRUDController<NotificationResponse, NotificationRequest, NotificationSearchRequest> {

	private NotificationService service;

	public NotificationManagementRest(final NotificationService service) {
		super(service);
		this.service = service;
	}

	@PutMapping(value = "deactivate/{id}")
	public ApiResponse<String> cancel(@PathVariable final Long id, @RequestHeader("AP_USER") final String user)
			throws ApiException {
		return service.deactivate(new ApiRequest<>(user, id));

	}

}
