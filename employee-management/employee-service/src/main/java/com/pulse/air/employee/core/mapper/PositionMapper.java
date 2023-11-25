package com.pulse.air.employee.core.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.pulse.air.commons.contract.BaseMapper;
import com.pulse.air.employee.dao.model.PositionEntity;
import com.pulse.air.employee.model.position.PositionRequest;
import com.pulse.air.employee.model.position.PositionResponse;

@Mapper(componentModel = "spring")
public interface PositionMapper extends BaseMapper<PositionEntity, PositionResponse, PositionRequest> {

	@Override
	@Mapping(source = "employee.firstName", target = "employeeFirstName")
	@Mapping(source = "employee.lastName", target = "employeeLastName")
	@Mapping(source = "jobType.title", target = "title")
	PositionResponse entityToDto(PositionEntity entity);
}