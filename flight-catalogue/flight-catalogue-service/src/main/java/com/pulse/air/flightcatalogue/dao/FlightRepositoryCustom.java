package com.pulse.air.flightcatalogue.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.pulse.air.flightcatalogue.dao.model.FlightEntity;
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
		Boolean firstCondition = Boolean.TRUE;
		if (request.getStatus() != null) {
			hql.append("where status = :status ");
			firstCondition = Boolean.FALSE;
		}
		if (request.getRouteId() != null) {
			if (Boolean.TRUE.equals(firstCondition)) {
				hql.append("where f.routeId=:routeId ");
				firstCondition = Boolean.FALSE;
			} else {
				hql.append("and f.routeId=:routeId ");
			}
		}
		if (request.getDepartOn() != null) {
			hql.append(Boolean.TRUE.equals(firstCondition) ? "where extract(day from f.departure) = :day "
					: "and extract(day from f.departure) = :day ");
			hql.append("and extract(month from f.departure) = :month ");
			hql.append("and extract(year from f.departure) = :year ");
		}
	}

	private void handleQueryParameters(final FlightSearchRequest request, final TypedQuery<FlightEntity> query) {
		if (request.getStatus() != null) {
			query.setParameter("status", request.getStatus());
		}
		if (request.getRouteId() != null) {
			query.setParameter("routeId", request.getRouteId());
		}
		if (request.getDepartOn() != null) {
			query.setParameter("day", request.getDepartOn().getDayOfMonth());
			query.setParameter("month", request.getDepartOn().getMonthValue());
			query.setParameter("year", request.getDepartOn().getYear());
		}
	}

	public List<FlightEntity> findReturnFligtsByRouteId(final Long routeId) {
		var hql = new StringBuilder();

		hql.append("select f from FlightEntity f where status = 'Active' ");
		hql.append("and routeId = (select r1.id from RouteEntity r1 where ");
		hql.append(
				"r1.arrivalAirportId = (select r2.departureAirportId from RouteEntity r2 where r2.id = :routeId) and ");
		hql.append("r1.departureAirportId (select r3.arrivalAirportId from RouteEntity r3 where r3.id = :routeId))");

		TypedQuery<FlightEntity> query = entityManager.createQuery(hql.toString(), FlightEntity.class)
				.setParameter("routeId", routeId);
		return query.getResultList();
	}
}
