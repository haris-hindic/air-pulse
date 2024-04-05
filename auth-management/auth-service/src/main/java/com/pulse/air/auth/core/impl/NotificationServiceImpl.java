package com.pulse.air.auth.core.impl;

import java.time.LocalDateTime;

import org.apache.commons.lang.StringUtils;
import org.springframework.data.domain.Example;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.pulse.air.auth.contract.NotificationService;
import com.pulse.air.auth.core.mapper.NotificationMapper;
import com.pulse.air.auth.dao.NotificationRepository;
import com.pulse.air.auth.dao.model.NotificationEntity;
import com.pulse.air.auth.model.nofitication.NotificationRequest;
import com.pulse.air.auth.model.nofitication.NotificationResponse;
import com.pulse.air.auth.model.nofitication.NotificationSearchRequest;
import com.pulse.air.common.model.ApiException;
import com.pulse.air.common.model.ApiRequest;
import com.pulse.air.common.model.ApiResponse;
import com.pulse.air.common.model.ApiUpdateRequest;
import com.pulse.air.commons.enums.Status;
import com.pulse.air.commons.services.BaseCRUDServiceImpl;

@Service
public class NotificationServiceImpl extends
		BaseCRUDServiceImpl<NotificationEntity, NotificationResponse, NotificationRequest, NotificationSearchRequest, NotificationMapper, NotificationRepository>
		implements NotificationService {

	NotificationRepository repository;

	public NotificationServiceImpl(final NotificationMapper mapper, final NotificationRepository repository) {
		super(mapper, repository);
		this.repository = repository;
	}

	@Override
	public Example<NotificationEntity> getExample(final ApiRequest<NotificationSearchRequest> request) {
		var search = request.getObject();
		if (search == null) {
			return super.getExample(request);
		}

		var example = new NotificationEntity();
		if (StringUtils.isNotEmpty(search.getStatus())) {
			example.setStatus(search.getStatus());
		}

		if (StringUtils.isNotEmpty(search.getRole())) {
			example.setRole(search.getRole());
		}

		return Example.of(example);
	}

	@Override
	public void beforeInsert(final NotificationEntity entity, final ApiRequest<NotificationRequest> request)
			throws ApiException {
		entity.setStatus(Status.ACTIVE.getValue());
		entity.setCreated(LocalDateTime.now());
		entity.setCreatedBy(request.getUsername());
		super.beforeInsert(entity, request);
	}

	@Override
	public void beforeUpdate(final NotificationEntity entity, final ApiUpdateRequest<NotificationRequest> request)
			throws ApiException {
		entity.setModified(LocalDateTime.now());
		entity.setModifiedBy(request.getUsername());
		super.beforeUpdate(entity, request);
	}

	@Override
	public ApiResponse<String> deactivate(final ApiRequest<Long> request) throws ApiException {

		var not = repository.findById(request.getObject());

		if (not.isPresent()) {
			var entity = not.get();
			entity.setModified(LocalDateTime.now());
			entity.setModifiedBy(request.getUsername());
			entity.setStatus("Inactive");
			repository.save(entity);
			return new ApiResponse<>(HttpStatus.OK.value(), HttpStatus.OK.getReasonPhrase(),
					"Successfully deactivated notification.");
		} else {
			throw new ApiException(HttpStatus.NOT_FOUND,
					String.format("There is no notification with id -> %s", request.getObject()));
		}

	}
}
