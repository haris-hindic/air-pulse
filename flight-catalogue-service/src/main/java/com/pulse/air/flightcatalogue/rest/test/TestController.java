package com.pulse.air.flightcatalogue.rest.test;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;

import com.pulse.air.commons.model.ApiListResponse;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("testing")
@AllArgsConstructor
public class TestController {
	private WebClient webClient;

	@GetMapping
	public ApiListResponse<Object> findAll() {

		ParameterizedTypeReference<ApiListResponse<Object>> x = new ParameterizedTypeReference<ApiListResponse<Object>>() {
		};

		return webClient.get().uri("http://localhost:8080/employee").retrieve().bodyToMono(x).block();
	}

}
