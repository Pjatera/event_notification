package ru.javacourse.event_notificator.db.repository;

import org.springframework.transaction.annotation.Transactional;
import ru.javacourse.event_notificator.db.entity.notification.UserNotificationEntity;

import java.util.List;

public interface UserNotificationRepositoryCustom {
    @Transactional
    void bulkInsertUserNotifications(List<UserNotificationEntity> userNotificationEntities);

    @Transactional
    void batchInsert(List<UserNotificationEntity> entities, int batchSize);


}
