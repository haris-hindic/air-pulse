package com.pulse.air.flightcatalogue.model.staff;

import com.pulse.air.common.model.BaseSearchRequest;

import lombok.Data;

@Data
public class StaffSearchRequest extends BaseSearchRequest {
	private static final long serialVersionUID = 1L;

	private Long aircraftId;
}
