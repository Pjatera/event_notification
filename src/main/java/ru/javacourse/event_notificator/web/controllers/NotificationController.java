package ru.javacourse.event_notificator.web.controllers;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.javacourse.event_notificator.domain.mapper.NotificationMapper;
import ru.javacourse.event_notificator.domain.notification.EventChangeNotification;
import ru.javacourse.event_notificator.service.NotificationService;
import ru.javacourse.event_notificator.web.dto.ListNotificationId;
import ru.javacourse.event_notificator.web.dto.notification.EventChangeNotificationDto;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
public class NotificationController {
    private final NotificationService notificationService;
    private final NotificationMapper notificationMapper;


    @GetMapping("/notifications")
    @Secured({"ROLE_ADMIN", "ROLE_USER"})
    public ResponseEntity<List<EventChangeNotificationDto>> getNotifications() {
        log.info("Request has been received to receive all notifications with status UNREAD");
        List<EventChangeNotification> notifications = notificationService.getAllUnreadNotificationsByUser();
        log.info("Get notifications wits status UNREAD");
        return ResponseEntity.status(HttpStatus.OK).body(notificationMapper.fromDomainListToDtoList(notifications));
    }

    @PostMapping("/notifications")
    @Secured({"ROLE_ADMIN", "ROLE_USER"})
    public ResponseEntity<Void> createNotification(@RequestBody ListNotificationId notificationIs) {
        log.info("Received a request to mark notifications as read");
        var notificationIds = notificationIs.notificationIds().toArray(Long[]::new);
        notificationService.markNotificationsAsRead(notificationIds);
        log.info("Marked notifications as read");
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }


}
