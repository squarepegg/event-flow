package org.randomidea.flow.utils.schema.avro.eventflowtransform;

import org.apache.kafka.common.serialization.Deserializer;
import org.apache.kafka.common.serialization.Serde;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.common.serialization.Serializer;
import org.randomidea.flow.transforms.EventFlowTransform;

public class EventFlowTransformSerde implements Serde<EventFlowTransform> {

    private final Serde<EventFlowTransform> inner;

    public EventFlowTransformSerde() {
        inner = Serdes.serdeFrom(new EventFlowTransformSerializer(), new EventFlowTransformDeserializer());
    }

    @Override
    public Serializer<EventFlowTransform> serializer() {
        return inner.serializer();
    }

    @Override
    public Deserializer<EventFlowTransform> deserializer() {
        return inner.deserializer();
    }
}
