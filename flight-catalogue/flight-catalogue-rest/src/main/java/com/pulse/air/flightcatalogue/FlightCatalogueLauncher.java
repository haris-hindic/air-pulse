package com.pulse.air.flightcatalogue;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class FlightCatalogueLauncher {

	public static void main(final String[] args) {
		SpringApplication.run(FlightCatalogueLauncher.class, args);
	}
}