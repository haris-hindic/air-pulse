package com.pulse.air.employee.core.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.pulse.air.commons.contract.BaseMapper;
import com.pulse.air.employee.dao.model.AbsenceEntity;
import com.pulse.air.employee.model.absence.AbsenceRequest;
import com.pulse.air.employee.model.absence.AbsenceResponse;

@Mapper(componentModel = "spring")
public interface AbsenceMapper extends BaseMapper<AbsenceEntity, AbsenceResponse, AbsenceRequest> {

	@Override
	@Mapping(source = "employee.firstName", target = "employeeFirstName")
	@Mapping(source = "employee.lastName", target = "employeeLastName")
	AbsenceResponse entityToDto(AbsenceEntity entity);
}
