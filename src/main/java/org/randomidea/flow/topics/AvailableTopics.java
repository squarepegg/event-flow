package org.randomidea.flow.topics;

import java.util.Collections;
import java.util.Set;

public class AvailableTopics {

    private volatile Set<String> topics;

    public void setTopics(Set<String> topics) {
        synchronized (Collections.unmodifiableSet(topics)) {
            this.topics = topics;
        }
    }

    public Set<String> getTopics() {
        synchronized (Collections.unmodifiableSet(topics)) {
            return topics;
        }
    }
}
