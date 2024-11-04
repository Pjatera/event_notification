package ru.javacourse.eventmanagement.domain.field;

public record FieldChange<T>(T oldField, T newField) {
}
