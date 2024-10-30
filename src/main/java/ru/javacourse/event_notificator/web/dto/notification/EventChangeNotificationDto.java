package ru.javacourse.event_notificator.web.dto.notification;

import ru.javacourse.event_notificator.domain.field.FieldChange;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record EventChangeNotificationDto(
        Long eventId,
        FieldChange<String> name,
        FieldChange<Integer> maxPlaces,
        FieldChange<LocalDateTime> date,
        FieldChange<BigDecimal> cost,
        FieldChange<Integer> duration,
        FieldChange<Integer> locationId,
        FieldChange<String> status,
        LocalDateTime createNotificationDate
) {
}
