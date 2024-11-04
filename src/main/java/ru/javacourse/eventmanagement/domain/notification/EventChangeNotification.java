package ru.javacourse.eventmanagement.domain.notification;

import ru.javacourse.eventmanagement.domain.field.FieldChange;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record EventChangeNotification(
        Long eventId,
        Long ownerId,
        FieldChange<String> name,
        FieldChange<Integer> maxPlaces,
        FieldChange<LocalDateTime> date,
        FieldChange<BigDecimal> cost,
        FieldChange<Integer> duration,
        FieldChange<Long> locationId,
        FieldChange<String> status,
        LocalDateTime createNotificationDate,
        boolean isRead) {
}
