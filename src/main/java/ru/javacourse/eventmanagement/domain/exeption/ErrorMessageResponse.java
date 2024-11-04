package ru.javacourse.eventmanagement.domain.exeption;

import java.time.LocalDateTime;

public record ErrorMessageResponse(String message, String detailedMessage, LocalDateTime dateTime) {
}
