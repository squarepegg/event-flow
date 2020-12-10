package org.randomidea.flow.configuration;

import org.apache.kafka.common.serialization.Deserializer;
import org.apache.kafka.common.serialization.Serde;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.common.serialization.Serializer;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Properties;

@Configuration
public class SerdeConfiguration {

    @Bean("kafkaKeySerde")
    public Serde keySerde(@Qualifier("kafkaStreamsProperties") Properties kafkaStreamsProperties) {
        return configureSerde(kafkaStreamsProperties, "key", true);
    }

    @Bean("kafkaValueSerde")
    public Serde valueSerde(@Qualifier("kafkaStreamsProperties") Properties kafkaStreamsProperties) {
        return configureSerde(kafkaStreamsProperties, "value", false);
    }

    private Serde configureSerde(Properties kafkaStreamProperties, String propertyName, boolean isKey) {
        try {
            Deserializer deserializer = (Deserializer) Class.forName(
                    kafkaStreamProperties.get(String.format("%s.deserializer", propertyName)).toString()).newInstance();
            Serializer serializer = (Serializer) Class.forName(
                    kafkaStreamProperties.get(String.format("%s.serializer", propertyName)).toString()).newInstance();
            Serde serde = Serdes.serdeFrom(serializer, deserializer);
            serde.configure(kafkaStreamProperties, isKey);
            return serde;
        } catch (Exception e) {
            throw new RuntimeException("Error configuring default Serdes", e);
        }
    }
}
