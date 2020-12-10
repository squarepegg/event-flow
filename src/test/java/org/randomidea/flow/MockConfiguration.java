package org.randomidea.flow;

import org.apache.kafka.clients.admin.KafkaAdminClient;
import org.apache.kafka.clients.admin.ListTopicsResult;
import org.apache.kafka.common.KafkaFuture;
import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import java.util.Collections;
import java.util.HashSet;

@Configuration
public class MockConfiguration {

    @Bean("adminClient")
    @Primary
    public KafkaAdminClient adminClient() {
        KafkaAdminClient mockClient = Mockito.mock(KafkaAdminClient.class);
        ListTopicsResult mockResult = Mockito.mock(ListTopicsResult.class);
        Mockito.when(mockResult.names()).thenReturn(KafkaFuture.completedFuture(new HashSet<>(Collections.singletonList("integration.site-53"))));
        Mockito.when(mockClient.listTopics()).thenReturn(mockResult);

        return mockClient;
    }
}
