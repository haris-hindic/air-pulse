package com.pulse.air.commons.configuration;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = { "com.pulse.air.**.rest", "com.pulse.air.**.core.mapper", "com.pulse.air.**.validation",
		"com.pulse.air.**.dao" })
public class BaseApplicationConfiguration {

}
