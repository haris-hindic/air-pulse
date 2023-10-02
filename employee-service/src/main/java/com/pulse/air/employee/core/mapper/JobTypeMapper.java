package com.pulse.air.employee.core.mapper;

import org.mapstruct.Mapper;

import com.pulse.air.commons.contract.BaseMapper;
import com.pulse.air.employee.dao.model.JobTypeEntity;
import com.pulse.air.employee.model.jobtype.JobTypeRequest;
import com.pulse.air.employee.model.jobtype.JobTypeResponse;

@Mapper(componentModel = "spring")
public interface JobTypeMapper extends BaseMapper<JobTypeEntity, JobTypeResponse, JobTypeRequest> {

}
