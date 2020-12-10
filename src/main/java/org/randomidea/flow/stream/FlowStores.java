package org.randomidea.flow.stream;

import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.StoreQueryParameters;
import org.apache.kafka.streams.state.QueryableStoreTypes;
import org.apache.kafka.streams.state.ReadOnlyKeyValueStore;
import org.randomidea.flow.transforms.EventFlowTransform;
import org.springframework.stereotype.Component;

@Component
public class FlowStores {

    public static final String KEY_WITH_COMPOSITE_KEY_STORE_NAME = "events-by-key-with-correlation-id-store";
    public static final String REKEYED_CORRELATED_EVENTS_STORE_NAME = "correlated-events-store";
    public static final String ALL_KEYS_WITH_TIMESTAMP_KEY_STORE_NAME = "all-keys-with-timestamp-store";

    private final KafkaStreams kafkaStreams;
    private ReadOnlyKeyValueStore<Object, Object> correlatedEventsByKeyStore;
    private ReadOnlyKeyValueStore<Object, EventFlowTransform> allEventsByCorrelationId;

    public FlowStores(KafkaStreams kafkaStreams) {
        this.kafkaStreams = kafkaStreams;
    }

    public ReadOnlyKeyValueStore<Object, Object> getCorrelatedEventsByKeyStore() {
        if (kafkaStreams == null) {
            return null;
        }

        if (correlatedEventsByKeyStore == null) {
            correlatedEventsByKeyStore = kafkaStreams.store(StoreQueryParameters
                    .fromNameAndType(KEY_WITH_COMPOSITE_KEY_STORE_NAME, QueryableStoreTypes.keyValueStore()));
        }

        return correlatedEventsByKeyStore;
    }

    public ReadOnlyKeyValueStore<Object, EventFlowTransform> getAllEventsByCompositeKey() {
        if (kafkaStreams == null) {
            return null;
        }

        if (allEventsByCorrelationId == null) {
            allEventsByCorrelationId = kafkaStreams.store(StoreQueryParameters
                    .fromNameAndType(REKEYED_CORRELATED_EVENTS_STORE_NAME, QueryableStoreTypes.keyValueStore()));
        }

        return allEventsByCorrelationId;
    }
}
