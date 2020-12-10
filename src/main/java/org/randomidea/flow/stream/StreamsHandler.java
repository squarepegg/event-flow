package org.randomidea.flow.stream;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.Topology;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationContext;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.io.Closeable;
import java.time.Duration;
import java.util.Properties;

@Slf4j
@Component("streamsHandler")
public class StreamsHandler extends KafkaStreams implements Closeable, Thread.UncaughtExceptionHandler,
        KafkaStreams.StateListener {

    private static final int MAX_DURATION_IN_SECONDS = 5;

    private final ApplicationContext context;
    private final Properties streamProperties;
    private final Topology topology;

    public StreamsHandler(final ApplicationContext context,
                          @Qualifier("kafkaStreamsProperties") final Properties streamProperties,
                          @Qualifier("streamsBuilder") final Topology topology) {
        super(topology, streamProperties);
        this.setUncaughtExceptionHandler(this);
        this.setStateListener(this);
        this.context = context;
        this.streamProperties = streamProperties;
        this.topology = topology;
    }

    @EventListener(ApplicationReadyEvent.class)
    public void startStreamThread() {
        log.info("Starting Stream and outputting topology");
        log.info(topology.describe().toString());
        this.start();
    }

    @Override
    public void uncaughtException(Thread t, Throwable e) {
        log.error("Error Running Stream Thread", e);
        close(Duration.ofSeconds(MAX_DURATION_IN_SECONDS));
    }

    @Override
    public void onChange(KafkaStreams.State newState, KafkaStreams.State oldState) {
        if (State.NOT_RUNNING.equals(newState)) {
            log.info("Stream State changed to Not Running - Shutting down application.");
            SpringApplication.exit(context, () -> 0);
        }
        if (State.ERROR.equals(newState)) {
            log.error("Stream in error state, closing stream.");
            this.close(Duration.ofSeconds(MAX_DURATION_IN_SECONDS));
        }
    }

    public Topology getTopology() {
        return topology;
    }

    public Properties getKafkaStreamsProperties() {
        return streamProperties;
    }
}
