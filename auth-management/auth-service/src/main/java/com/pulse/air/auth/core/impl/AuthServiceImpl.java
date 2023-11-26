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
	private AuthenticationManager authentificationManager;

	@Override
	public ApiResponse<String> login(final ApiRequest<LoginRequest> request) throws ApiException {
		var authenticate = authentificationManager.authenticate(new UsernamePasswordAuthenticationToken(
				request.getObject().getUsername(), request.getObject().getPassword()));

		if (authenticate.isAuthenticated()) {
			return new ApiResponse<>(HttpStatus.OK.value(), HttpStatus.OK.getReasonPhrase(),
					jwt.generateToken(request.getObject().getUsername()));
		} else {
			throw new ApiException(HttpStatus.UNAUTHORIZED, "invalid access");
		}
	}

	@Override
	public ApiResponse<String> register(final ApiRequest<UserRequest> request) throws ApiException {
		var user = userService.create(request).getData();

		return new ApiResponse<>(HttpStatus.OK.value(), HttpStatus.OK.getReasonPhrase(),
				jwt.generateToken(user.getUsername()));
	}

	@Override
	public ApiResponse<Boolean> validateToken(final ApiRequest<String> request)
			throws IOException {
		
		// TODO MOVE THIS TO GATEWAY TO ADD ONTO EVERY EXCHANGE

		String[] chunks = request.getObject().split("\\.");
		Base64.Decoder decoder = Base64.getUrlDecoder();
		ObjectMapper mapper = new ObjectMapper();

		String header = new String(decoder.decode(chunks[0]));
		var payload = mapper.readValue(decoder.decode(chunks[1]), Map.class);
		String user = (String) payload.get("username");
		

		return new ApiResponse<>(HttpStatus.OK.value(), HttpStatus.OK.getReasonPhrase(),
				jwt.validateToken(request.getObject()));
	}
}
