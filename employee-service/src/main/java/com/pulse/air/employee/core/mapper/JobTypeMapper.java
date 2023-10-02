package com.pulse.air.employee.core.mapper;

import org.mapstruct.Mapper;

import com.pulse.air.commons.contract.BaseMapper;
import com.pulse.air.employee.api.model.jobtype.JobTypeRequest;
import com.pulse.air.employee.api.model.jobtype.JobTypeResponse;
import com.pulse.air.employee.dao.model.JobTypeEntity;

@Mapper(componentModel = "spring")
public interface JobTypeMapper extends BaseMapper<JobTypeEntity, JobTypeResponse, JobTypeRequest> {

}
