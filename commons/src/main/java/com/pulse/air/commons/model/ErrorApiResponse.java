package com.pulse.air.commons.model;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ErrorApiResponse {

	private Long code;
	private String status;
	private String message;
	private List<String> errors;

}
