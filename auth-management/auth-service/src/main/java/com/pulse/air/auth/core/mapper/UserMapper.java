package com.pulse.air.auth.core.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.NullValuePropertyMappingStrategy;

import com.pulse.air.auth.dao.model.UserEntity;
import com.pulse.air.auth.model.user.UserRequest;
import com.pulse.air.auth.model.user.UserResponse;
import com.pulse.air.commons.contract.BaseMapper;

@Mapper(componentModel = "spring", nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS, nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface UserMapper extends BaseMapper<UserEntity, UserResponse, UserRequest> {

}
