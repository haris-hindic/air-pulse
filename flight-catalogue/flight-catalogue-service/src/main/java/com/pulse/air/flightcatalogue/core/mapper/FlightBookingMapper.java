package com.pulse.air.flightcatalogue.core.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.pulse.air.commons.contract.BaseMapper;
import com.pulse.air.flightcatalogue.dao.model.FlightBookingEntity;
import com.pulse.air.flightcatalogue.dao.model.RouteEntity;
import com.pulse.air.flightcatalogue.model.flightbooking.FlightBookingRequest;
import com.pulse.air.flightcatalogue.model.flightbooking.FlightBookingResponse;
import com.pulse.air.flightcatalogue.model.route.RouteResponse;

@Mapper(componentModel = "spring")
public interface FlightBookingMapper
		extends BaseMapper<FlightBookingEntity, FlightBookingResponse, FlightBookingRequest> {

	@Override
	FlightBookingResponse entityToDto(FlightBookingEntity entity);

	default RouteResponse routeEntityToDto(final RouteEntity entity) {
		return Mappers.getMapper(RouteMapper.class).entityToDto(entity);
	}
}
