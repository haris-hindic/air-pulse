package com.pulse.air.commons.services;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pulse.air.commons.contract.BaseCRUDService;
import com.pulse.air.commons.contract.BaseMapper;

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
	public TResponse create(final TRequest request) {
		var entity = mapper.dtoToEntity(request);
		beforeInsert(entity);

		repository.save(entity);

		return mapper.entityToDto(entity);
	}

	public void beforeInsert(final TEntity entity) {
		// To be overridden
	}

	@Override
	public TResponse update(final Long id, final TRequest request) {
		var entityOptional = repository.findById(id);

		if (entityOptional.isPresent()) {
			var entity = entityOptional.get();
			mapper.updateEntity(entity, request);
			beforeUpdate(entity);

			repository.save(entity);
			return mapper.entityToDto(entity);
		} else {
			// HANDLE NON EXISTENT ENTITY
			return null;
		}
	}

	public void beforeUpdate(final TEntity entity) {
		// TO BE OVERRIDEN
	}

	@Override
	public String delete(final Long id) {
		var entity = repository.findById(id);

		if (entity.isPresent()) {
			repository.delete(entity.get());
			return String.format("Successfully deleted entity with id -> %s", id);
		} else {
			return String.format("There is no entity with id -> %s", id);
		}
	}

}
