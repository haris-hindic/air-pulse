package com.pulse.air.auth.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pulse.air.auth.dao.model.NotificationEntity;

public interface NotificationRepository extends JpaRepository<NotificationEntity, Long> {

}
