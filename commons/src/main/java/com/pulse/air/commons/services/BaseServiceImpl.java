package com.pulse.air.commons.services;

import java.util.List;

import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.HttpStatus;

import com.pulse.air.common.model.ApiException;
import com.pulse.air.common.model.ApiListResponse;
import com.pulse.air.common.model.ApiRequest;
import com.pulse.air.common.model.ApiResponse;
import com.pulse.air.common.model.BaseSearchRequest;
import com.pulse.air.commons.contract.BaseMapper;
import com.pulse.air.commons.contract.BaseService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class BaseServiceImpl<TEntity, TResponse, TRequest, TSearch extends BaseSearchRequest, TMapper extends BaseMapper<TEntity, TResponse, TRequest>, TRepository extends JpaRepository<TEntity, Long>>
		implements BaseService<TResponse, TSearch> {

	private TMapper mapper;
	private TRepository repository;

	@Override
	public ApiListResponse<TResponse> findAll(final ApiRequest<TSearch> request) {
		var example = getExample(request);
		List<TEntity> entities;

		if (example != null) {
			entities = repository.findAll(example);
		} else {
			entities = repository.findAll();
		}

		return new ApiListResponse<>(HttpStatus.OK.value(), HttpStatus.OK.getReasonPhrase(),
				mapper.entitesToDtos(entities));
	}

	public Example<TEntity> getExample(final ApiRequest<TSearch> request) {
		// To be overridden
		return null;
	}

	@Override
	public ApiResponse<TResponse> findById(final ApiRequest<Long> request) throws ApiException {
		var entity = repository.findById(request.getObject());
		if (entity.isPresent()) {
			return new ApiResponse<>(HttpStatus.OK.value(), HttpStatus.OK.getReasonPhrase(),
					mapper.entityToDto(entity.get()));
		} else {
			throw new ApiException(HttpStatus.NOT_FOUND,
					String.format("There is no entity with id -> %s", request.getObject()));
		}
	}

}
