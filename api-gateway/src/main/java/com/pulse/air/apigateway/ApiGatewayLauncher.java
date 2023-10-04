package com.pulse.air.apigateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class ApiGatewayLauncher {

	public static void main(final String[] args) {
		SpringApplication.run(ApiGatewayLauncher.class, args);
	}

}
