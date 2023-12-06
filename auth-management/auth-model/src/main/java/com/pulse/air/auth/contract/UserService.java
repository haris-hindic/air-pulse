package com.pulse.air.auth.contract;

import com.pulse.air.auth.model.user.UserRequest;
import com.pulse.air.auth.model.user.UserResponse;
import com.pulse.air.common.model.BaseSearchRequest;
import com.pulse.air.commons.contract.BaseCRUDService;

public interface UserService extends BaseCRUDService<UserResponse, UserRequest, BaseSearchRequest> {

}
