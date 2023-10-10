package com.pulse.air.auth.core.impl;

import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

import com.pulse.air.auth.contract.AuthService;
import com.pulse.air.auth.contract.UserService;
import com.pulse.air.auth.core.utils.JwtUtil;
import com.pulse.air.auth.model.auth.LoginRequest;
import com.pulse.air.auth.model.user.UserRequest;
import com.pulse.air.commons.model.ApiRequest;
import com.pulse.air.commons.model.ApiResponse;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class AuthServiceImpl implements AuthService {

	private JwtUtil jwt;
	private UserService userService;
	private AuthenticationManager authentificationManager;

	@Override
	public ApiResponse<String> login(final ApiRequest<LoginRequest> request) {
		var authenticate = authentificationManager.authenticate(new UsernamePasswordAuthenticationToken(
				request.getObject().getUsername(), request.getObject().getPassword()));

		if (authenticate.isAuthenticated()) {
			return new ApiResponse<>(HttpStatus.OK.value(), HttpStatus.OK.getReasonPhrase(),
					jwt.generateToken(request.getObject().getUsername()));
		} else {
			throw new RuntimeException("invalid access");
		}
	}

	@Override
	public ApiResponse<String> register(final ApiRequest<UserRequest> request) {
		var user = userService.create(request).getData();

		return new ApiResponse<>(HttpStatus.OK.value(), HttpStatus.OK.getReasonPhrase(),
				jwt.generateToken(user.getUsername()));
	}

	@Override
	public ApiResponse<Boolean> validateToken(final ApiRequest<String> request) {

		return new ApiResponse<>(HttpStatus.OK.value(), HttpStatus.OK.getReasonPhrase(),
				jwt.validateToken(request.getObject()));
	}
}
