package org.randomidea.flow.utils.schemas;

import io.confluent.avro.random.generator.Generator;
import org.apache.avro.generic.GenericRecord;
import org.randomidea.flow.utils.schema.avro.AvroSchemaUtils;

import java.io.IOException;
import java.util.Random;

public class TestSchemas {

    public TestSchemas() {
        long seed = 100L;
        generator = new Generator(Deposit.SCHEMA$, new Random(seed));
    }

    private Generator generator;

    public Deposit getDeposit() throws IOException {
        return (Deposit) AvroSchemaUtils.convertGenericToSpecific((GenericRecord) generator.generate(), generator.schema());
    }
}
