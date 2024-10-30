package ru.javacourse.event_notificator.domain.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;
import ru.javacourse.event_notificator.db.entity.field.*;
import ru.javacourse.event_notificator.domain.field.FieldChange;
import ru.javacourse.event_notificator.web.dto.field.FieldChangeDto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Mapper(unmappedSourcePolicy = ReportingPolicy.IGNORE,
        componentModel = MappingConstants.ComponentModel.SPRING)
public interface FieldMapper {

    FieldChange<String> fromDtoToDomainString(FieldChangeDto<String> dto);

    FieldChange<Integer> fromDtoToDomainInteger(FieldChangeDto<Integer> dto);

    FieldChange<BigDecimal> fromDtoToDomainBigDecimal(FieldChangeDto<BigDecimal> dto);

    FieldChange<LocalDateTime> fromDtoToDomainLocalDateTime(FieldChangeDto<LocalDateTime> dto);

    FieldChangeDto<String> fromDomainToDtoString(FieldChange<String> domain);

    FieldChangeDto<Integer> fromDomainToDtoInteger(FieldChange<Integer> domain);

    FieldChangeDto<BigDecimal> fromDomainToDtoBigDecimal(FieldChange<BigDecimal> domain);

    FieldChangeDto<LocalDateTime> fromDomainToDtoLocalDateTime(FieldChange<LocalDateTime> domain);
    FieldChangeDto<Long> fromDomainToDtoLong(FieldChange<Long> domain);


    FieldChangeIntegerEntity fromDomainToIntegerEntity(FieldChange<Integer> domain);
    FieldChangeLongEntity fromDomainToLongEntity(FieldChange<Long> domain);


    FieldChangeStringEntity fromDomainToStringEntity(FieldChange<String> domain);


    FieldChangeDecimalEntity fromDomainToDecimalEntity(FieldChange<BigDecimal> domain);


    FieldChangeDateTimeEntity fromDomainToDateTimeEntity(FieldChange<LocalDateTime> domain);

    FieldChange<Integer> fromEntityToDomain(FieldChangeIntegerEntity entity);

    FieldChange<String> fromEntityToDomain(FieldChangeStringEntity entity);

    FieldChange<BigDecimal> fromEntityToDomain(FieldChangeDecimalEntity entity);

    FieldChange<LocalDateTime> fromEntityToDomain(FieldChangeDateTimeEntity entity);
    FieldChange<Long> fromEntityToDomain(FieldChangeLongEntity entity);

}
