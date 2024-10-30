package ru.javacourse.event_notificator.web.kafka;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaHandler;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import ru.javacourse.event_notificator.service.NotificationService;
import ru.javacourse.eventnotoficationcore.kafka.EventChangeKafkaMessage;

@Component
@Slf4j
@RequiredArgsConstructor
@KafkaListener(topics = "ru.queuing.event_notification.event_change.read")
public class EventNotificationHandler {
    private final NotificationService notificationService;

    @KafkaHandler
    public void handle(EventChangeKafkaMessage event) {
        log.info("Notification received about event ID change : {}", event.eventId());
        try {
            notificationService.reportAnEvent(event);
        } catch (KafkaDataFormatException e) {
            log.error(e.getMessage());
        }

    }
}
