package com.pulse.air.employee.core.mapper;

import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import com.pulse.air.commons.contract.BaseMapper;
import com.pulse.air.employee.dao.model.PositionEntity;
import com.pulse.air.employee.model.position.PositionRequest;
import com.pulse.air.employee.model.position.PositionResponse;

@Mapper(componentModel = "spring")
public interface PositionMapper extends BaseMapper<PositionEntity, PositionResponse, PositionRequest> {

	@Override
	@Mapping(source = "jobType.title", target = "title")
	PositionResponse entityToDto(PositionEntity entity);

	@AfterMapping
	default void setFullName(@MappingTarget final PositionResponse response, final PositionEntity entity) {
		response.setEmployeeFullName(entity.getEmployee().getFirstName() + " " + entity.getEmployee().getLastName());
	}
}