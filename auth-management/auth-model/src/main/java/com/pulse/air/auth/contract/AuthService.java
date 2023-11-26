package com.pulse.air.auth.contract;

import java.io.IOException;

import com.pulse.air.auth.model.auth.LoginRequest;
import com.pulse.air.auth.model.user.UserRequest;
import com.pulse.air.common.model.ApiException;
import com.pulse.air.common.model.ApiRequest;
import com.pulse.air.common.model.ApiResponse;

public interface AuthService {

	public ApiResponse<String> login(final ApiRequest<LoginRequest> request) throws ApiException;

	public ApiResponse<String> register(final ApiRequest<UserRequest> request) throws ApiException;

	public ApiResponse<Boolean> validateToken(final ApiRequest<String> request) throws IOException;
}
