package org.randomidea.flow.utils.schema.avro.arraylist;

import org.apache.kafka.common.serialization.*;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

public class ArrayListSerializer implements Serializer<ArrayList<String>> {

    private final Serde<String> valueSerializer = Serdes.serdeFrom(new StringSerializer(), new StringDeserializer());

    // Default constructor needed by Kafka
    public ArrayListSerializer() {
    }

    @Override
    public byte[] serialize(String topic, ArrayList<String> queue) {
        final int size = queue.size();
        final ByteArrayOutputStream baos = new ByteArrayOutputStream();
        final DataOutputStream dos = new DataOutputStream(baos);
        final Iterator<String> iterator = queue.iterator();
        try {
            dos.writeInt(size);
            while (iterator.hasNext()) {
                final byte[] bytes = valueSerializer.serializer().serialize(topic, iterator.next());
                dos.writeInt(bytes.length);
                dos.write(bytes);
            }
        } catch (IOException e) {
            throw new RuntimeException("Unable to serialize ArrayList", e);
        }
        return baos.toByteArray();
    }

    @Override
    public void close() {
        valueSerializer.close();
    }
}
