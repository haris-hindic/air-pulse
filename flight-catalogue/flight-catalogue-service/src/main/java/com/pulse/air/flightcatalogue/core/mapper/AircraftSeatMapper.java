package com.pulse.air.flightcatalogue.core.mapper;

import org.mapstruct.Mapper;

import com.pulse.air.commons.contract.BaseMapper;
import com.pulse.air.flightcatalogue.dao.model.AircraftSeatEntity;
import com.pulse.air.flightcatalogue.model.aircraftseat.AircraftSeatRequest;
import com.pulse.air.flightcatalogue.model.aircraftseat.AircraftSeatResponse;

@Mapper(componentModel = "spring")
public interface AircraftSeatMapper extends BaseMapper<AircraftSeatEntity, AircraftSeatResponse, AircraftSeatRequest> {

	@Override
	AircraftSeatResponse entityToDto(AircraftSeatEntity entity);

}
