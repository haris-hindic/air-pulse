package com.pulse.air.commons.configuration;

import org.springframework.context.annotation.Bean;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;

public class BaseSwaggerConfiguration {

	private static final String AIR_PULSE_API = "Air Pulse API";

	@Bean
	public OpenAPI openApiInformation() {

		var contact = new Contact().email("info@airpulse.com").name("Air Pulse");

		var info = new Info().contact(contact).description(AIR_PULSE_API).summary(AIR_PULSE_API).title(AIR_PULSE_API)
				.version("V1.0.0").license(new License().name("Air Pulse service").url("http://springdoc.org"));

		return new OpenAPI().info(info);
	}
}
