package com.pulse.air.commons.services;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.HttpStatus;

import com.pulse.air.commons.contract.BaseCRUDService;
import com.pulse.air.commons.contract.BaseMapper;
import com.pulse.air.commons.model.ApiResponse;

public class BaseCRUDServiceImpl<TEntity, TResponse, TRequest, TMapper extends BaseMapper<TEntity, TResponse, TRequest>, TRepository extends JpaRepository<TEntity, Long>>
		extends BaseServiceImpl<TEntity, TResponse, TRequest, TMapper, TRepository>
		implements BaseCRUDService<TResponse, TRequest> {

	private TMapper mapper;
	private TRepository repository;

	public BaseCRUDServiceImpl(final TMapper mapper, final TRepository repository) {
		super(mapper, repository);
		this.mapper = mapper;
		this.repository = repository;
	}

	@Override
	public ApiResponse<TResponse> create(final TRequest request) {
		var entity = mapper.dtoToEntity(request);
		beforeInsert(entity);

		repository.save(entity);

		return new ApiResponse<>(HttpStatus.CREATED.value(), HttpStatus.CREATED.getReasonPhrase(),
				mapper.entityToDto(entity));
	}

	public void beforeInsert(final TEntity entity) {
		// To be overridden
	}

	@Override
	public ApiResponse<TResponse> update(final Long id, final TRequest request) {
		var entityOptional = repository.findById(id);

		if (entityOptional.isPresent()) {
			var entity = entityOptional.get();
			mapper.updateEntity(entity, request);
			beforeUpdate(entity);

			repository.save(entity);
			return new ApiResponse<>(HttpStatus.OK.value(), HttpStatus.OK.getReasonPhrase(),
					mapper.entityToDto(entity));
		} else {
			// HANDLE NON EXISTENT ENTITY
			return null;
		}
	}

	public void beforeUpdate(final TEntity entity) {
		// TO BE OVERRIDEN
	}

	@Override
	public ApiResponse<String> delete(final Long id) {
		var entity = repository.findById(id);

		if (entity.isPresent()) {
			repository.delete(entity.get());
			return new ApiResponse<>(HttpStatus.OK.value(), HttpStatus.OK.getReasonPhrase(),
					String.format("Successfully deleted entity with id -> %s", id));
		} else {
			return new ApiResponse<>(HttpStatus.OK.value(), HttpStatus.OK.getReasonPhrase(),
					String.format("There is no entity with id -> %s", id));
		}
	}

}
