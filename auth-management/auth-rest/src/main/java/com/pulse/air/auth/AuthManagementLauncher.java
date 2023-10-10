package com.pulse.air.auth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class AuthManagementLauncher {
	public static void main(final String[] args) {
		SpringApplication.run(AuthManagementLauncher.class, args);
	}
}