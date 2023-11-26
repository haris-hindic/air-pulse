package com.pulse.air.flightcatalogue.core.mapper;

import org.mapstruct.Mapper;

import com.pulse.air.commons.contract.BaseMapper;
import com.pulse.air.flightcatalogue.dao.model.FlightEntity;
import com.pulse.air.flightcatalogue.model.flight.FlightRequest;
import com.pulse.air.flightcatalogue.model.flight.FlightResponse;

@Mapper(componentModel = "spring")
public interface FlightMapper extends BaseMapper<FlightEntity, FlightResponse, FlightRequest> {

	@Override
	FlightResponse entityToDto(FlightEntity entity);

}
