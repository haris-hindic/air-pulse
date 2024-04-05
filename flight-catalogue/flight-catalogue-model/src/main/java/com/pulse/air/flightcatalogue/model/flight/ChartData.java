package com.pulse.air.flightcatalogue.model.flight;

import java.io.Serializable;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ChartData implements Serializable {
	private static final long serialVersionUID = 1L;

	private List<String> chartLabels;
	private List<Long> chartValues;
}
