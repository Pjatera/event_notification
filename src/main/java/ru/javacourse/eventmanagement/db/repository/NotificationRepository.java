package ru.javacourse.eventmanagement.db.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.javacourse.eventmanagement.db.entity.notification.NotificationEntity;

public interface NotificationRepository extends JpaRepository<NotificationEntity, Long> {


}