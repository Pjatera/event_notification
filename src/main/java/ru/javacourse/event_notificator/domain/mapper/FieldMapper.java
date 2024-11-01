package ru.javacourse.event_notificator.domain.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;
import ru.javacourse.event_notificator.db.entity.field.*;
import ru.javacourse.event_notificator.domain.field.FieldChange;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Mapper(unmappedSourcePolicy = ReportingPolicy.IGNORE,
        componentModel = MappingConstants.ComponentModel.SPRING)
public interface FieldMapper {

    FieldChange<Integer> fromEntityToDomain(FieldChangeIntegerEntity entity);

    FieldChange<String> fromEntityToDomain(FieldChangeStringEntity entity);

    FieldChange<BigDecimal> fromEntityToDomain(FieldChangeDecimalEntity entity);

    FieldChange<LocalDateTime> fromEntityToDomain(FieldChangeDateTimeEntity entity);

    FieldChange<Long> fromEntityToDomain(FieldChangeLongEntity entity);

}
