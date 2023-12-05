package com.pulse.air.employee.core.mapper;

import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import com.pulse.air.commons.contract.BaseMapper;
import com.pulse.air.employee.dao.model.AbsenceEntity;
import com.pulse.air.employee.model.absence.AbsenceRequest;
import com.pulse.air.employee.model.absence.AbsenceResponse;

@Mapper(componentModel = "spring")
public interface AbsenceMapper extends BaseMapper<AbsenceEntity, AbsenceResponse, AbsenceRequest> {

	@AfterMapping
	default void setFullName(@MappingTarget final AbsenceResponse response, final AbsenceEntity entity) {
		if (entity.getEmployee() != null) {
			response.setEmployeeFullName(
					entity.getEmployee().getFirstName() + " " + entity.getEmployee().getLastName());
		}
	}
}
