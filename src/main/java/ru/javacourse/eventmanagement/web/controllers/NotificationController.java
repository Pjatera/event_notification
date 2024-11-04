package ru.javacourse.eventmanagement.web.controllers;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.javacourse.eventmanagement.domain.mapper.NotificationMapper;
import ru.javacourse.eventmanagement.domain.notification.EventChangeNotification;
import ru.javacourse.eventmanagement.service.NotificationService;
import ru.javacourse.eventmanagement.web.dto.ListNotificationId;
import ru.javacourse.eventmanagement.web.dto.notification.EventChangeNotificationDto;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/notifications")
public class NotificationController {
    private final NotificationService notificationService;
    private final NotificationMapper notificationMapper;


    @GetMapping
    public ResponseEntity<List<EventChangeNotificationDto>> getNotifications() {
        log.info("Request has been received to receive all notifications with status UNREAD");
        List<EventChangeNotification> notifications = notificationService.getAllUnreadNotificationsByUser();
        log.info("Get notifications wits status UNREAD");
        return ResponseEntity.status(HttpStatus.OK).body(notificationMapper.fromDomainListToDtoList(notifications));
    }

    @PostMapping
    public ResponseEntity<Void> createNotification(@RequestBody ListNotificationId notificationIs) {
        log.info("Received a request to mark notifications as read");
        var notificationIds = notificationIs.notificationIds().toArray(Long[]::new);
        notificationService.markNotificationsAsRead(notificationIds);
        log.info("Marked notifications as read");
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }


}
