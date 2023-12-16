package com.pulse.air.flightcatalogue.rest.test;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cloudinary.Cloudinary;
import com.pulse.air.common.model.ApiListResponse;
import com.pulse.air.employee.model.employee.EmployeeResponse;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.checkout.Session;
import com.stripe.param.checkout.SessionCreateParams;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("testing")
@AllArgsConstructor
public class TestController {

	private final Cloudinary cloudinary;

	@GetMapping
	public ApiListResponse<EmployeeResponse> findAll() {

//		Dotenv dotenv = Dotenv.load();
//		Cloudinary cloudinary = new Cloudinary(dotenv.get("CLOUDINARY_URL"));
//		cloudinary.config.secure = true;
//		System.out.println(cloudinary.config.cloudName);
		return null;
	}

	@PostMapping(value = "/create-checkout")
	public String uploadPhoto(@RequestBody final String file) throws StripeException {
		Stripe.apiKey = "sk_test_51KR05DIwNGlyHmAKv1n1TGR3LZ5bgvIsSEozrU8rWs8usz8BtEHuhsYwPIlVnDNsZL8rcJV6m65oMudBj4eSQSBE00yEuWupGX";
		var params = SessionCreateParams.builder().setMode(SessionCreateParams.Mode.PAYMENT)
				.setSuccessUrl("http://localhost:4200/success").setCancelUrl(
						"http://localhost:4200/cancel")
				.addLineItem(
						SessionCreateParams.LineItem.builder().setQuantity(1L)
								.setPriceData(
										SessionCreateParams.LineItem.PriceData.builder().setCurrency("usd")
												.setUnitAmount(2000L)
												.setProductData(SessionCreateParams.LineItem.PriceData.ProductData
														.builder().setName("T-shirt").build())
												.build())
								.build())
				.build();

		var session = Session.create(params);

		return session.getUrl();
	}

}
