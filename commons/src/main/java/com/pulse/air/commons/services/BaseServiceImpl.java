package com.pulse.air.commons.services;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.HttpStatus;

import com.pulse.air.commons.contract.BaseMapper;
import com.pulse.air.commons.contract.BaseService;
import com.pulse.air.commons.model.ApiListResponse;
import com.pulse.air.commons.model.ApiRequest;
import com.pulse.air.commons.model.ApiResponse;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class BaseServiceImpl<TEntity, TResponse, TRequest, TMapper extends BaseMapper<TEntity, TResponse, TRequest>, TRepository extends JpaRepository<TEntity, Long>>
		implements BaseService<TResponse> {

	private TMapper mapper;
	private TRepository repository;

	@Override
	public ApiListResponse<TResponse> findAll() {

		var entities = repository.findAll();
		return new ApiListResponse<>(HttpStatus.OK.value(), HttpStatus.OK.getReasonPhrase(),
				mapper.entitesToDtos(entities));
	}

	@Override
	public ApiResponse<TResponse> findById(final ApiRequest<Long> request) {
		var entity = repository.findById(request.getObject());
		if (entity.isPresent()) {
			return new ApiResponse<>(HttpStatus.OK.value(), HttpStatus.OK.getReasonPhrase(),
					mapper.entityToDto(entity.get()));
		} else {
			return null;
		}
	}

}
