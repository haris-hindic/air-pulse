package com.pulse.air.flightcatalogue.model.flight;

import java.io.Serializable;

import lombok.Data;

@Data
public class CheckoutRequest implements Serializable {
	private static final long serialVersionUID = 1L;

	private String succesUrl;
	private String failUrl;
	private String name;
	private Long amount;
}
