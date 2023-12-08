package com.pulse.air.flightcatalogue.core.mapper;

import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import com.pulse.air.commons.contract.BaseMapper;
import com.pulse.air.flightcatalogue.dao.model.RouteEntity;
import com.pulse.air.flightcatalogue.model.route.RouteRequest;
import com.pulse.air.flightcatalogue.model.route.RouteResponse;

@Mapper(componentModel = "spring")
public interface RouteMapper extends BaseMapper<RouteEntity, RouteResponse, RouteRequest> {

	@AfterMapping
	default void setAdditionalInfo(@MappingTarget final RouteResponse response, final RouteEntity entity) {

		if (entity.getArrivalAirport() != null) {
			response.setArrivalAirportDetails(
					entity.getArrivalAirport().getName() + " (" + entity.getArrivalAirport().getIataCode() + ")");
		}
		if (entity.getDepartureAirport() != null) {
			response.setDepartureAirportDetails(
					entity.getDepartureAirport().getName() + " (" + entity.getDepartureAirport().getIataCode() + ")");
		}

		if (entity.getAircraft() != null) {
			response.setAircraftDetails(entity.getAircraft().getModel());
		}
	}

}
