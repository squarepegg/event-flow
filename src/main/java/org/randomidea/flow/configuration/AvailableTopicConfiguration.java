package org.randomidea.flow.configuration;

import org.apache.kafka.clients.admin.AdminClient;
import org.apache.kafka.clients.admin.KafkaAdminClient;
import org.randomidea.flow.topics.AvailableTopics;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Properties;

@Configuration
public class AvailableTopicConfiguration {

    @Bean("adminClient")
    public KafkaAdminClient adminClient(@Qualifier("kafkaStreamsProperties") Properties kafkaProperties) {
        return (KafkaAdminClient) AdminClient.create(kafkaProperties);
    }

    @Bean("availableTopics")
    public AvailableTopics availableTopics() {
        return new AvailableTopics();
    }
}
