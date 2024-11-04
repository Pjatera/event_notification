package ru.javacourse.eventmanagement.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class ScheduledService {
    private final NotificationService notificationService;

    @Scheduled(cron = "${notification.delete.cron}")
    @Async
    public void performTaskDeleteNotification() {
        log.info("Deleting notification started");
        notificationService.performTaskDeleteNotification();
    }
}
