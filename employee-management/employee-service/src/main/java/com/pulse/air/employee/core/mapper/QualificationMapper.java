package com.pulse.air.employee.core.mapper;

import org.mapstruct.Mapper;

import com.pulse.air.commons.contract.BaseMapper;
import com.pulse.air.employee.dao.model.QualificationEntity;
import com.pulse.air.employee.qualification.QualificationRequest;
import com.pulse.air.employee.qualification.QualificationResponse;

@Mapper(componentModel = "spring")
public interface QualificationMapper
		extends BaseMapper<QualificationEntity, QualificationResponse, QualificationRequest> {

}
