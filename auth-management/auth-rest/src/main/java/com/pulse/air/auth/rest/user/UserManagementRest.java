package com.pulse.air.auth.rest.user;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pulse.air.auth.model.user.UserRequest;
import com.pulse.air.auth.model.user.UserResponse;
import com.pulse.air.common.model.BaseSearchRequest;
import com.pulse.air.commons.contract.BaseCRUDService;
import com.pulse.air.commons.rest.BaseCRUDController;

@RestController
@RequestMapping("user")
public class UserManagementRest extends BaseCRUDController<UserResponse, UserRequest, BaseSearchRequest> {

	public UserManagementRest(final BaseCRUDService<UserResponse, UserRequest, BaseSearchRequest> service) {
		super(service);
	}

}
