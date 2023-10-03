package com.pulse.air.commons.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.reactive.function.client.WebClient;

@ComponentScan(basePackages = { "com.pulse.air.**.rest", "com.pulse.air.**.core.impl", "com.pulse.air.**.core.mapper",
		"com.pulse.air.**.core.validation", "com.pulse.air.**.dao" })
public class BaseApplicationConfiguration {

	@Bean
	public WebClient webClient() {
		return WebClient.builder().build();
	}
}
