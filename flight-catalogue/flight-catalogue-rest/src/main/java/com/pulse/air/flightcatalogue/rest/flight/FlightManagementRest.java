package com.pulse.air.flightcatalogue.rest.flight;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.pulse.air.common.model.ApiException;
import com.pulse.air.common.model.ApiListResponse;
import com.pulse.air.common.model.ApiRequest;
import com.pulse.air.common.model.ApiResponse;
import com.pulse.air.commons.rest.BaseCRUDController;
import com.pulse.air.flightcatalogue.contract.FlightService;
import com.pulse.air.flightcatalogue.model.flight.FindReturnFlightRequest;
import com.pulse.air.flightcatalogue.model.flight.CheckoutRequest;
import com.pulse.air.flightcatalogue.model.flight.FlightRequest;
import com.pulse.air.flightcatalogue.model.flight.FlightResponse;
import com.pulse.air.flightcatalogue.model.flight.FlightSearchRequest;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.checkout.Session;
import com.stripe.param.checkout.SessionCreateParams;

@RestController
@RequestMapping("flight")
public class FlightManagementRest extends BaseCRUDController<FlightResponse, FlightRequest, FlightSearchRequest> {

	private FlightService flightService;

	public FlightManagementRest(final FlightService service) {
		super(service);
		this.flightService = service;
	}

	@GetMapping("return")
	public ApiListResponse<FlightResponse> findReturnFligtsByRouteId(@RequestParam final Long departureAirportId,
			@RequestParam final Long arrivalAirportId, @RequestParam(required = false) final String departOn,
			@RequestParam(required = false) final String flightAfter, @RequestHeader("AP_USER") final String user)
			throws ApiException {
		return flightService.findReturnFligtsByRouteId(new ApiRequest<>(user,
				new FindReturnFlightRequest(departureAirportId, arrivalAirportId, departOn, flightAfter)));
	}

	@PostMapping(value = "create-checkout")
	public ApiResponse<String> createCheckout(@RequestBody final CheckoutRequest request) throws StripeException {
		Stripe.apiKey = "sk_test_51KR05DIwNGlyHmAKv1n1TGR3LZ5bgvIsSEozrU8rWs8usz8BtEHuhsYwPIlVnDNsZL8rcJV6m65oMudBj4eSQSBE00yEuWupGX";
		var params = SessionCreateParams.builder().setMode(SessionCreateParams.Mode.PAYMENT)
				.setSuccessUrl(request.getSuccesUrl()).setCancelUrl(
						request.getFailUrl())
				.addLineItem(
						SessionCreateParams.LineItem.builder().setQuantity(1L)
								.setPriceData(
										SessionCreateParams.LineItem.PriceData.builder().setCurrency("usd")
												.setUnitAmount(request.getAmount())
												.setProductData(SessionCreateParams.LineItem.PriceData.ProductData
														.builder().setName(request.getName()).build())
												.build())
								.build())
				.build();

		var session = Session.create(params);

		return new ApiResponse<>(HttpStatus.OK.value(), HttpStatus.OK.getReasonPhrase(), session.getUrl());

	}
}
