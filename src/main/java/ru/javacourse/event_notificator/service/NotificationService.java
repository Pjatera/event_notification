package ru.javacourse.event_notificator.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.javacourse.event_notificator.db.entity.notification.UserNotificationEntity;
import ru.javacourse.event_notificator.db.repository.NotificationRepository;
import ru.javacourse.event_notificator.db.repository.UserNotificationRepository;
import ru.javacourse.event_notificator.db.repository.UserNotificationRepositoryCustom;
import ru.javacourse.event_notificator.domain.mapper.NotificationMapper;
import ru.javacourse.event_notificator.domain.notification.EventChangeNotification;
import ru.javacourse.event_notificator.web.kafka.KafkaDataFormatException;
import ru.javacourse.eventnotoficationcore.kafka.EventChangeKafkaMessage;

import java.time.LocalDateTime;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Slf4j
public class NotificationService {
    private final NotificationMapper notificationMapper;
    private final AuthenticationService authenticationService;
    private final NotificationRepository notificationRepository;
    private final UserNotificationRepositoryCustom userNotificationRepositoryCustom;
    private final UserNotificationRepository userNotificationRepository;
    private final Period TIME_PERIOD_FOR_DELETION = Period.ofDays(7);

    @Transactional
    public List<EventChangeNotification> getAllUnreadNotificationsByUser() {
        var currentUserAuthenticated = authenticationService.getCurrentUserAuthenticated();
        var userId = currentUserAuthenticated.id();
        var usersNotification = userNotificationRepository.findAllUserNotificationWithStatusisRead(userId, false);
        var list = usersNotification.stream()
                .map(UserNotificationEntity::getNotification)
                .toList();
        return notificationMapper.fromEntityListToDomainList(list);
    }

    @Transactional
    public void markNotificationsAsRead(Long... notificationIds) {
        var currentUserId = authenticationService.getCurrentUserAuthenticated().id();
        var allUserNotificationWithId = userNotificationRepository.findAllUserNotificationWithId(currentUserId, notificationIds);
        allUserNotificationWithId.forEach(notification -> {
            notification.getNotification().setIsRead(true);
        });
    }

    @Transactional
    public void reportAnEvent(EventChangeKafkaMessage event) throws KafkaDataFormatException {
        var eventChangeNotification = notificationMapper.fromKafkaMessageToNotificationEntity(event);
        if (eventChangeNotification == null || eventChangeNotification.getEventId() == null || eventChangeNotification.getOwnerId() == null) {
            throw new KafkaDataFormatException("Bad kafka message,eventId and ownerId not be null");
        }
        var listUsers = event.usersId();
        listUsers.add(event.ownerId());
        listUsers = listUsers.stream().distinct().toList();
        List<UserNotificationEntity> notificationEntities = new ArrayList<>();
        listUsers.forEach(userId -> {
            var userNotificationEntity = new UserNotificationEntity(null,
                    userId,
                    eventChangeNotification);
            notificationEntities.add(userNotificationEntity);
        });

        notificationRepository.save(eventChangeNotification);
        notificationRepository.flush();
        userNotificationRepositoryCustom.bulkInsertUserNotifications(notificationEntities);
        //  userNotificationRepositoryCustom.batchInsert(notificationEntities,100);
    }

    @Transactional
    public void performTaskDeleteNotification() {
        var dateTime = LocalDateTime.now().minus(TIME_PERIOD_FOR_DELETION);

        var allUsersNotificationBefore = userNotificationRepository.findAllUsersNotificationBefore(dateTime);
        var longs = allUsersNotificationBefore.stream()
                .map(UserNotificationEntity::getId)
                .toList();
        userNotificationRepository.deleteAllById(longs);
        log.info("deleting {} notifications", longs.size());
    }
}
