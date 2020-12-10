package org.randomidea.flow.stream;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.admin.KafkaAdminClient;
import org.randomidea.flow.configuration.EventFlowProperties;
import org.randomidea.flow.topics.AvailableTopics;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.concurrent.TimeUnit;

@Slf4j
@Component
public class AvailableTopicsRefresh {

    @Getter
    private final AvailableTopics availableTopics;

    private final long topicRefreshTimeoutMs;
    private final KafkaAdminClient adminClient;

    public AvailableTopicsRefresh(KafkaAdminClient adminClient, AvailableTopics availableTopics,
                                  EventFlowProperties eventFlowProperties) {
        this.adminClient = adminClient;
        this.availableTopics = availableTopics;
        this.topicRefreshTimeoutMs = eventFlowProperties.getAvailableTopicsTimeoutMs();
    }

    public void refreshAvailableTopics() {
        Set<String> topics = null;
        try {
            topics = adminClient.listTopics().names().get(topicRefreshTimeoutMs, TimeUnit.MILLISECONDS);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }

        if (topics != null) {
            if (log.isTraceEnabled()) {
                log.trace("Updated Available Topics: " + topics);
            }
            availableTopics.setTopics(topics);
        }
    }
}
