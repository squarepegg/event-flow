package org.randomidea.flow.utils;

import org.apache.kafka.common.header.Headers;
import org.apache.kafka.common.header.internals.RecordHeader;
import org.apache.kafka.common.header.internals.RecordHeaders;

import java.nio.charset.StandardCharsets;

public final class KafkaHeaderBuilder {

    private final Headers headers = new RecordHeaders();

    private static final String MESSAGE_VALUE_IS_NULL = "Value cannot be null. for header (%s)";
    private static final String MESSAGE_KEY_IS_NULL = "Key cannot be null.";

    private KafkaHeaderBuilder() {
    }

    public static KafkaHeaderBuilder newBuilder() {
        return new KafkaHeaderBuilder();
    }

    public KafkaHeaderBuilder addHeader(String headerName, String value) throws IllegalArgumentException {
        if (headerName == null) {
            throw new IllegalArgumentException(MESSAGE_KEY_IS_NULL);
        }
        if (value == null) {
            throw new IllegalArgumentException(String.format(MESSAGE_VALUE_IS_NULL, headerName));
        }

        RecordHeader header = new RecordHeader(headerName, value.getBytes(StandardCharsets.UTF_8));
        headers.add(header);
        return this;
    }

    public Headers build() {
        return headers;
    }
}
