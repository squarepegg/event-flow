package org.randomidea.flow.controller;

import com.fasterxml.jackson.annotation.JsonRootName;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;
import org.randomidea.flow.transforms.EventFlowTransform;

import java.util.ArrayList;
import java.util.List;

@Data
@JsonRootName("eventFlow")
public class EventFlowResponse {

    private String correlationId;
    private String eventKey;
    private List<EventFlow> eventFlows = new ArrayList<>();

    public EventFlowResponse build(List<EventFlowTransform> events, String eventKey) {
        this.correlationId = events.get(0).getCorrelationId();
        this.eventKey = eventKey;

        for (EventFlowTransform event: events) {
            eventFlows.add(new EventFlow(event.getEventType(), event.getTimestamp(), event.getSourceTopic()));
        }
        return this;
    }

    public String toString() {
        ObjectMapper objectMapper = new ObjectMapper();

        try {
            return objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(this);
        } catch (JsonProcessingException e) {
            return "";
        }
    }
}
