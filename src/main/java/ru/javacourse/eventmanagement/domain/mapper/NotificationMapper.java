package ru.javacourse.eventmanagement.domain.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;
import ru.javacourse.eventmanagement.db.entity.notification.NotificationEntity;
import ru.javacourse.eventmanagement.domain.notification.EventChangeNotification;
import ru.javacourse.eventmanagement.web.dto.notification.EventChangeNotificationDto;
import ru.javacourse.eventmanagement.web.kafka.EventChangeKafkaMessage;


import java.time.LocalDateTime;
import java.util.List;

@Mapper(unmappedSourcePolicy = ReportingPolicy.IGNORE,
        componentModel = MappingConstants.ComponentModel.SPRING,
        uses = FieldMapper.class, imports = {LocalDateTime.class})
public interface NotificationMapper {


    EventChangeNotification fromDomainToEntity(NotificationEntity notificationEntity);

    EventChangeNotificationDto fromDomainToDto(EventChangeNotification notification);

    List<EventChangeNotificationDto> fromDomainListToDtoList(List<EventChangeNotification> notifications);

    List<EventChangeNotification> fromEntityListToDomainList(List<NotificationEntity> notificationEntities);


    @Mapping(target = "isRead", constant = "false")
    @Mapping(target = "createNotificationDate", expression = "java(LocalDateTime.now())")
    @Mapping(target = "id", expression = "java(null)")
    NotificationEntity fromKafkaMessageToNotificationEntity(EventChangeKafkaMessage kafkaMessage);
}
