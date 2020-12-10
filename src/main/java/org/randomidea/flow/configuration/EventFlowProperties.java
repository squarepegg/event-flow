package org.randomidea.flow.configuration;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.util.regex.Pattern;

@Getter
@Setter
@Component
@ConfigurationProperties(prefix = "event-flow")
public class EventFlowProperties {

    private static final int MIN_AVAILABLE_TOPICS_REFRESH_MS = 10000;
    private static final int MAX_AVAILABLE_TOPICS_REFRESH_MS = 3600000;

    private static final int MIN_AVAILABLE_TOPICS_TIMEOUT_MS = 10000;
    private static final int MAX_AVAILABLE_TOPICS_TIMEOUT_MS = 30000;

    private static final int MIN_RETENTION_PERIOD_MS = 600000;
    private static final long MAX_RETENTION_PERIOD_MS = 31556952000L;

    private static final int MIN_DELETE_SCHEDULE_MS = 600000;
    private static final long MAX_DELETE_SCHEDULE_MS = 86400000;

    @Min(MIN_AVAILABLE_TOPICS_REFRESH_MS)
    @Max(MAX_AVAILABLE_TOPICS_REFRESH_MS)
    private long availableTopicsRefreshMs;

    @Min(MIN_AVAILABLE_TOPICS_TIMEOUT_MS)
    @Max(MAX_AVAILABLE_TOPICS_TIMEOUT_MS)
    private long availableTopicsTimeoutMs;

    @Min(MIN_RETENTION_PERIOD_MS)
    @Max(MAX_RETENTION_PERIOD_MS)
    private long retentionPeriodMs;

    @Min(MIN_DELETE_SCHEDULE_MS)
    @Max(MAX_DELETE_SCHEDULE_MS)
    private long deleteScheduleMs;

    private String sourceTopicsFilterRegex;

    private String[] sourceTopics;

    private String correlationIdName;

    // If a filter is not specified - use a regex that will match anything
    public Pattern getSourceTopicsFilterRegex() {
        return Pattern.compile(sourceTopicsFilterRegex == null ? "" : sourceTopicsFilterRegex);
    }

    public String[] getSourceTopics() {
        return sourceTopics == null ? new String[]{} : sourceTopics;
    }
}
