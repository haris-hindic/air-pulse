package com.pulse.air.flightcatalogue.core.mapper;

import org.mapstruct.Mapper;

import com.pulse.air.commons.contract.BaseMapper;
import com.pulse.air.flightcatalogue.dao.model.RouteEntity;
import com.pulse.air.flightcatalogue.model.route.RouteRequest;
import com.pulse.air.flightcatalogue.model.route.RouteResponse;

@Mapper(componentModel = "spring")
public interface RouteMapper extends BaseMapper<RouteEntity, RouteResponse, RouteRequest> {

	@Override
	RouteResponse entityToDto(RouteEntity entity);

}
