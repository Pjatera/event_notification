package ru.javacourse.event_notificator.web.dto.field;

public record FieldChangeDto <T> (T oldField, T newField) {

}
