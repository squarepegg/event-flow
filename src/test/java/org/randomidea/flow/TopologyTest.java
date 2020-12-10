package org.randomidea.flow;

import org.apache.kafka.common.header.Headers;
import org.apache.kafka.streams.TestInputTopic;
import org.apache.kafka.streams.state.ReadOnlyKeyValueStore;
import org.apache.kafka.streams.test.TestRecord;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.randomidea.flow.configuration.StreamsBuilderConfiguration;
import org.randomidea.flow.stream.FlowStores;
import org.randomidea.flow.transforms.EventFlowTransform;
import org.randomidea.flow.utils.KafkaHeaderBuilder;
import org.randomidea.flow.utils.schemas.Deposit;
import org.randomidea.flow.utils.schemas.TestSchemas;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.time.Duration;
import java.util.List;
import java.util.UUID;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {TestConfiguration.class, MockConfiguration.class})
public class TopologyTest extends AbstractKafkaStreamsTest {

    private static final String INTEGRATION_MOVE_RESPONSE_TOPIC = "integration.site-53";

    private TestInputTopic<Object, Object> testInputTopic;

    private ReadOnlyKeyValueStore<Object, List<String>> eventKeyWithCompositeKeyStore;
    private ReadOnlyKeyValueStore<String, EventFlowTransform> compositeKeyWithEventStore;
    private String correlationId;
    private TestSchemas testSchemas;

    @Override
    public Class<?> getLoggerClass() {
        return StreamsBuilderConfiguration.class;
    }

    @Before
    public void setup() {
        testInputTopic = topologyTestDriver.createInputTopic(INTEGRATION_MOVE_RESPONSE_TOPIC, keySerde.serializer(), valueSerde.serializer());

        compositeKeyWithEventStore = topologyTestDriver.getKeyValueStore(FlowStores.REKEYED_CORRELATED_EVENTS_STORE_NAME);
        eventKeyWithCompositeKeyStore = topologyTestDriver.getKeyValueStore(FlowStores.KEY_WITH_COMPOSITE_KEY_STORE_NAME);

        testSchemas = new TestSchemas();

        correlationId = UUID.randomUUID().toString();
    }

    @Test
    public void consume_emptyStringKey_ignored() {
        String inputKey = "";

        TestRecord<Object, Object> record = new TestRecord<>(inputKey, inputKey, addCorrelationIdHeader());
        testInputTopic.pipeInput(record);
        Object events = eventKeyWithCompositeKeyStore.get(inputKey);
        assertNull(events);
    }

    @Test
    public void consume_validEvent_stored() throws IOException {
        String inputKey = UUID.randomUUID().toString();
        Deposit inputValue = testSchemas.getDeposit();

        TestRecord<Object, Object> record = new TestRecord<>(inputKey, inputValue, addCorrelationIdHeader());
        testInputTopic.pipeInput(record);
        List<String> events = eventKeyWithCompositeKeyStore.get(inputKey);
        assertNotNull(events);

        EventFlowTransform eventFlowTransform = compositeKeyWithEventStore.get(events.get(0));
        assertNotNull(eventFlowTransform);
        assertEquals(INTEGRATION_MOVE_RESPONSE_TOPIC, eventFlowTransform.getSourceTopic());
    }

    @Test
    public void consume_validEvent_windowExpiredNoLongerStored() throws IOException {
        String inputKey = UUID.randomUUID().toString();
        Deposit inputValue = testSchemas.getDeposit();

        TestRecord<Object, Object> record = new TestRecord<>(inputKey, inputValue, addCorrelationIdHeader());
        testInputTopic.pipeInput(record);
        List<String> events = eventKeyWithCompositeKeyStore.get(inputKey);
        assertNotNull(events);

        EventFlowTransform eventFlowTransform = compositeKeyWithEventStore.get(events.get(0));
        assertNotNull(eventFlowTransform);
        assertEquals(INTEGRATION_MOVE_RESPONSE_TOPIC, eventFlowTransform.getSourceTopic());

        topologyTestDriver.advanceWallClockTime(Duration.ofHours(2));
        eventFlowTransform = compositeKeyWithEventStore.get(events.get(0));
        assertNull(eventFlowTransform);
    }

    private Headers addCorrelationIdHeader() {
        return KafkaHeaderBuilder.newBuilder()
                .addHeader("CorrelationId", correlationId).build();
    }
}
