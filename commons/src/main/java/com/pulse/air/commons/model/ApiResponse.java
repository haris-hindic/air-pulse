package com.pulse.air.commons.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ApiResponse<T> {

	private Long code;
	private String status;
	private T data;

	public ApiResponse(final int code, final String status, final T data) {
		super();
		this.code = (long) code;
		this.status = status;
		this.data = data;
	}

}