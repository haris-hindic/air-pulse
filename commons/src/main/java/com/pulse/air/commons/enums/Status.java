package com.pulse.air.commons.enums;

public enum Status {
	ACTIVE("Active"), INACTIVE("Inactive"), CLOSED("Closed"), DRAFT("Draft");

	public final String value;

	private Status(final String value) {
		this.value = value;
	}

	public String getValue() {
		return this.value;
	}
}
