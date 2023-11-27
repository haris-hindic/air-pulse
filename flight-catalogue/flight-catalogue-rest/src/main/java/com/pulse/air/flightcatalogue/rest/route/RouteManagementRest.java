package com.pulse.air.flightcatalogue.rest.route;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pulse.air.commons.rest.BaseCRUDController;
import com.pulse.air.flightcatalogue.contract.RouteService;
import com.pulse.air.flightcatalogue.model.route.RouteRequest;
import com.pulse.air.flightcatalogue.model.route.RouteResponse;

@RestController
@RequestMapping("route")
public class RouteManagementRest extends BaseCRUDController<RouteResponse, RouteRequest> {

	public RouteManagementRest(final RouteService service) {
		super(service);
	}

}
