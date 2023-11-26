package com.pulse.air.flightcatalogue.rest.aircraft;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pulse.air.commons.rest.BaseCRUDController;
import com.pulse.air.flightcatalogue.contract.AircraftService;
import com.pulse.air.flightcatalogue.model.aircraft.AircraftRequest;
import com.pulse.air.flightcatalogue.model.aircraft.AircraftResponse;

@RestController
@RequestMapping("aircraft")
public class AircraftManagementRest extends BaseCRUDController<AircraftResponse, AircraftRequest> {

	public AircraftManagementRest(final AircraftService service) {
		super(service);
	}

}

