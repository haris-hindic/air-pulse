package com.pulse.air.flightcatalogue.core.mapper;

import org.mapstruct.Mapper;

import com.pulse.air.commons.contract.BaseMapper;
import com.pulse.air.flightcatalogue.dao.model.StaffEntity;
import com.pulse.air.flightcatalogue.model.staff.StaffRequest;
import com.pulse.air.flightcatalogue.model.staff.StaffResponse;

@Mapper(componentModel = "spring")
public interface StaffMapper extends BaseMapper<StaffEntity, StaffResponse, StaffRequest> {

	@Override
	StaffResponse entityToDto(StaffEntity entity);

}
