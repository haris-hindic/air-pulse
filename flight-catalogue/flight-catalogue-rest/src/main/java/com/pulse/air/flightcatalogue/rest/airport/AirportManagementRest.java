package com.pulse.air.flightcatalogue.rest.airport;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pulse.air.common.model.BaseSearchRequest;
import com.pulse.air.commons.rest.BaseCRUDController;
import com.pulse.air.flightcatalogue.contract.AirportService;
import com.pulse.air.flightcatalogue.model.airport.AirportRequest;
import com.pulse.air.flightcatalogue.model.airport.AirportResponse;

@RestController
@RequestMapping("airport")
public class AirportManagementRest extends BaseCRUDController<AirportResponse, AirportRequest, BaseSearchRequest> {

	public AirportManagementRest(final AirportService service) {
		super(service);
	}

}
