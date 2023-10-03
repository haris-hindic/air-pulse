package com.pulse.air.flightcatalogue.rest.test;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;

import com.pulse.air.commons.model.ApiListResponse;
import com.pulse.air.employee.model.employee.EmployeeResponse;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("testing")
@AllArgsConstructor
public class TestController {
	private WebClient.Builder webClient;

	@GetMapping
	public ApiListResponse<EmployeeResponse> findAll() {

		ParameterizedTypeReference<ApiListResponse<EmployeeResponse>> x = new ParameterizedTypeReference<ApiListResponse<EmployeeResponse>>() {
		};

		return webClient.build().get().uri("http://employee-service/employee").retrieve().bodyToMono(x).block();
	}

}
