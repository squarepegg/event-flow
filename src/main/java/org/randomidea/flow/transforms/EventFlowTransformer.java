package org.randomidea.flow.transforms;

import lombok.extern.slf4j.Slf4j;
import org.apache.avro.generic.GenericRecord;
import org.apache.kafka.common.header.Header;
import org.apache.kafka.streams.KeyValue;
import org.apache.kafka.streams.kstream.Transformer;
import org.apache.kafka.streams.processor.ProcessorContext;
import org.apache.kafka.streams.state.KeyValueStore;
import org.randomidea.flow.stream.FlowStores;
import org.randomidea.flow.utils.schema.avro.AvroSchemaUtils;

import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
public class EventFlowTransformer implements Transformer<Object, Object, KeyValue<Object, EventFlowTransform>> {

    private KeyValueStore<Object, List<String>> keyWithCompositeKeyStore;
    private KeyValueStore<String, EventFlowTransform> compositeKeyWithEventStore;
    private KeyValueStore<Object, Long> allKeysWithTimestampStore;
    private ProcessorContext context;
    private final String correlationIdName;

    public EventFlowTransformer(String correlationIdName) {
        this.correlationIdName = correlationIdName;
    }

    @SuppressWarnings("unchecked")
    @Override
    public void init(ProcessorContext context) {
        this.context = context;
        this.keyWithCompositeKeyStore = (KeyValueStore<Object, List<String>>)
                context.getStateStore(FlowStores.KEY_WITH_COMPOSITE_KEY_STORE_NAME);
        this.compositeKeyWithEventStore = (KeyValueStore<String, EventFlowTransform>)
                context.getStateStore(FlowStores.REKEYED_CORRELATED_EVENTS_STORE_NAME);
        this.allKeysWithTimestampStore = (KeyValueStore<Object, Long>)
                context.getStateStore(FlowStores.ALL_KEYS_WITH_TIMESTAMP_KEY_STORE_NAME);
    }

    @Override
    public KeyValue<Object, EventFlowTransform> transform(Object key, Object value) {
        if (key == null || key.toString().equals("")) {
            return null;
        }
        Optional<String> correlationId = getCorrelationId(context, value);

        if (!correlationId.isPresent()) {
            log.warn(String.format("Ignoring %s of type %s. Correlation ID could not be found in Kafka Headers or"
                            + "message body using %s", key, value.getClass().getSimpleName(), correlationIdName));
            return null;
        }

        EventFlowTransform eventFlow = generateEventFlowTransform(key, value, correlationId.get());
        String compositeKey = storeKeyWithChildren(key, correlationId.get());
        storeCompositeKeyWithEvent(compositeKey, eventFlow);
        storeKeyWithTimestamp(key);

        return KeyValue.pair(key, eventFlow);
    }

    private void storeKeyWithTimestamp(Object key) {
        allKeysWithTimestampStore.put(key, Instant.now().toEpochMilli());
    }

    private void storeCompositeKeyWithEvent(String compositeKey, EventFlowTransform eventFlow) {
        compositeKeyWithEventStore.put(compositeKey, eventFlow);
    }

    @Override
    public void close() {
         // Not needed.
    }

    private String storeKeyWithChildren(Object key, String correlationId) {
        String compositeKey = String.format("%s_%s_%s", correlationId, key, context.topic());
        List<String> storeValue =  keyWithCompositeKeyStore.get(key);
        List<String> children = new ArrayList<>();
        if (storeValue != null) {
            children = storeValue;
        }
        children.add(compositeKey);
        keyWithCompositeKeyStore.put(key, children);
        return compositeKey;
    }

    private EventFlowTransform generateEventFlowTransform(Object key, Object value, String correlationId) {
        String sourceTopic = context.topic();
        Long timestamp = context.timestamp();
        EventFlowTransform eventFlow = new EventFlowTransform(key, value.getClass().getSimpleName(),
                correlationId, sourceTopic, timestamp);
        log.debug(String.format("Created new EventFlow %s", eventFlow.toString()));
        return eventFlow;
    }

    private Optional<String> getCorrelationId(ProcessorContext context, Object value) {
        Optional<String> correlationId = getCorrelationIdFromHeaders(context);

        if (!correlationId.isPresent()) {
            correlationId = getCorrelationIdFromMessageBody(value);
        }

        return correlationId;
    }

    private Optional<String> getCorrelationIdFromMessageBody(Object value) {
        return AvroSchemaUtils.getFieldValue((GenericRecord) value, correlationIdName)
                .map(Object::toString);
    }

    private Optional<String> getCorrelationIdFromHeaders(ProcessorContext context) {
        if (context.headers() == null) {
            return Optional.empty();
        } else {
            Header header = context.headers().lastHeader(correlationIdName);
            if (header == null) {
                return Optional.empty();
            } else {
                return header.value() == null ? Optional.empty()
                        : Optional.of(new String(header.value(), StandardCharsets.UTF_8));
            }
        }
    }
}
