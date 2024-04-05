package com.pulse.air.flightcatalogue.rest.flight;

import java.math.BigDecimal;
import java.util.List;

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
import com.pulse.air.flightcatalogue.contract.FlightBookingService;
import com.pulse.air.flightcatalogue.contract.FlightService;
import com.pulse.air.flightcatalogue.model.flight.ChartData;
import com.pulse.air.flightcatalogue.model.flight.CheckoutRequest;
import com.pulse.air.flightcatalogue.model.flight.FindReturnFlightRequest;
import com.pulse.air.flightcatalogue.model.flight.FlightRequest;
import com.pulse.air.flightcatalogue.model.flight.FlightResponse;
import com.pulse.air.flightcatalogue.model.flight.FlightSearchRequest;
import com.pulse.air.flightcatalogue.model.flightbooking.FlightBookingRequest;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.checkout.Session;
import com.stripe.param.checkout.SessionCreateParams;

@RestController
@RequestMapping("flight")
public class FlightManagementRest extends BaseCRUDController<FlightResponse, FlightRequest, FlightSearchRequest> {

	private FlightService flightService;
	private FlightBookingService flightBookingService;

	public FlightManagementRest(final FlightService service, final FlightBookingService flightBookingService) {
		super(service);
		this.flightService = service;
		this.flightBookingService = flightBookingService;
	}

	@GetMapping("return")
	public ApiListResponse<FlightResponse> findReturnFligtsByRouteId(@RequestParam final Long departureAirportId,
			@RequestParam final Long arrivalAirportId, @RequestParam(required = false) final List<String> departOn,
			@RequestParam(required = false) final String flightAfter, @RequestHeader("AP_USER") final String user)
			throws ApiException {
		return flightService.findReturnFligtsByRouteId(new ApiRequest<>(user,
				new FindReturnFlightRequest(departureAirportId, arrivalAirportId, departOn, flightAfter)));
	}

	@PostMapping(value = "create-checkout")
	public ApiResponse<String> createCheckout(@RequestBody final CheckoutRequest request,
			@RequestHeader("AP_USER") final String user) throws StripeException, ApiException {
		var booking = flightBookingService.create(new ApiRequest<>(user,
				new FlightBookingRequest(BigDecimal.valueOf(request.getAmount()), request.getUserId(), "Draft",
						request.getFlightId(), request.getReturnFlightId(), request.getLuggage(),
						request.getSeatClass())))
				.getData();

		Stripe.apiKey = "sk_test_51KR05DIwNGlyHmAKv1n1TGR3LZ5bgvIsSEozrU8rWs8usz8BtEHuhsYwPIlVnDNsZL8rcJV6m65oMudBj4eSQSBE00yEuWupGX";
		var params = SessionCreateParams.builder().setMode(SessionCreateParams.Mode.PAYMENT)
				.setSuccessUrl(request.getSuccesUrl().replace("#", booking.getId().toString()))
				.setCancelUrl(
						request.getFailUrl())
				.addLineItem(
						SessionCreateParams.LineItem.builder().setQuantity(1L)
								.setPriceData(
										SessionCreateParams.LineItem.PriceData.builder().setCurrency("usd")
												.setUnitAmount(request.getAmount() * 100L)
												.setProductData(SessionCreateParams.LineItem.PriceData.ProductData
														.builder().setName(request.getName()).build())
												.build())
								.build())
				.build();

		var session = Session.create(params);

		return new ApiResponse<>(HttpStatus.OK.value(), HttpStatus.OK.getReasonPhrase(), session.getUrl());

	}

	@GetMapping("chart-by-months")
	public ApiResponse<ChartData> flightsByMonths(@RequestHeader("AP_USER") final String user) throws ApiException {
		return flightService.flightsByMonths();
	}

	@GetMapping("chart-by-cities")
	public ApiResponse<ChartData> flightsByCity(@RequestHeader("AP_USER") final String user) throws ApiException {
		return flightService.flightsByCity();
	}
}
