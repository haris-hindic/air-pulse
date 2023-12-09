package com.pulse.air.flightcatalogue.core.impl;

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
import com.pulse.air.commons.services.BaseCRUDServiceImpl;
import com.pulse.air.flightcatalogue.contract.AircraftService;
import com.pulse.air.flightcatalogue.core.mapper.AircraftMapper;
import com.pulse.air.flightcatalogue.dao.AircraftRepository;
import com.pulse.air.flightcatalogue.dao.model.AircraftEntity;
import com.pulse.air.flightcatalogue.model.aircraft.AircraftRequest;
import com.pulse.air.flightcatalogue.model.aircraft.AircraftResponse;

@Service
public class AircraftServiceImpl extends
		BaseCRUDServiceImpl<AircraftEntity, AircraftResponse, AircraftRequest, BaseSearchRequest, AircraftMapper, AircraftRepository>
		implements AircraftService {

	private Cloudinary cloudinary;

	public AircraftServiceImpl(final AircraftMapper mapper, final AircraftRepository repository,
			final Cloudinary cloudinary) {
		super(mapper, repository);
		this.cloudinary = cloudinary;
	}

	@Override
	public Example<AircraftEntity> getExample(final ApiRequest<BaseSearchRequest> request) {
		var search = request.getObject();
		if (search == null) {
			return super.getExample(request);
		}

		var example = new AircraftEntity();
		if (StringUtils.isNotEmpty(search.getStatus())) {
			example.setStatus(search.getStatus());
		}

		return Example.of(example);
	}

	@Override
	public void beforeInsert(final AircraftEntity entity, final ApiRequest<AircraftRequest> request)
			throws ApiException {
		entity.setCreated(LocalDateTime.now());
		entity.setCreatedBy(request.getUsername());
		super.beforeInsert(entity, request);
	}

	@Override
	public void beforeUpdate(final AircraftEntity entity, final ApiUpdateRequest<AircraftRequest> request)
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

	private String handleImageUpload(final ApiUpdateRequest<AircraftRequest> request) throws IOException {
		var base64image = request.getObject().getImageData().split(",")[1];
		var decodedImage = Base64.getMimeDecoder().decode(base64image.getBytes());

		var cloudinaryResponse = cloudinary.uploader().upload(decodedImage,
				Map.of("public_id", UUID.randomUUID().toString()));

		return (String) cloudinaryResponse.get("url");
	}

}
