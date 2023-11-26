package com.pulse.air.flightcatalogue.model.staff;

import java.io.Serializable;
import java.time.LocalDateTime;

import lombok.Data;

@Data
public class StaffResponse implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id;
	private Long aircraftId;
	private LocalDateTime created;
	private String createdBy;
	private Long employeeId;
	private LocalDateTime modified;
	private String modifiedBy;
	private String status;
	private LocalDateTime validFrom;
	private LocalDateTime validTo;

	// private AircraftEntity aircraft;

}
