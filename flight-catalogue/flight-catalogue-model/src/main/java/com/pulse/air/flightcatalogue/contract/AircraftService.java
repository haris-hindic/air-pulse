package com.pulse.air.flightcatalogue.contract;

import com.pulse.air.common.model.BaseSearchRequest;
import com.pulse.air.commons.contract.BaseCRUDService;
import com.pulse.air.flightcatalogue.model.aircraft.AircraftRequest;
import com.pulse.air.flightcatalogue.model.aircraft.AircraftResponse;

public interface AircraftService extends BaseCRUDService<AircraftResponse, AircraftRequest, BaseSearchRequest> {

}
