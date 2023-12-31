package com.pulse.air.employee.contract;

import com.pulse.air.common.model.BaseSearchRequest;
import com.pulse.air.commons.contract.BaseCRUDService;
import com.pulse.air.employee.model.employee.EmployeeRequest;
import com.pulse.air.employee.model.employee.EmployeeResponse;

public interface EmployeeService extends BaseCRUDService<EmployeeResponse, EmployeeRequest, BaseSearchRequest> {

}
