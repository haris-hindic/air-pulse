package com.pulse.air.flightcatalogue.rest.flight;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pulse.air.common.model.BaseSearchRequest;
import com.pulse.air.commons.rest.BaseCRUDController;
import com.pulse.air.flightcatalogue.contract.FlightService;
import com.pulse.air.flightcatalogue.model.flight.FlightRequest;
import com.pulse.air.flightcatalogue.model.flight.FlightResponse;

@RestController
@RequestMapping("flight")
public class FlightManagementRest extends BaseCRUDController<FlightResponse, FlightRequest, BaseSearchRequest> {

	public FlightManagementRest(final FlightService service) {
		super(service);
	}

}
