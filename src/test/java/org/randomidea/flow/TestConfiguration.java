package org.randomidea.flow;

import io.confluent.kafka.schemaregistry.client.MockSchemaRegistryClient;
import io.confluent.kafka.serializers.KafkaAvroDeserializer;
import io.confluent.kafka.serializers.KafkaAvroSerializer;
import org.apache.kafka.common.serialization.Serde;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.Topology;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import java.util.Properties;

@Configuration
@EnableAutoConfiguration
@ComponentScan(basePackages = "org.randomidea")
public class TestConfiguration {

    @Bean
    public MockSchemaRegistryClient mockSchemaRegistryClient() {
        return new MockSchemaRegistryClient();
    }

    @Bean("kafkaStreamsTestProperties")
    public Properties kafkaProperties(@Qualifier("kafkaStreamsProperties") Properties properties) {
        String schemaRegistryURL = "http://localhost:8081";
        String stateDir = "kafka-streams-test";
        properties.setProperty("state.dir", stateDir);
        properties.setProperty("schema.registry.url", schemaRegistryURL);
        properties.setProperty("enable.idempotence", "false");
        return properties;
    }

    @Bean("streamsHandler")
    @Primary
    public KafkaStreams streamsHandler(@Qualifier("kafkaStreamsTestProperties") Properties properties, @Qualifier("streamsBuilder") Topology topology) {
        return new KafkaStreams(topology, properties);
    }

    @Bean("kafkaKeySerde")
    @Primary
    public Serde keySerde(@Qualifier("kafkaStreamsTestProperties") Properties properties, MockSchemaRegistryClient mockSchemaRegistryClient) {
        Serde keySerde = Serdes.serdeFrom(new KafkaAvroSerializer(mockSchemaRegistryClient), new KafkaAvroDeserializer(mockSchemaRegistryClient));
        keySerde.configure(properties, true);
        return keySerde;
    }

    @Bean("kafkaValueSerde")
    @Primary
    public Serde valueSerde(@Qualifier("kafkaStreamsTestProperties") Properties properties, MockSchemaRegistryClient mockSchemaRegistryClient) {
        Serde valueSerde = Serdes.serdeFrom(new KafkaAvroSerializer(mockSchemaRegistryClient), new KafkaAvroDeserializer(mockSchemaRegistryClient));
        valueSerde.configure(properties, false);
        return valueSerde;
    }
}
