package ru.javacourse.event_notificator.domain.field;

public record FieldChange<T>(T oldField, T newField) {
}
