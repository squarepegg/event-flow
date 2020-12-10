package org.randomidea.flow.stream;

import org.randomidea.flow.configuration.EventFlowProperties;
import org.randomidea.flow.topics.AvailableTopics;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
public class FlowSources {

    private final Pattern sourceTopicsFilterRegex;
    private final String[] sourceTopics;
    private final AvailableTopics availableTopics;

    public FlowSources(EventFlowProperties eventFlowProperties, AvailableTopics availableTopics) {
        this.sourceTopicsFilterRegex = eventFlowProperties.getSourceTopicsFilterRegex();
        this.sourceTopics = eventFlowProperties.getSourceTopics();
        this.availableTopics = availableTopics;
    }

    public String[] populateAllSourceTopics() {
        List<String> filteredTopics = availableTopics
                .getTopics()
                .stream()
                .filter((f) -> {
                    if (sourceTopicsFilterRegex.toString().length() == 0) {
                        return true;
                    }
                    return f.matches(sourceTopicsFilterRegex.pattern());
                })
                .collect(Collectors.toList());
        return Stream.concat(Arrays.stream(sourceTopics), filteredTopics.stream()).toArray(String[]::new);
    }
}
