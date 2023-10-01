package com.pulse.air.commons.contract;

import java.util.List;

import org.mapstruct.MappingTarget;

public interface BaseMapper<TEntity, TResponse, TRequest> {

	TResponse entityToDto(TEntity entity);

	TEntity dtoToEntity(TRequest request);

	List<TResponse> entitesToDtos(List<TEntity> entities);

	List<TEntity> dtosToEntities(List<TRequest> request);

	void updateEntity(@MappingTarget TEntity entity, TRequest request);
}