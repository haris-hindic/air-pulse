package com.pulse.air.auth.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pulse.air.auth.dao.model.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, Long> {

	Optional<UserEntity> findByUsername(String username);
}
