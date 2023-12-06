package com.pulse.air.flightcatalogue.contract;

import com.pulse.air.common.model.BaseSearchRequest;
import com.pulse.air.commons.contract.BaseCRUDService;
import com.pulse.air.flightcatalogue.model.airport.AirportRequest;
import com.pulse.air.flightcatalogue.model.airport.AirportResponse;

public interface AirportService extends BaseCRUDService<AirportResponse, AirportRequest, BaseSearchRequest> {

}
