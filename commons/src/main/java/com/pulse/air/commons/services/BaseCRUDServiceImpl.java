package com.pulse.air.commons.services;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.transaction.annotation.Transactional;

import com.pulse.air.common.model.ApiException;
import com.pulse.air.common.model.ApiListRequest;
import com.pulse.air.common.model.ApiRequest;
import com.pulse.air.common.model.ApiResponse;
import com.pulse.air.common.model.ApiUpdateRequest;
import com.pulse.air.common.model.BaseSearchRequest;
import com.pulse.air.commons.contract.BaseCRUDService;
import com.pulse.air.commons.contract.BaseMapper;

import java.util.Objects;

public class BaseCRUDServiceImpl<TEntity, TResponse, TRequest, TSearch extends BaseSearchRequest, TMapper extends BaseMapper<TEntity, TResponse, TRequest>, TRepository extends JpaRepository<TEntity, Long>>
		extends BaseServiceImpl<TEntity, TResponse, TRequest, TSearch, TMapper, TRepository>
		implements BaseCRUDService<TResponse, TRequest, TSearch> {

	private TMapper mapper;
	private TRepository repository;

	public BaseCRUDServiceImpl(final TMapper mapper, final TRepository repository) {
		super(mapper, repository);
		this.mapper = mapper;
		this.repository = repository;
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public ApiResponse<TResponse> create(final ApiRequest<TRequest> request) throws ApiException {
		var entity = mapper.dtoToEntity(request.getObject());
		beforeInsert(entity, request);

		repository.save(entity);

		return new ApiResponse<>(HttpStatus.CREATED.value(), HttpStatus.CREATED.getReasonPhrase(),
				mapper.entityToDto(entity));
	}

	public void beforeInsert(final TEntity entity, final ApiRequest<TRequest> request) throws ApiException {
		// To be overridden
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public ApiResponse<TResponse> update(final ApiUpdateRequest<TRequest> request) throws ApiException {
		var entityOptional = repository.findById(request.getId());

		if (entityOptional.isPresent()) {
			var entity = entityOptional.get();
			mapper.updateEntity(entity, request.getObject());
			beforeUpdate(entity, request);


			repository.save(entity);
			return new ApiResponse<>(HttpStatus.OK.value(), HttpStatus.OK.getReasonPhrase(),
					mapper.entityToDto(entity));
		} else {
			throw new ApiException(HttpStatus.NOT_FOUND,
					String.format("There is no entity with id -> %s", request.getObject()));
		}
	}

	public void beforeUpdate(final TEntity entity, final ApiUpdateRequest<TRequest> request) throws ApiException {
		// TO BE OVERRIDEN
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public ApiResponse<String> delete(final ApiRequest<Long> request) throws ApiException {
		var entity = repository.findById(request.getObject());

		if (entity.isPresent()) {
			repository.delete(entity.get());
			return new ApiResponse<>(HttpStatus.OK.value(), HttpStatus.OK.getReasonPhrase(),
					String.format("Successfully deleted entity with id -> %s", request.getObject()));
		} else {
			throw new ApiException(HttpStatus.NOT_FOUND,
					String.format("There is no entity with id -> %s", request.getObject()));
		}
	}

	public void beforeDelete(final TEntity entity, final ApiRequest<Long> request) {
		// TO BE OVERRIDEN
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public ApiResponse<String> bulkDelete(final ApiListRequest<Long> request) throws ApiException {

		var ids = request.getObject();
		ids.removeIf(Objects::isNull);

		if (!ids.isEmpty()) {
			repository.deleteAllById(request.getObject());
			return new ApiResponse<>(HttpStatus.OK.value(), HttpStatus.OK.getReasonPhrase(), "Successfully deleted.");
		} else {
			throw new ApiException(HttpStatus.NOT_FOUND, "No valid items provided.");
		}

	}

}
