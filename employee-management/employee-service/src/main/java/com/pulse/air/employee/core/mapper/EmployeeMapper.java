package com.pulse.air.employee.core.mapper;

import org.mapstruct.AfterMapping;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.springframework.util.CollectionUtils;

import com.pulse.air.commons.contract.BaseMapper;
import com.pulse.air.commons.enums.Status;
import com.pulse.air.employee.dao.model.EmployeeEntity;
import com.pulse.air.employee.model.employee.EmployeeRequest;
import com.pulse.air.employee.model.employee.EmployeeResponse;

@Mapper(componentModel = "spring", builder = @Builder(disableBuilder = true))
public interface EmployeeMapper extends BaseMapper<EmployeeEntity, EmployeeResponse, EmployeeRequest> {

	@AfterMapping
	default void enrichWithPosition(@MappingTarget final EmployeeResponse response, final EmployeeEntity entity) {
		if (!CollectionUtils.isEmpty(entity.getPositions())) {
			var title = "";

			var position = entity.getPositions().stream().filter(x -> Status.ACTIVE.getValue().equals(x.getStatus()))
					.findFirst().orElse(null);

			if (position != null) {
				title = position.getJobType().getTitle();
			}

			response.setTitle(title);
		}
	}
}