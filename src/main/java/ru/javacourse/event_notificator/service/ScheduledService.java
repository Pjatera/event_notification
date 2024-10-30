package ru.javacourse.event_notificator.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class ScheduledService {
    private final NotificationService notificationService;

    @Scheduled(cron = "${notification.delete.cron}")
    public void performTaskDeleteNotification() {
        log.info("Deleting notification started");
        notificationService.performTaskDeleteNotification();
    }
}
