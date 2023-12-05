package com.pulse.air.commons.configuration;

import java.util.HashMap;
import java.util.Map;

import org.springframework.context.annotation.Bean;

import com.cloudinary.Cloudinary;

public class BaseCloudinaryConfiguration {
	private static final String CLOUD_NAME = "airpulse";
	private static final String API_KEY = "881788216557576";
	private static final String API_SECRET = "VAr3ySidaMp8ghgdFygXn7Oe5mQ";

	@Bean
	public Cloudinary cloudinary() {
		Map<String, String> config = new HashMap<>();
		config.put("cloud_name", CLOUD_NAME);
		config.put("api_key", API_KEY);
		config.put("api_secret", API_SECRET);
		return new Cloudinary(config);
	}
}