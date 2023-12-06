package com.pulse.air.common.model;

import java.io.Serializable;

import lombok.Data;

@Data
public class BaseSearchRequest implements Serializable {
	private static final long serialVersionUID = 1L;

	private String status;
}
