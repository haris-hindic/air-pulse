package com.pulse.air.auth.core.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.NullValuePropertyMappingStrategy;

import com.pulse.air.auth.dao.model.NotificationEntity;
import com.pulse.air.auth.model.nofitication.NotificationRequest;
import com.pulse.air.auth.model.nofitication.NotificationResponse;
import com.pulse.air.commons.contract.BaseMapper;

@Mapper(componentModel = "spring", nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS, nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface NotificationMapper extends BaseMapper<NotificationEntity, NotificationResponse, NotificationRequest> {

}
