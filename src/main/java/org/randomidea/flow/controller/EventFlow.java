package org.randomidea.flow.controller;

import lombok.Data;

import java.io.Serializable;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

@Data
public class EventFlow implements Serializable {

    private String eventType;
    private String timestamp;
    private String topic;

    public EventFlow(String eventType, Long timestamp, String topic) {
        this.eventType = eventType;
        this.topic = topic;

        Instant instant = Instant.ofEpochMilli(timestamp);
        ZonedDateTime dt = ZonedDateTime.ofInstant(instant, ZoneId.of("UTC"));
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMMM dd yyyy HH:mm:ss.SSS");
        this.timestamp = formatter.format(dt);
    }
}
