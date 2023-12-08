package com.pulse.air.flightcatalogue.core.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.pulse.air.commons.contract.BaseMapper;
import com.pulse.air.flightcatalogue.dao.model.FlightEntity;
import com.pulse.air.flightcatalogue.dao.model.RouteEntity;
import com.pulse.air.flightcatalogue.model.flight.FlightRequest;
import com.pulse.air.flightcatalogue.model.flight.FlightResponse;
import com.pulse.air.flightcatalogue.model.route.RouteResponse;

@Mapper(componentModel = "spring")
public interface FlightMapper extends BaseMapper<FlightEntity, FlightResponse, FlightRequest> {

	@Override
	FlightResponse entityToDto(FlightEntity entity);

	default RouteResponse routeEntityToDto(final RouteEntity entity) {
		return Mappers.getMapper(RouteMapper.class).entityToDto(entity);
	}
}
