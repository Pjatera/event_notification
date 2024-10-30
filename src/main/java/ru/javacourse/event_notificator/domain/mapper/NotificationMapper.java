package ru.javacourse.event_notificator.domain.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;
import ru.javacourse.event_notificator.db.entity.notification.NotificationEntity;
import ru.javacourse.event_notificator.domain.notification.EventChangeNotification;
import ru.javacourse.event_notificator.web.dto.notification.EventChangeNotificationDto;
import ru.javacourse.eventnotoficationcore.kafka.EventChangeKafkaMessage;

import java.time.LocalDateTime;
import java.util.List;

@Mapper(unmappedSourcePolicy = ReportingPolicy.IGNORE,
        componentModel = MappingConstants.ComponentModel.SPRING,
        uses = FieldMapper.class, imports = {LocalDateTime.class})
public interface NotificationMapper {

    NotificationEntity fromEntityToDomain(EventChangeNotification notification);

    EventChangeNotification fromDomainToEntity(NotificationEntity notificationEntity);

    EventChangeNotificationDto fromDomainToDto(EventChangeNotification notification);

    EventChangeNotification fromDtoToDomain(EventChangeNotificationDto notificationDto);

    List<EventChangeNotificationDto> fromDomainListToDtoList(List<EventChangeNotification> notifications);

    List<EventChangeNotification> fromEntityListToDomainList(List<NotificationEntity> notificationEntities);



    @Mapping(target = "isRead", constant = "false")
    @Mapping(target = "createNotificationDate", expression = "java(LocalDateTime.now())")
    @Mapping(target = "id",expression ="java(null)")
    NotificationEntity fromKafkaMessageToNotificationEntity(EventChangeKafkaMessage kafkaMessage);
}
