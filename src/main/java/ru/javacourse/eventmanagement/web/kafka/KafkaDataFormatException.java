package ru.javacourse.eventmanagement.web.kafka;

import org.apache.kafka.common.KafkaException;

public class KafkaDataFormatException extends KafkaException {
    public KafkaDataFormatException(String message) {
        super(message);
    }
}
