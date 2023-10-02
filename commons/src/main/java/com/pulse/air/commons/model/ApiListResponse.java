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
public class ApiListResponse<T> {

	private Long code;
	private String status;
	private List<T> data;

	public ApiListResponse(final int code, final String status, final List<T> data) {
		super();
		this.code = (long) code;
		this.status = status;
		this.data = data;
	}

}
