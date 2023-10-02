package com.pulse.air.employee.core.mapper;

import org.mapstruct.Mapper;

import com.pulse.air.commons.contract.BaseMapper;
import com.pulse.air.employee.api.model.employee.EmployeeRequest;
import com.pulse.air.employee.api.model.employee.EmployeeResponse;
import com.pulse.air.employee.dao.model.EmployeeEntity;

@Mapper(componentModel = "spring")
public interface EmployeeMapper extends BaseMapper<EmployeeEntity, EmployeeResponse, EmployeeRequest> {

}
