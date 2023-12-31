package com.pulse.air.discoveryservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class DiscoveryServiceLauncher {

	public static void main(final String[] args) {
		SpringApplication.run(DiscoveryServiceLauncher.class, args);
	}

}
