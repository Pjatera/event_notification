package ru.javacourse.event_notificator.domain.exeption;

import java.time.LocalDateTime;

public record ErrorMessageResponse(String message, String detailedMessage, LocalDateTime dateTime) {
}