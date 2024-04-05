package com.pulse.air.auth.rest.user;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pulse.air.auth.contract.UserService;
import com.pulse.air.auth.model.user.UserRequest;
import com.pulse.air.auth.model.user.UserResponse;
import com.pulse.air.common.model.ApiException;
import com.pulse.air.common.model.ApiRequest;
import com.pulse.air.common.model.ApiResponse;
import com.pulse.air.common.model.BaseSearchRequest;
import com.pulse.air.commons.rest.BaseCRUDController;

@RestController
@RequestMapping("user")
public class UserManagementRest extends BaseCRUDController<UserResponse, UserRequest, BaseSearchRequest> {

	private UserService userService;

	public UserManagementRest(final UserService service) {
		super(service);
		this.userService = service;
	}

	@GetMapping("userinfo/{userId}")
	public ApiResponse<String> userinfo(@PathVariable final Long userId) throws ApiException {
		return userService.userinfo(new ApiRequest<>(null, userId));
	}
}
