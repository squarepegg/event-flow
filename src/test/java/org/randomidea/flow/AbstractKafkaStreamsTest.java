package org.randomidea.flow;

import org.apache.kafka.common.serialization.Serde;
import org.apache.kafka.streams.Topology;
import org.apache.kafka.streams.TopologyTestDriver;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Appender;
import org.apache.logging.log4j.core.LogEvent;
import org.apache.logging.log4j.core.Logger;
import org.junit.After;
import org.junit.Before;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Comparator;
import java.util.Properties;

@SpringBootTest(classes = {TestConfiguration.class})
public abstract class AbstractKafkaStreamsTest {

    @Autowired
    @Qualifier("kafkaKeySerde")
    protected Serde keySerde;

    @Autowired
    @Qualifier("kafkaValueSerde")
    protected Serde valueSerde;

    @Autowired
    @Qualifier("kafkaStreamsTestProperties")
    private Properties kafkaProperties;

    @Autowired
    @Qualifier("streamsBuilder")
    private Topology streamsBuilder;

    @Mock
    protected Appender mockAppender;

    @Captor
    protected ArgumentCaptor<LogEvent> loggingArgumentCaptor;

    protected TopologyTestDriver topologyTestDriver;

    private Logger logger;

    @Before
    public void testSetup() {
        Mockito.when(mockAppender.getName()).thenReturn("MockAppender");
        Mockito.when(mockAppender.isStarted()).thenReturn(true);
        logger = (Logger) LogManager.getLogger(getLoggerClass());
        logger.addAppender(mockAppender);
        logger.setLevel(Level.DEBUG);
        topologyTestDriver = new TopologyTestDriver(streamsBuilder, kafkaProperties);
    }

    @After
    public void tearDown() throws IOException {
        if (logger != null) {
            logger.removeAppender(this.mockAppender);
        }

        try {
            topologyTestDriver.close();
        } catch (Exception e) {
            File file = new File(e.getCause().getMessage());
            if (file.isDirectory()) {
                Path path = file.toPath();
                Files.walk(path).sorted(Comparator.reverseOrder()).map(Path::toFile).forEach(File::delete);
            }
        }

    }

    public abstract Class<?> getLoggerClass();
}
