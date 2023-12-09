package com.pulse.air.auth.core.impl;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Base64;
import java.util.Map;
import java.util.UUID;

import org.apache.commons.lang.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.cloudinary.Cloudinary;
import com.pulse.air.auth.contract.UserService;
import com.pulse.air.auth.core.mapper.UserMapper;
import com.pulse.air.auth.dao.UserRepository;
import com.pulse.air.auth.dao.model.UserEntity;
import com.pulse.air.auth.model.user.UserRequest;
import com.pulse.air.auth.model.user.UserResponse;
import com.pulse.air.common.model.ApiException;
import com.pulse.air.common.model.ApiRequest;
import com.pulse.air.common.model.ApiUpdateRequest;
import com.pulse.air.common.model.BaseSearchRequest;
import com.pulse.air.commons.enums.Status;
import com.pulse.air.commons.services.BaseCRUDServiceImpl;

@Service
public class UserServiceImpl extends
		BaseCRUDServiceImpl<UserEntity, UserResponse, UserRequest, BaseSearchRequest, UserMapper, UserRepository>
		implements UserService {

	private PasswordEncoder passwordEncoder;
	private Cloudinary cloudinary;

	public UserServiceImpl(final UserMapper mapper, final UserRepository repository,
			final PasswordEncoder passwordEncoder, final Cloudinary cloudinary) {
		super(mapper, repository);
		this.passwordEncoder = passwordEncoder;
		this.cloudinary = cloudinary;
	}

	@Override
	public void beforeInsert(final UserEntity entity, final ApiRequest<UserRequest> request) throws ApiException {
		entity.setPassword(passwordEncoder.encode(request.getObject().getPassword()));
		entity.setStatus(Status.ACTIVE.getValue());
		entity.setCreated(LocalDateTime.now());
		entity.setCreatedBy(request.getUsername());
		super.beforeInsert(entity, request);
	}

	@Override
	public void beforeUpdate(final UserEntity entity, final ApiUpdateRequest<UserRequest> request) throws ApiException {
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

	private String handleImageUpload(final ApiUpdateRequest<UserRequest> request) throws IOException {
		var base64image = request.getObject().getImageData().split(",")[1];
		var decodedImage = Base64.getMimeDecoder().decode(base64image.getBytes());

		var cloudinaryResponse = cloudinary.uploader().upload(decodedImage,
				Map.of("public_id", UUID.randomUUID().toString()));

		return (String) cloudinaryResponse.get("url");
	}
}
