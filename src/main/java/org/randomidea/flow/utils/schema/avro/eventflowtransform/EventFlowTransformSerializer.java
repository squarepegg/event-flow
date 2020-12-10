package org.randomidea.flow.utils.schema.avro.eventflowtransform;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.common.serialization.Serializer;
import org.randomidea.flow.transforms.EventFlowTransform;

public class EventFlowTransformSerializer implements Serializer<EventFlowTransform> {

    public EventFlowTransformSerializer() {
    }

    @Override
    public byte[] serialize(String topic, EventFlowTransform data) {
        byte[] retVal;
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            retVal = objectMapper.writeValueAsString(data).getBytes();
        } catch (Exception e) {
            throw new RuntimeException("Oops");
        }
        return retVal;
    }
}
