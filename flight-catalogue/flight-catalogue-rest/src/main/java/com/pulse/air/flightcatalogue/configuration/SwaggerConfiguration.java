package com.pulse.air.flightcatalogue.configuration;

import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.pulse.air.commons.configuration.BaseSwaggerConfiguration;

@Configuration
public class SwaggerConfiguration extends BaseSwaggerConfiguration {

	@Bean
	public GroupedOpenApi testApi() {
		return GroupedOpenApi.builder().group("test-api").packagesToScan("com.pulse.air.flightcatalogue.rest.test")
				.build();
	}

	@Bean
	public GroupedOpenApi flightApi() {
		return GroupedOpenApi.builder().group("flight-api").packagesToScan("com.pulse.air.flightcatalogue.rest.flight")
				.build();
	}

	@Bean
	public GroupedOpenApi aircraftApi() {
		return GroupedOpenApi.builder().group("aircraft-api")
				.packagesToScan("com.pulse.air.flightcatalogue.rest.aircraft").build();
	}

	@Bean
	public GroupedOpenApi aircraftSeatApi() {
		return GroupedOpenApi.builder().group("aircraft-seat-api")
				.packagesToScan("com.pulse.air.flightcatalogue.rest.aircraftseat").build();
	}

	@Bean
	public GroupedOpenApi airportApi() {
		return GroupedOpenApi.builder().group("airport-api")
				.packagesToScan("com.pulse.air.flightcatalogue.rest.airport").build();
	}

	@Bean
	public GroupedOpenApi staffApi() {
		return GroupedOpenApi.builder().group("staff-api").packagesToScan("com.pulse.air.flightcatalogue.rest.staff")
				.build();
	}

	@Bean
	public GroupedOpenApi routeApi() {
		return GroupedOpenApi.builder().group("route-api").packagesToScan("com.pulse.air.flightcatalogue.rest.route")
				.build();
	}

	@Bean
	public GroupedOpenApi flightBookingApi() {
		return GroupedOpenApi.builder().group("flight-booking-api")
				.packagesToScan("com.pulse.air.flightcatalogue.rest.flightbooking").build();
	}
}
