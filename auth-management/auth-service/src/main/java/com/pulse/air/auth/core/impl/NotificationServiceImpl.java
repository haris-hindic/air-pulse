package com.pulse.air.auth.core.impl;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

import com.pulse.air.auth.contract.NotificationService;
import com.pulse.air.auth.core.mapper.NotificationMapper;
import com.pulse.air.auth.dao.NotificationRepository;
import com.pulse.air.auth.dao.model.NotificationEntity;
import com.pulse.air.auth.model.nofitication.NotificationRequest;
import com.pulse.air.auth.model.nofitication.NotificationResponse;
import com.pulse.air.common.model.ApiException;
import com.pulse.air.common.model.ApiRequest;
import com.pulse.air.common.model.ApiUpdateRequest;
import com.pulse.air.common.model.BaseSearchRequest;
import com.pulse.air.commons.enums.Status;
import com.pulse.air.commons.services.BaseCRUDServiceImpl;

@Service
public class NotificationServiceImpl extends
		BaseCRUDServiceImpl<NotificationEntity, NotificationResponse, NotificationRequest, BaseSearchRequest, NotificationMapper, NotificationRepository>
		implements NotificationService {


	public NotificationServiceImpl(final NotificationMapper mapper, final NotificationRepository repository) {
		super(mapper, repository);
	}

	@Override
	public void beforeInsert(final NotificationEntity entity, final ApiRequest<NotificationRequest> request) throws ApiException {
		entity.setStatus(Status.ACTIVE.getValue());
		entity.setCreated(LocalDateTime.now());
		entity.setCreatedBy(request.getUsername());
		super.beforeInsert(entity, request);
	}

	@Override
	public void beforeUpdate(final NotificationEntity entity, final ApiUpdateRequest<NotificationRequest> request) throws ApiException {
		entity.setModified(LocalDateTime.now());
		entity.setModifiedBy(request.getUsername());
		super.beforeUpdate(entity, request);
	}
}
