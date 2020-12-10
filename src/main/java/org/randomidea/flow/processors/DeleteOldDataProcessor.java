package org.randomidea.flow.processors;

import org.apache.kafka.streams.processor.Processor;
import org.apache.kafka.streams.processor.ProcessorContext;
import org.apache.kafka.streams.processor.PunctuationType;
import org.apache.kafka.streams.state.KeyValueStore;
import org.randomidea.flow.configuration.EventFlowProperties;
import org.randomidea.flow.stream.FlowStores;
import org.randomidea.flow.transforms.EventFlowTransform;

import java.time.Duration;
import java.time.Instant;
import java.util.List;

public class DeleteOldDataProcessor implements Processor<Object, Object> {

    private final EventFlowProperties eventFlowProperties;

    public DeleteOldDataProcessor(EventFlowProperties eventFlowProperties) {
        this.eventFlowProperties = eventFlowProperties;
    }

    private KeyValueStore<Object, List<String>> keyWithCompositeKeyStore;
    private KeyValueStore<String, EventFlowTransform> compositeKeyWithEventStore;
    private KeyValueStore<Object, Long> allKeysWithTimestampStore;

    @SuppressWarnings("unchecked")
    @Override
    public void init(ProcessorContext context) {
        keyWithCompositeKeyStore = (KeyValueStore<Object, List<String>>)
                context.getStateStore(FlowStores.KEY_WITH_COMPOSITE_KEY_STORE_NAME);
        compositeKeyWithEventStore = (KeyValueStore<String, EventFlowTransform>)
                context.getStateStore(FlowStores.REKEYED_CORRELATED_EVENTS_STORE_NAME);
        allKeysWithTimestampStore = (KeyValueStore<Object, Long>)
                context.getStateStore(FlowStores.ALL_KEYS_WITH_TIMESTAMP_KEY_STORE_NAME);

        context.schedule(Duration.ofMillis(eventFlowProperties.getDeleteScheduleMs()), PunctuationType.WALL_CLOCK_TIME,
                this::processStateStore);
    }

    @Override
    public void process(Object key, Object value) {

    }

    @Override
    public void close() {

    }

    private void processStateStore(Long timestamp) {
        Instant deleteIfOlder = Instant.ofEpochMilli(timestamp).minusMillis(eventFlowProperties.getRetentionPeriodMs());

        allKeysWithTimestampStore.all().forEachRemaining(keyValue -> {
            if (Instant.ofEpochMilli(keyValue.value).compareTo(deleteIfOlder) < 0) {
                List<String> compositeKeys = keyWithCompositeKeyStore.get(keyValue.key);
                for (String compositeKey: compositeKeys) {
                    compositeKeyWithEventStore.delete(compositeKey);
                }
                keyWithCompositeKeyStore.delete(keyValue.key);
                allKeysWithTimestampStore.delete(keyValue.key);
            }
        });
    }
}
