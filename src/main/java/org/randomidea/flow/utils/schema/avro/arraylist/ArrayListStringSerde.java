package org.randomidea.flow.utils.schema.avro.arraylist;

import org.apache.kafka.common.serialization.Deserializer;
import org.apache.kafka.common.serialization.Serde;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.common.serialization.Serializer;

import java.util.ArrayList;
import java.util.Map;

public class ArrayListStringSerde implements Serde<ArrayList<String>> {

    private final Serde<ArrayList<String>> inner;

    public ArrayListStringSerde() {
        inner = Serdes.serdeFrom(new ArrayListSerializer(), new ArrayListDeserializer());
    }

    @Override
    public Serializer<ArrayList<String>> serializer() {
        return inner.serializer();
    }

    @Override
    public Deserializer<ArrayList<String>> deserializer() {
        return inner.deserializer();
    }

    @Override
    public void configure(Map<String, ?> configs, boolean isKey) {
        inner.serializer().configure(configs, isKey);
        inner.deserializer().configure(configs, isKey);
    }

    @Override
    public void close() {
        inner.serializer().close();
        inner.deserializer().close();
    }
}
