package com.pulse.air.auth.rest.auth;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.pulse.air.auth.contract.AuthService;
import com.pulse.air.auth.model.auth.LoginRequest;
import com.pulse.air.auth.model.user.UserRequest;
import com.pulse.air.common.model.ApiRequest;
import com.pulse.air.common.model.ApiResponse;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("auth")
@AllArgsConstructor
public class AuthManagementRest {

	private AuthService authService;

	@GetMapping("validate-token")
	public ApiResponse<Boolean> validateToken(@RequestParam final String token) {
		return authService.validateToken(new ApiRequest<>(null, token));
	}

	@PostMapping("login")
	public ApiResponse<String> login(@RequestBody final LoginRequest request) {
		return authService.login(new ApiRequest<>(null, request));
	}

	@PostMapping("register")
	public ApiResponse<String> register(@RequestBody final UserRequest request) throws Exception {
		return authService.register(new ApiRequest<>(request.getUsername(), request));
	}
}
