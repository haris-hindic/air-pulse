package com.pulse.air.commons.services;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pulse.air.commons.contract.BaseMapper;
import com.pulse.air.commons.contract.BaseService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class BaseServiceImpl<TEntity, TResponse, TRequest, TMapper extends BaseMapper<TEntity, TResponse, TRequest>, TRepository extends JpaRepository<TEntity, Long>>
		implements BaseService<TResponse> {

	private TMapper mapper;
	private TRepository repository;

	@Override
	public List<TResponse> findAll() {
		var entities = repository.findAll();
		return mapper.entitesToDtos(entities);
	}

	@Override
	public TResponse findById(final Long id) {
		var entity = repository.findById(id);
		if (entity.isPresent()) {
			return mapper.entityToDto(entity.get());
		} else {
			return null;
		}
	}

}
