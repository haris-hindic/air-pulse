package com.pulse.air.employee.configuration;

import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.pulse.air.commons.configuration.BaseSwaggerConfiguration;

@Configuration
public class SwaggerConfiguration extends BaseSwaggerConfiguration {

	@Bean
	public GroupedOpenApi employeeApi() {
		return GroupedOpenApi.builder().group("employee-api").packagesToScan("com.pulse.air.employee.rest.employee")
				.build();
	}

	@Bean
	public GroupedOpenApi jobTypeApi() {
		return GroupedOpenApi.builder().group("jobtype-api").packagesToScan("com.pulse.air.employee.rest.jobtype")
				.build();
	}

	@Bean
	public GroupedOpenApi absenceApi() {
		return GroupedOpenApi.builder().group("absence-api").packagesToScan("com.pulse.air.employee.rest.absence")
				.build();
	}

	@Bean
	public GroupedOpenApi qualificationApi() {
		return GroupedOpenApi.builder().group("qualification-api")
				.packagesToScan("com.pulse.air.employee.rest.qualification").build();
	}

	@Bean
	public GroupedOpenApi positionApi() {
		return GroupedOpenApi.builder().group("position-api").packagesToScan("com.pulse.air.employee.rest.position")
				.build();
	}
}
