package com.pulse.air.flightcatalogue.dao;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;

import com.pulse.air.flightcatalogue.dao.model.FlightEntity;
import com.pulse.air.flightcatalogue.model.flight.ChartData;
import com.pulse.air.flightcatalogue.model.flight.FlightSearchRequest;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;

@Repository
public class FlightRepositoryCustom {

	@PersistenceContext
	private EntityManager entityManager;

	public List<FlightEntity> searchFlights(final FlightSearchRequest request) {
		var hql = new StringBuilder();

		hql.append("select f from FlightEntity f ");

		handleConditions(request, hql);

		hql.append("order by f.basePrice asc");

		TypedQuery<FlightEntity> query = entityManager.createQuery(hql.toString(), FlightEntity.class);
		handleQueryParameters(request, query);

		return query.getResultList();
	}

	private void handleConditions(final FlightSearchRequest request, final StringBuilder hql) {
		var firstCondition = Boolean.TRUE;
		if (request.getStatus() != null) {
			hql.append("where status = :status ");
			firstCondition = Boolean.FALSE;
		}
		if (request.getRouteId() != null && request.getRouteId() != 0L) {
			if (Boolean.TRUE.equals(firstCondition)) {
				hql.append("where f.routeId=:routeId ");
				firstCondition = Boolean.FALSE;
			} else {
				hql.append("and f.routeId=:routeId ");
			}
		}
		if (!CollectionUtils.isEmpty(request.getDepartOn())) {
			hql.append(Boolean.TRUE.equals(firstCondition) ? "where DATE(f.departure) in (:days) "
					: "and DATE(f.departure) in (:days) ");
			if (Boolean.TRUE.equals(firstCondition)) {
				firstCondition = Boolean.FALSE;
			}
		}

		if (StringUtils.isNotEmpty(request.getFlightAfter())) {
			hql.append(Boolean.TRUE.equals(firstCondition) ? "where f.departure > :flightAfter "
					: "and f.departure > :flightAfter ");
		}
	}

	private void handleQueryParameters(final FlightSearchRequest request, final TypedQuery<FlightEntity> query) {
		if (request.getStatus() != null) {
			query.setParameter("status", request.getStatus());
		}
		if (request.getRouteId() != null && request.getRouteId() != 0L) {
			query.setParameter("routeId", request.getRouteId());
		}
		if (!CollectionUtils.isEmpty(request.getDepartOn())) {
			var formatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME;
			var dates = new ArrayList<>();
			request.getDepartOn().forEach(x -> dates.add(LocalDateTime.parse(x, formatter)));
			query.setParameter("days", dates);
		}
		if (StringUtils.isNotEmpty(request.getFlightAfter())) {
			var formatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME;
			var flightAfter = LocalDateTime.parse(request.getFlightAfter(), formatter);
			query.setParameter("flightAfter", flightAfter);
		}
	}

	public List<FlightEntity> findReturnFligtsByRouteId(final Long routeId) {
		var hql = new StringBuilder();

		hql.append("select f from FlightEntity f where status = 'Active' ");
		hql.append("and routeId = (select r1.id from RouteEntity r1 where ");
		hql.append(
				"r1.arrivalAirportId = (select r2.departureAirportId from RouteEntity r2 where r2.id = :routeId) and ");
		hql.append("r1.departureAirportId (select r3.arrivalAirportId from RouteEntity r3 where r3.id = :routeId))");

		var query = entityManager.createQuery(hql.toString(), FlightEntity.class).setParameter("routeId", routeId);
		return query.getResultList();
	}

	public ChartData getFlightCountsByCountryAndCity() {
		var jpql = """
				select country || ', '|| city , count(*)
				 from testing.fc_flight ff join testing.fc_route fr on ff.route_id = fr.id join testing.fc_airport fa on fa.id = fr.departure_airport_id
				 group by country ,city;""";

		return retrieveChartData(jpql);
	}

	public ChartData getFlightCountsByMonths() {
		var jpql = "SELECT TO_CHAR(departure, 'FMMonth YYYY') AS month_year, COUNT(*) AS flight_count "
				+ "FROM testing.fc_flight GROUP BY TO_CHAR(departure, 'FMMonth YYYY') ORDER by MIN(departure);";

		return retrieveChartData(jpql);
	}

	@SuppressWarnings("unchecked")
	private ChartData retrieveChartData(final String jpql) {
		var query = entityManager.createNativeQuery(jpql);
		List<Object[]> rows = query.getResultList();
		var labels = new ArrayList<String>();
		var data = new ArrayList<Long>();

		for (Object[] row : rows) {
			labels.add((String) row[0]);
			data.add((Long) row[1]);
		}

		return new ChartData(labels, data);
	}
}
