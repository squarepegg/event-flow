package org.randomidea.flow.utils.schema.avro;

import org.apache.avro.Schema;
import org.apache.avro.generic.GenericDatumWriter;
import org.apache.avro.generic.GenericRecord;
import org.apache.avro.io.Decoder;
import org.apache.avro.io.DecoderFactory;
import org.apache.avro.io.Encoder;
import org.apache.avro.io.EncoderFactory;
import org.apache.avro.specific.SpecificDatumReader;
import org.apache.avro.specific.SpecificRecord;
import org.apache.avro.specific.SpecificRecordBase;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

/**
 Tools to iterate through Apache Avro Schemas to update field values.
 */
public final class AvroSchemaUtils {

    private AvroSchemaUtils() {
    }

    public static boolean setFieldValue(GenericRecord payload, String fieldName, Object value) {
        return setFieldValueInternal(payload, fieldName, value);
    }

    public static Optional<Object> getFieldValue(GenericRecord payload, String fieldName) {
        return getFieldValueInternal(payload, fieldName);
    }

    private static Optional<Object> getFieldValueInternal(GenericRecord instance, String fieldName) {
        Optional<Object> fieldValue = Optional.empty();
        for (Schema.Field field : instance.getSchema().getFields()) {
            if (field.name().equals(fieldName)) {
                fieldValue = Optional.of(instance.get(field.pos()));
                break;
            }

            if (field.schema().getType().equals(Schema.Type.NULL) || (field.schema().getType().equals(Schema.Type.UNION)
                    && hasNullType(field))) {
                continue;
            }

            Object fieldInstance = instance.get(field.pos());

            if (fieldInstance != null && field.schema().getType().equals(Schema.Type.UNION) && isArrayRecord(field)) {
                for (Object record : (List<?>) fieldInstance) {
                    fieldValue = getFieldValueInternal((GenericRecord) record, fieldName);
                }
            } else if (fieldInstance != null && field.schema().getType().equals(Schema.Type.UNION) && isRecord(field)) {
                fieldValue = getFieldValueInternal((GenericRecord) fieldInstance, fieldName);
            }
            if (fieldValue.isPresent()) {
                break;
            }
        }
        return fieldValue;
    }

    private static boolean setFieldValueInternal(GenericRecord instance, String fieldName, Object value) {
        boolean setValue = false;
        for (Schema.Field field : instance.getSchema().getFields()) {
            if (field.name().equals(fieldName)) {
                instance.put(field.pos(), value);
                setValue = true;
                break;
            }

            if (field.schema().getType().equals(Schema.Type.NULL) || (field.schema().getType().equals(Schema.Type.UNION)
                    && hasNullType(field))) {
                continue;
            }

            Object fieldInstance = instance.get(field.pos());

            if (fieldInstance != null && field.schema().getType().equals(Schema.Type.UNION) && isArrayRecord(field)) {
                for (Object record : (List<?>) fieldInstance) {
                    setValue = setFieldValueInternal((SpecificRecordBase) record, fieldName, value);
                }
            } else if (fieldInstance != null && field.schema().getType().equals(Schema.Type.UNION) && isRecord(field)) {
                setValue = setFieldValueInternal((SpecificRecordBase) fieldInstance, fieldName, value);
            }
            if (setValue) {
                break;
            }
        }
        return setValue;
    }

    public static boolean isRecord(Schema.Field field) {
        return field.schema().getTypes().parallelStream()
                .anyMatch(schema -> schema.getType().equals(Schema.Type.RECORD));
    }

    public static boolean isArrayRecord(Schema.Field field) {
        return field.schema().getTypes().parallelStream()
                .anyMatch(schema -> schema.getType().equals(Schema.Type.ARRAY)
                        && schema.getElementType().getType().equals(Schema.Type.RECORD));
    }

    public static boolean hasNullType(Schema.Field field) {
        boolean hasNullType = false;
        for (Schema schema : field.schema().getTypes()) {
            if ((schema.getType().equals(Schema.Type.ARRAY)
                    && schema.getElementType().getType().equals(Schema.Type.RECORD))
                    || schema.getType().equals(Schema.Type.RECORD)) {
                hasNullType = false;
                break;
            }
            if (schema.getType().equals(Schema.Type.NULL)) {
                hasNullType = true;
            }
        }
        return hasNullType;
    }

    public static SpecificRecord convertGenericToSpecific(GenericRecord genericRecord, Schema schema) throws IOException {
        GenericDatumWriter<GenericRecord> writer = new GenericDatumWriter<>(schema);
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        Encoder encoder = EncoderFactory.get().binaryEncoder(out, null);
        writer.write(genericRecord, encoder);
        encoder.flush();

        byte[] avroData = out.toByteArray();
        out.close();

        SpecificDatumReader reader = new SpecificDatumReader<>(schema);
        Decoder binaryDecoder = DecoderFactory.get().binaryDecoder(avroData, null);
        Decoder decoder = DecoderFactory.get().validatingDecoder(schema, binaryDecoder);

        return (SpecificRecord) reader.read(null, decoder);
    }
}
