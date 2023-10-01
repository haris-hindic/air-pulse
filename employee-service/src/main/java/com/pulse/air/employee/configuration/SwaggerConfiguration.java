package com.pulse.air.employee.configuration;

import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.pulse.air.commons.configuration.BaseSwaggerConfiguration;

@Configuration
public class SwaggerConfiguration extends BaseSwaggerConfiguration {

	@Bean
	public GroupedOpenApi employeeApi() {
		return GroupedOpenApi.builder().group("employee-api").packagesToScan("com.pulse.air.employee.rest").build();
	}
}
