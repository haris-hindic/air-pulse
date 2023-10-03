package com.pulse.air.employee;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class EmployeeManagementLauncher {
	public static void main(final String[] args) {
		SpringApplication.run(EmployeeManagementLauncher.class, args);
	}
}