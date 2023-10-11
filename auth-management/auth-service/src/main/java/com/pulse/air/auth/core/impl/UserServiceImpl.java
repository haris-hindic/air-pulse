package com.pulse.air.auth.core.impl;

import java.time.LocalDateTime;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.pulse.air.auth.contract.UserService;
import com.pulse.air.auth.core.mapper.UserMapper;
import com.pulse.air.auth.dao.UserRepository;
import com.pulse.air.auth.dao.model.UserEntity;
import com.pulse.air.auth.model.user.UserRequest;
import com.pulse.air.auth.model.user.UserResponse;
import com.pulse.air.common.model.ApiRequest;
import com.pulse.air.commons.enums.Status;
import com.pulse.air.commons.services.BaseCRUDServiceImpl;

@Service
public class UserServiceImpl extends
		BaseCRUDServiceImpl<UserEntity, UserResponse, UserRequest, UserMapper, UserRepository> implements UserService {

	private PasswordEncoder passwordEncoder;

	public UserServiceImpl(final UserMapper mapper, final UserRepository repository,
			final PasswordEncoder passwordEncoder) {
		super(mapper, repository);
		this.passwordEncoder = passwordEncoder;
	}

	@Override
	public void beforeInsert(final UserEntity entity, final ApiRequest<UserRequest> request) {
		entity.setPassword(passwordEncoder.encode(request.getObject().getPassword()));
		entity.setStatus(Status.ACTIVE.getValue());
		entity.setCreated(LocalDateTime.now());
		entity.setCreatedBy(request.getUsername());
		super.beforeInsert(entity, request);
	}

}
