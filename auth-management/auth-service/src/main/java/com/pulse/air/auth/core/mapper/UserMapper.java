package com.pulse.air.auth.core.mapper;

import org.mapstruct.Mapper;

import com.pulse.air.auth.dao.model.UserEntity;
import com.pulse.air.auth.model.user.UserRequest;
import com.pulse.air.auth.model.user.UserResponse;
import com.pulse.air.commons.contract.BaseMapper;

@Mapper(componentModel = "spring")
public interface UserMapper extends BaseMapper<UserEntity, UserResponse, UserRequest> {

}
