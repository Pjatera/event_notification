package ru.javacourse.eventmanagement.db.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.javacourse.eventmanagement.db.entity.notification.UserNotificationEntity;

import java.time.LocalDateTime;
import java.util.List;

public interface UserNotificationRepository extends JpaRepository<UserNotificationEntity, Long> {
    @Query("""
            select  u from UserNotificationEntity u
            join fetch u.notification n
            where u.userId = :userId
            and  n.isRead = :isRead
            """)
    List<UserNotificationEntity> findAllUserNotificationWithStatusisRead(Long userId, boolean isRead);

    @Query(
            """
                     select  u from UserNotificationEntity u
                     join fetch u.notification n
                     where u.userId = :userId
                     and  n.id in :notificationIds
                    """
    )
    List<UserNotificationEntity> findAllUserNotificationWithId(Long userId, Long... notificationIds);


    @Query("""
              select  u from UserNotificationEntity u
              join fetch u.notification n
              left join fetch n.date
              left join fetch n.status
              left join fetch n.duration         
              left join fetch n.cost
              left join fetch n.maxPlaces
              left join fetch n.name
              left join fetch n.locationId          
              where n.createNotificationDate <= CAST(:dateTime as date)
            """)
    List<UserNotificationEntity> findAllUsersNotificationBefore(LocalDateTime dateTime);
}
