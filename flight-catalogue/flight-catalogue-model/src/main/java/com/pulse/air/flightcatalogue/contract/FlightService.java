package com.pulse.air.flightcatalogue.contract;

import com.pulse.air.common.model.BaseSearchRequest;
import com.pulse.air.commons.contract.BaseCRUDService;
import com.pulse.air.flightcatalogue.model.flight.FlightRequest;
import com.pulse.air.flightcatalogue.model.flight.FlightResponse;

public interface FlightService extends BaseCRUDService<FlightResponse, FlightRequest, BaseSearchRequest> {

}
