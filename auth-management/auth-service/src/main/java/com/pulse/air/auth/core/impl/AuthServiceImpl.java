package com.pulse.air.auth.core.impl;

import java.io.IOException;
import java.util.Base64;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pulse.air.auth.contract.AuthService;
import com.pulse.air.auth.contract.UserService;
import com.pulse.air.auth.core.utils.JwtUtil;
import com.pulse.air.auth.dao.UserRepository;
import com.pulse.air.auth.model.auth.LoginRequest;
import com.pulse.air.auth.model.user.UserRequest;
import com.pulse.air.common.model.ApiException;
import com.pulse.air.common.model.ApiRequest;
import com.pulse.air.common.model.ApiResponse;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class AuthServiceImpl implements AuthService {

	private JwtUtil jwt;
	private UserService userService;
	private UserRepository userRepository;
	private AuthenticationManager authentificationManager;

	@Override
	public ApiResponse<String> login(final ApiRequest<LoginRequest> request) throws ApiException {
		try {

			var authenticate = authentificationManager.authenticate(new UsernamePasswordAuthenticationToken(
					request.getObject().getUsername(), request.getObject().getPassword()));

			if (authenticate.isAuthenticated()) {
				var user = userRepository.findByUsername(request.getObject().getUsername()).get();
				return new ApiResponse<>(HttpStatus.OK.value(), HttpStatus.OK.getReasonPhrase(),
						jwt.generateToken(request.getObject().getUsername(), user.getRole()));
			} else {
				throw new ApiException(HttpStatus.UNAUTHORIZED, "invalid access");
			}
		} catch (Exception e) {
			throw new ApiException(HttpStatus.UNAUTHORIZED, e.getMessage());
		}
	}

	@Override
	public ApiResponse<String> register(final ApiRequest<UserRequest> request) throws ApiException {
		var existingUserByUsername = userRepository.findByUsername(request.getUsername());
		if (existingUserByUsername.isPresent()) {
			throw new ApiException(HttpStatus.BAD_REQUEST, "Username already exists.");
		}

		var user = userService.create(request).getData();

		return new ApiResponse<>(HttpStatus.OK.value(), HttpStatus.OK.getReasonPhrase(),
				jwt.generateToken(user.getUsername(), user.getRole()));
	}

	@Override
	public ApiResponse<Boolean> validateToken(final ApiRequest<String> request) throws IOException {

		// TODO MOVE THIS TO GATEWAY TO ADD ONTO EVERY EXCHANGE

		var chunks = request.getObject().split("\\.");
		var decoder = Base64.getUrlDecoder();
		var mapper = new ObjectMapper();

		var header = new String(decoder.decode(chunks[0]));
		var payload = mapper.readValue(decoder.decode(chunks[1]), Map.class);
		var user = (String) payload.get("username");

		return new ApiResponse<>(HttpStatus.OK.value(), HttpStatus.OK.getReasonPhrase(),
				jwt.validateToken(request.getObject()));
	}
}
