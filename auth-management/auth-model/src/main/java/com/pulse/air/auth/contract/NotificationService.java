package com.pulse.air.auth.contract;

import com.pulse.air.auth.model.nofitication.NotificationRequest;
import com.pulse.air.auth.model.nofitication.NotificationResponse;
import com.pulse.air.common.model.BaseSearchRequest;
import com.pulse.air.commons.contract.BaseCRUDService;

public interface NotificationService
		extends BaseCRUDService<NotificationResponse, NotificationRequest, BaseSearchRequest> {

}
