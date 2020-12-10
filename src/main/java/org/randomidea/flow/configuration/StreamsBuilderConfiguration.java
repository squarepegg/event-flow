package org.randomidea.flow.configuration;

import org.apache.kafka.common.serialization.Serde;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.Topology;
import org.apache.kafka.streams.kstream.Consumed;
import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.state.KeyValueStore;
import org.apache.kafka.streams.state.StoreBuilder;
import org.apache.kafka.streams.state.Stores;
import org.randomidea.flow.processors.DeleteOldDataProcessor;
import org.randomidea.flow.stream.AvailableTopicsRefresh;
import org.randomidea.flow.stream.FlowSources;
import org.randomidea.flow.stream.FlowStores;
import org.randomidea.flow.transforms.EventFlowTransform;
import org.randomidea.flow.transforms.EventFlowTransformer;
import org.randomidea.flow.utils.schema.avro.arraylist.ArrayListStringSerde;
import org.randomidea.flow.utils.schema.avro.eventflowtransform.EventFlowTransformSerde;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;
import java.util.List;

@Configuration
public class StreamsBuilderConfiguration {

    @Bean("streamsBuilder")
    public Topology streamsBuilder(@Qualifier("kafkaKeySerde") Serde keySerde,
                                   @Qualifier("kafkaValueSerde") Serde valueSerde,
                                   EventFlowProperties eventFlowProperties,
                                   AvailableTopicsRefresh availableTopicsRefresh, FlowSources flowSources) {

        availableTopicsRefresh.refreshAvailableTopics();
        String[] sourceTopics = flowSources.populateAllSourceTopics();

        StreamsBuilder builder = new StreamsBuilder();

        ArrayListStringSerde arrayListStringSerde = new ArrayListStringSerde();
        EventFlowTransformSerde eventFlowTransformSerde = new EventFlowTransformSerde();

        StoreBuilder<KeyValueStore<Object, List<String>>> keyValueStoreBuilderForKeyWithCompositeKeyStore =
                Stores.keyValueStoreBuilder(Stores.persistentKeyValueStore(FlowStores.KEY_WITH_COMPOSITE_KEY_STORE_NAME),
                        keySerde, arrayListStringSerde);

        StoreBuilder<KeyValueStore<String, EventFlowTransform>> keyValueStoreBuilderForCompositeKeyWithEventStore =
                Stores.keyValueStoreBuilder(Stores.persistentKeyValueStore(FlowStores.REKEYED_CORRELATED_EVENTS_STORE_NAME),
                        Serdes.String(), eventFlowTransformSerde);

        StoreBuilder<KeyValueStore<Object, Long>> keyValueStoreStoreBuilderForAllKeysWithTimestampKeyStore =
                Stores.keyValueStoreBuilder(Stores.persistentKeyValueStore(FlowStores.ALL_KEYS_WITH_TIMESTAMP_KEY_STORE_NAME),
                        keySerde, Serdes.Long());

        builder.addStateStore(keyValueStoreBuilderForKeyWithCompositeKeyStore);
        builder.addStateStore(keyValueStoreBuilderForCompositeKeyWithEventStore);
        builder.addStateStore(keyValueStoreStoreBuilderForAllKeysWithTimestampKeyStore);

        KStream<Object, Object> topics = builder.stream(Arrays.asList(sourceTopics),
                Consumed.with(keySerde, valueSerde));

         topics.transform(() -> new EventFlowTransformer(eventFlowProperties.getCorrelationIdName()),
                 FlowStores.KEY_WITH_COMPOSITE_KEY_STORE_NAME, FlowStores.REKEYED_CORRELATED_EVENTS_STORE_NAME,
                 FlowStores.ALL_KEYS_WITH_TIMESTAMP_KEY_STORE_NAME);

        topics.process(() -> new DeleteOldDataProcessor(eventFlowProperties),
                FlowStores.KEY_WITH_COMPOSITE_KEY_STORE_NAME, FlowStores.REKEYED_CORRELATED_EVENTS_STORE_NAME,
                FlowStores.ALL_KEYS_WITH_TIMESTAMP_KEY_STORE_NAME);

        return builder.build();
    }
}
