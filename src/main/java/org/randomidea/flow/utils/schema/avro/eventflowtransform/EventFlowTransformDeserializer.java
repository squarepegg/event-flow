package org.randomidea.flow.utils.schema.avro.eventflowtransform;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.common.serialization.*;
import org.randomidea.flow.transforms.EventFlowTransform;

public class EventFlowTransformDeserializer implements Deserializer<EventFlowTransform> {

    public EventFlowTransformDeserializer() {
    }

    @Override
    public EventFlowTransform deserialize(String topic, byte[] data) {
        ObjectMapper mapper = new ObjectMapper();
        EventFlowTransform eventFlowTransform;
        try {
            eventFlowTransform = mapper.readValue(data, EventFlowTransform.class);
        } catch (Exception e) {
            throw new RuntimeException("Ooops");
        }
        return eventFlowTransform;
    }
}
