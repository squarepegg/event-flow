package org.randomidea.flow.utils.schema.avro.arraylist;

import org.apache.kafka.common.serialization.*;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

public class ArrayListDeserializer implements Deserializer<ArrayList<String>> {

    private final Serde<String> valueDeserializer = Serdes.serdeFrom(new StringSerializer(), new StringDeserializer());

    public ArrayListDeserializer() {
    }

    @Override
    public void configure(Map<String, ?> configs, boolean isKey) {
        // do nothing
    }

    @Override
    public ArrayList<String> deserialize(String topic, byte[] bytes) {
        if (bytes == null || bytes.length == 0) {
            return null;
        }

        final ArrayList<String> arrayList = new ArrayList<>();
        final DataInputStream dataInputStream = new DataInputStream(new ByteArrayInputStream(bytes));

        try {
            final int records = dataInputStream.readInt();
            for (int i = 0; i < records; i++) {
                final byte[] valueBytes = new byte[dataInputStream.readInt()];
                dataInputStream.read(valueBytes);
                arrayList.add(valueDeserializer.deserializer().deserialize(topic, valueBytes));
            }
        } catch (IOException e) {
            throw new RuntimeException("Unable to deserialize ArrayList", e);
        }

        return arrayList;
    }

    @Override
    public void close() {
        // do nothing
    }
}
