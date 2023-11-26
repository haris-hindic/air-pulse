package com.pulse.air.flightcatalogue.core.mapper;

import org.mapstruct.Mapper;

import com.pulse.air.commons.contract.BaseMapper;
import com.pulse.air.flightcatalogue.dao.model.AircraftEntity;
import com.pulse.air.flightcatalogue.model.aircraft.AircraftRequest;
import com.pulse.air.flightcatalogue.model.aircraft.AircraftResponse;

@Mapper(componentModel = "spring")
public interface AircraftMapper extends BaseMapper<AircraftEntity, AircraftResponse, AircraftRequest> {

	@Override
	AircraftResponse entityToDto(AircraftEntity entity);

}
