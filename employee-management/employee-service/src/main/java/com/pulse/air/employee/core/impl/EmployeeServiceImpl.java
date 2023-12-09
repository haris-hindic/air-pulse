package com.pulse.air.employee.core.impl;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Base64;
import java.util.Map;
import java.util.UUID;

import org.apache.commons.lang.StringUtils;
import org.springframework.data.domain.Example;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.cloudinary.Cloudinary;
import com.pulse.air.common.model.ApiException;
import com.pulse.air.common.model.ApiRequest;
import com.pulse.air.common.model.ApiUpdateRequest;
import com.pulse.air.common.model.BaseSearchRequest;
import com.pulse.air.commons.enums.Status;
import com.pulse.air.commons.services.BaseCRUDServiceImpl;
import com.pulse.air.employee.contract.EmployeeService;
import com.pulse.air.employee.core.mapper.EmployeeMapper;
import com.pulse.air.employee.dao.EmployeeRepository;
import com.pulse.air.employee.dao.model.EmployeeEntity;
import com.pulse.air.employee.model.employee.EmployeeRequest;
import com.pulse.air.employee.model.employee.EmployeeResponse;

@Service
public class EmployeeServiceImpl extends
		BaseCRUDServiceImpl<EmployeeEntity, EmployeeResponse, EmployeeRequest, BaseSearchRequest, EmployeeMapper, EmployeeRepository>
		implements EmployeeService {

	private Cloudinary cloudinary;

	public EmployeeServiceImpl(final EmployeeMapper mapper, final EmployeeRepository repository,
			final Cloudinary cloudinary) {
		super(mapper, repository);
		this.cloudinary = cloudinary;
	}

	@Override
	public Example<EmployeeEntity> getExample(final ApiRequest<BaseSearchRequest> request) {
		var search = request.getObject();
		if (search == null) {
			return super.getExample(request);
		}

		var example = new EmployeeEntity();
		if (StringUtils.isNotEmpty(search.getStatus())) {
			example.setStatus(search.getStatus());
		}

		return Example.of(example);
	}

	@Override
	public void beforeInsert(final EmployeeEntity entity, final ApiRequest<EmployeeRequest> request)
			throws ApiException {
		entity.setStatus(Status.ACTIVE.getValue());
		entity.setCreated(LocalDateTime.now());
		entity.setCreatedBy(request.getUsername());
		super.beforeInsert(entity, request);
	}

	@Override
	public void beforeUpdate(final EmployeeEntity entity, final ApiUpdateRequest<EmployeeRequest> request)
			throws ApiException {
		entity.setModified(LocalDateTime.now());
		entity.setModifiedBy(request.getUsername());
		if (StringUtils.isNotEmpty(request.getObject().getImageData())) {

			try {
				entity.setImage(handleImageUpload(request));
			} catch (IOException e) {
				e.printStackTrace();
				throw new ApiException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
			}
		}
		super.beforeUpdate(entity, request);
	}

	private String handleImageUpload(final ApiUpdateRequest<EmployeeRequest> request) throws IOException {
		var base64image = request.getObject().getImageData().split(",")[1];
		var decodedImage = Base64.getMimeDecoder().decode(base64image.getBytes());

		var cloudinaryResponse = cloudinary.uploader().upload(decodedImage,
				Map.of("public_id", UUID.randomUUID().toString()));

		return (String) cloudinaryResponse.get("url");
	}
}
