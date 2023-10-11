package com.pulse.air.apigateway.filter;

import org.apache.http.client.utils.URIBuilder;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import com.pulse.air.common.model.ApiResponse;

@Component
public class AuthFilter extends AbstractGatewayFilterFactory<AuthFilter.Config> {

	RouteValidator routeValidator;
	WebClient.Builder webClient;

	public AuthFilter(final RouteValidator routeValidator, final WebClient.Builder webClient) {
		super(Config.class);
		this.routeValidator = routeValidator;
		this.webClient = webClient;
	}

	@Override
	public GatewayFilter apply(final Config config) {

		return ((exchange, chain) -> {

			if (routeValidator.getIsSecured().test(exchange.getRequest())) {
				if (!exchange.getRequest().getHeaders().containsKey(HttpHeaders.AUTHORIZATION)) {
					throw new RuntimeException("missing auth header");
				}

				var authHeader = exchange.getRequest().getHeaders().get(HttpHeaders.AUTHORIZATION).get(0);
				if (authHeader != null && authHeader.startsWith("Bearer ")) {
					authHeader = authHeader.substring(7);
				}

				final var token = authHeader;

				try {
					ParameterizedTypeReference<ApiResponse<Boolean>> type = new ParameterizedTypeReference<ApiResponse<Boolean>>() {
					};

					var builder = new URIBuilder().setScheme("http").setHost("auth-service")
							.setPath("/auth/validate-token").addParameter("token", token);

					var x = webClient.build().get().uri(builder.toString()).retrieve().bodyToMono(type).block();

					if (Boolean.FALSE.equals(x.getData())) {
						throw new RuntimeException("unauthorized access to application");
					}
				} catch (Exception e) {
					e.printStackTrace();
					throw new RuntimeException("unauthorized access to application");
				}
			}

			return chain.filter(exchange);
		});
	}

	public static class Config {

	}
}
