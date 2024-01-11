package com.pulse.air.auth.configuration;

import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.pulse.air.commons.configuration.BaseSwaggerConfiguration;

@Configuration
public class SwaggerConfiguration extends BaseSwaggerConfiguration {

	@Bean
	public GroupedOpenApi authApi() {
		return GroupedOpenApi.builder().group("auth-api").packagesToScan("com.pulse.air.auth.rest.auth").build();
	}

	@Bean
	public GroupedOpenApi userApi() {
		return GroupedOpenApi.builder().group("user-api").packagesToScan("com.pulse.air.auth.rest.user").build();
	}

	@Bean
	public GroupedOpenApi notificationApi() {
		return GroupedOpenApi.builder().group("notification-api").packagesToScan("com.pulse.air.auth.rest.notification")
				.build();
	}
}
