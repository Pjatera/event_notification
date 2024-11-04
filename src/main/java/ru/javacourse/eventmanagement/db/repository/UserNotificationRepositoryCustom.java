package ru.javacourse.eventmanagement.db.repository;

import org.springframework.transaction.annotation.Transactional;
import ru.javacourse.eventmanagement.db.entity.notification.UserNotificationEntity;

import java.util.List;

public interface UserNotificationRepositoryCustom {
    @Transactional
    void bulkInsertUserNotifications(List<UserNotificationEntity> userNotificationEntities);

    @Transactional
    void batchInsert(List<UserNotificationEntity> entities, int batchSize);


}
