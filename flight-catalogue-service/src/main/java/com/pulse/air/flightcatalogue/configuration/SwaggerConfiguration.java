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
}
