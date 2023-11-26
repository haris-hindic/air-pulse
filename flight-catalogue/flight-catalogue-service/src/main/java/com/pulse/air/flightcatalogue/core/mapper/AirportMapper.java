package com.pulse.air.flightcatalogue.core.mapper;

import org.mapstruct.Mapper;

import com.pulse.air.commons.contract.BaseMapper;
import com.pulse.air.flightcatalogue.dao.model.AirportEntity;
import com.pulse.air.flightcatalogue.model.airport.AirportRequest;
import com.pulse.air.flightcatalogue.model.airport.AirportResponse;

@Mapper(componentModel = "spring")
public interface AirportMapper extends BaseMapper<AirportEntity, AirportResponse, AirportRequest> {

	@Override
	AirportResponse entityToDto(AirportEntity entity);

}
