package ru.javacourse.eventmanagement.db.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.javacourse.eventmanagement.db.entity.notification.UserNotificationEntity;

import java.util.List;

@Repository

public class UserNotificationRepositoryCustomImpl implements UserNotificationRepositoryCustom {

    @PersistenceContext
    private EntityManager entityManager;


    @Override
    @Transactional
    public void bulkInsertUserNotifications(List<UserNotificationEntity> entities) {
        StringBuilder sb = new StringBuilder();
        sb.append("INSERT INTO user_notifications (notification_id, user_id) VALUES ");
        for (int i = 0; i < entities.size(); i++) {
            var userNotificationEntity = entities.get(i);
            sb.append("(");
            sb.append(userNotificationEntity.getNotification().getId()).append(",");
            sb.append(userNotificationEntity.getUserId()).append(")");
            if (i < entities.size() - 1) {
                sb.append(",");
            }
        }
        entityManager.createNativeQuery(sb.toString()).executeUpdate();
    }


    @Override
    @Transactional
    public void batchInsert(List<UserNotificationEntity> entities, int batchSize) {
        for (int i = 0; i < entities.size(); i++) {
            entityManager.persist(entities.get(i));
            if (i > 0 && i % batchSize == 0) {
                entityManager.flush();
                entityManager.clear();
            }
        }
        entityManager.flush();
        entityManager.clear();
    }
}
