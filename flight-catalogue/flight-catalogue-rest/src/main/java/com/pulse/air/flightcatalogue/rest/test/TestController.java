package com.pulse.air.flightcatalogue.rest.test;

import java.io.IOException;
import java.util.Base64;
import java.util.Map;
import java.util.UUID;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cloudinary.Cloudinary;
import com.pulse.air.common.model.ApiListResponse;
import com.pulse.air.employee.model.employee.EmployeeResponse;

import io.github.cdimascio.dotenv.Dotenv;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("testing")
@AllArgsConstructor
public class TestController {

	private final Cloudinary cloudinary;

	@GetMapping
	public ApiListResponse<EmployeeResponse> findAll() {

		Dotenv dotenv = Dotenv.load();
		Cloudinary cloudinary = new Cloudinary(dotenv.get("CLOUDINARY_URL"));
		cloudinary.config.secure = true;
		System.out.println(cloudinary.config.cloudName);
		return null;
	}

	@PostMapping(value = "/upload")
	public Map uploadPhoto(@RequestBody final String file)
			throws IOException {
		String base64image = file.split(",")[1];
		byte[] decodedImage = Base64.getMimeDecoder().decode(base64image.getBytes());

		return cloudinary.uploader().upload(decodedImage,
				Map.of("public_id", UUID.randomUUID().toString()));
		
	}

}
