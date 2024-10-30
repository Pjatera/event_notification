package ru.javacourse.event_notificator.web.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.javacourse.event_notificator.domain.mapper.NotificationMapper;
import ru.javacourse.event_notificator.domain.notification.EventChangeNotification;
import ru.javacourse.event_notificator.service.NotificationService;
import ru.javacourse.event_notificator.web.dto.notification.EventChangeNotificationDto;

import java.util.List;

@RestController("/notifications")
@RequiredArgsConstructor
public class NotificationController {
    private final NotificationService notificationService;
    private final NotificationMapper notificationMapper;


    @GetMapping
    public ResponseEntity<List<EventChangeNotificationDto>> getNotifications() {
        List<EventChangeNotification> notifications = notificationService.getAllUnreadNotificationsByUser();
        return ResponseEntity.status(HttpStatus.OK).body(notificationMapper.fromDomainListToDtoList(notifications));
    }

    @PostMapping
    public ResponseEntity<Void> createNotification(@RequestBody Long... notificationIds) {
        notificationService.markNotificationsAsRead(notificationIds);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }


}
