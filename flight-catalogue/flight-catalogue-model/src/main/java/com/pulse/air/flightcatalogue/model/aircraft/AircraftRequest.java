package com.pulse.air.flightcatalogue.model.aircraft;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class AircraftRequest implements Serializable {
	private static final long serialVersionUID = 1L;

	@NotNull(message = "Average speed is mandatory!")
	private BigDecimal averageSpeed;
	@NotNull(message = "Build date is mandatory!")
	private LocalDateTime buildDate;
	@NotBlank(message = "Manufacturer is mandatory!")
	private String manufacturer;
	@NotBlank(message = "Model is mandatory!")
	private String model;
	@NotBlank(message = "Status is mandatory!")
	private String status;

}
