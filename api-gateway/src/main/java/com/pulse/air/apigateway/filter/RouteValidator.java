package com.pulse.air.apigateway.filter;

import java.util.List;
import java.util.function.Predicate;

import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;

@Component
public class RouteValidator {
	public static final List<String> openApiEndpoints = List.of("auth/login", "auth/register");

	private Predicate<ServerHttpRequest> isSecured = request -> openApiEndpoints.stream()
			.noneMatch(uri -> request.getURI().getPath().contains(uri));

	public Predicate<ServerHttpRequest> getIsSecured() {
		return isSecured;
	}

	public void setIsSecured(final Predicate<ServerHttpRequest> isSecured) {
		this.isSecured = isSecured;
	}
}
