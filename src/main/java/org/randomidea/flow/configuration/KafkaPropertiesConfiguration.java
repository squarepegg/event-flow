package org.randomidea.flow.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import java.util.Properties;

@Configuration
@PropertySource("classpath:/kafka-defaults.properties")
public class KafkaPropertiesConfiguration {

    @Bean("kafkaStreamsProperties")
    @ConfigurationProperties(prefix = "kafka")
    Properties kafkaProperties() {
        return new Properties();
    }
}
