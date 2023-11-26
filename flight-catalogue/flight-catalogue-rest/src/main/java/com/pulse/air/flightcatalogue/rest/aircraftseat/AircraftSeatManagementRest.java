package com.pulse.air.flightcatalogue.rest.aircraftseat;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pulse.air.commons.rest.BaseCRUDController;
import com.pulse.air.flightcatalogue.contract.AircraftSeatService;
import com.pulse.air.flightcatalogue.model.aircraftseat.AircraftSeatRequest;
import com.pulse.air.flightcatalogue.model.aircraftseat.AircraftSeatResponse;

@RestController
@RequestMapping("aircraft-seat")
public class AircraftSeatManagementRest extends BaseCRUDController<AircraftSeatResponse, AircraftSeatRequest> {

	public AircraftSeatManagementRest(final AircraftSeatService service) {
		super(service);
	}

}
