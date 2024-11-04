package ru.javacourse.eventmanagement.web.dto.field;

public record FieldChangeDto <T> (T oldField, T newField) {

}
