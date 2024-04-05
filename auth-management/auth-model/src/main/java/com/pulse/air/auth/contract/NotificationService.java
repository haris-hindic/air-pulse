package com.pulse.air.auth.contract;

import com.pulse.air.auth.model.nofitication.NotificationRequest;
import com.pulse.air.auth.model.nofitication.NotificationResponse;
import com.pulse.air.auth.model.nofitication.NotificationSearchRequest;
import com.pulse.air.common.model.ApiException;
import com.pulse.air.common.model.ApiRequest;
import com.pulse.air.common.model.ApiResponse;
import com.pulse.air.commons.contract.BaseCRUDService;

public interface NotificationService
		extends BaseCRUDService<NotificationResponse, NotificationRequest, NotificationSearchRequest> {

	public ApiResponse<String> deactivate(final ApiRequest<Long> request) throws ApiException;
}
