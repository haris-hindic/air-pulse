package com.pulse.air.flightcatalogue.rest.aircraftseat;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pulse.air.common.model.ApiException;
import com.pulse.air.common.model.ApiListResponse;
import com.pulse.air.common.model.ApiRequest;
import com.pulse.air.common.model.BaseSearchRequest;
import com.pulse.air.commons.rest.BaseCRUDController;
import com.pulse.air.flightcatalogue.contract.AircraftSeatService;
import com.pulse.air.flightcatalogue.model.aircraftseat.AircraftSeatRequest;
import com.pulse.air.flightcatalogue.model.aircraftseat.AircraftSeatResponse;

@RestController
@RequestMapping("aircraft-seat")
public class AircraftSeatManagementRest
		extends BaseCRUDController<AircraftSeatResponse, AircraftSeatRequest, BaseSearchRequest> {

	private AircraftSeatService absenceService;

	public AircraftSeatManagementRest(final AircraftSeatService service) {
		super(service);
		this.absenceService = service;
	}

	@GetMapping("aircraft/{id}")
	public ApiListResponse<AircraftSeatResponse> findByAircraftId(@PathVariable final Long id,
			@RequestHeader("AP_USER") final String user) throws ApiException {
		return absenceService.findByAircraftId(new ApiRequest<>(user, id));
	}

}
