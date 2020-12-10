package org.randomidea.flow.utils.schemas;

import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import org.apache.avro.AvroRuntimeException;
import org.apache.avro.Schema;
import org.apache.avro.Schema.Parser;
import org.apache.avro.data.RecordBuilder;
import org.apache.avro.io.DatumReader;
import org.apache.avro.io.DatumWriter;
import org.apache.avro.specific.AvroGenerated;
import org.apache.avro.specific.SpecificData;
import org.apache.avro.specific.SpecificDatumReader;
import org.apache.avro.specific.SpecificDatumWriter;
import org.apache.avro.specific.SpecificRecord;
import org.apache.avro.specific.SpecificRecordBase;
import org.apache.avro.specific.SpecificRecordBuilderBase;

@AvroGenerated
public class DepositNote extends SpecificRecordBase implements SpecificRecord {
    private static final long serialVersionUID = 6359794100206462369L;
    public static final Schema SCHEMA$ = (new Parser()).parse("{\"type\":\"record\",\"name\":\"DepositNote\",\"namespace\":\"org.randomidea.flow.utils.schemas\",\"doc\":\"Note related to the deposit\",\"fields\":[{\"name\":\"creationDate\",\"type\":\"long\",\"doc\":\"Date the deposit note was created using Unix epoch in seconds\"},{\"name\":\"note\",\"type\":{\"type\":\"string\",\"avro.java.string\":\"String\"},\"doc\":\"Note associated to the deposit\"}]}");
    /** @deprecated */
    @Deprecated
    public long creationDate;
    /** @deprecated */
    @Deprecated
    public String note;
    private static final DatumWriter WRITER$;
    private static final DatumReader READER$;

    public static Schema getClassSchema() {
        return SCHEMA$;
    }

    public DepositNote() {
    }

    public DepositNote(Long creationDate, String note) {
        this.creationDate = creationDate;
        this.note = note;
    }

    public Schema getSchema() {
        return SCHEMA$;
    }

    public Object get(int field$) {
        switch(field$) {
            case 0:
                return this.creationDate;
            case 1:
                return this.note;
            default:
                throw new AvroRuntimeException("Bad index");
        }
    }

    public void put(int field$, Object value$) {
        switch(field$) {
            case 0:
                this.creationDate = (Long)value$;
                break;
            case 1:
                this.note = (String)value$;
                break;
            default:
                throw new AvroRuntimeException("Bad index");
        }

    }

    public Long getCreationDate() {
        return this.creationDate;
    }

    public void setCreationDate(Long value) {
        this.creationDate = value;
    }

    public String getNote() {
        return this.note;
    }

    public void setNote(String value) {
        this.note = value;
    }

    public static DepositNote.Builder newBuilder() {
        return new DepositNote.Builder();
    }

    public static DepositNote.Builder newBuilder(DepositNote.Builder other) {
        return new DepositNote.Builder(other);
    }

    public static DepositNote.Builder newBuilder(DepositNote other) {
        return new DepositNote.Builder(other);
    }

    public void writeExternal(ObjectOutput out) throws IOException {
        WRITER$.write(this, SpecificData.getEncoder(out));
    }

    public void readExternal(ObjectInput in) throws IOException {
        READER$.read(this, SpecificData.getDecoder(in));
    }

    static {
        WRITER$ = new SpecificDatumWriter(SCHEMA$);
        READER$ = new SpecificDatumReader(SCHEMA$);
    }

    public static class Builder extends SpecificRecordBuilderBase<DepositNote> implements RecordBuilder<DepositNote> {
        private long creationDate;
        private String note;

        private Builder() {
            super(DepositNote.SCHEMA$);
        }

        private Builder(DepositNote.Builder other) {
            super(other);
            if (isValidValue(this.fields()[0], other.creationDate)) {
                this.creationDate = (Long)this.data().deepCopy(this.fields()[0].schema(), other.creationDate);
                this.fieldSetFlags()[0] = true;
            }

            if (isValidValue(this.fields()[1], other.note)) {
                this.note = (String)this.data().deepCopy(this.fields()[1].schema(), other.note);
                this.fieldSetFlags()[1] = true;
            }

        }

        private Builder(DepositNote other) {
            super(DepositNote.SCHEMA$);
            if (isValidValue(this.fields()[0], other.creationDate)) {
                this.creationDate = (Long)this.data().deepCopy(this.fields()[0].schema(), other.creationDate);
                this.fieldSetFlags()[0] = true;
            }

            if (isValidValue(this.fields()[1], other.note)) {
                this.note = (String)this.data().deepCopy(this.fields()[1].schema(), other.note);
                this.fieldSetFlags()[1] = true;
            }

        }

        public Long getCreationDate() {
            return this.creationDate;
        }

        public DepositNote.Builder setCreationDate(long value) {
            this.validate(this.fields()[0], value);
            this.creationDate = value;
            this.fieldSetFlags()[0] = true;
            return this;
        }

        public boolean hasCreationDate() {
            return this.fieldSetFlags()[0];
        }

        public DepositNote.Builder clearCreationDate() {
            this.fieldSetFlags()[0] = false;
            return this;
        }

        public String getNote() {
            return this.note;
        }

        public DepositNote.Builder setNote(String value) {
            this.validate(this.fields()[1], value);
            this.note = value;
            this.fieldSetFlags()[1] = true;
            return this;
        }

        public boolean hasNote() {
            return this.fieldSetFlags()[1];
        }

        public DepositNote.Builder clearNote() {
            this.note = null;
            this.fieldSetFlags()[1] = false;
            return this;
        }

        public DepositNote build() {
            try {
                DepositNote record = new DepositNote();
                record.creationDate = this.fieldSetFlags()[0] ? this.creationDate : (Long)this.defaultValue(this.fields()[0]);
                record.note = this.fieldSetFlags()[1] ? this.note : (String)this.defaultValue(this.fields()[1]);
                return record;
            } catch (Exception var2) {
                throw new AvroRuntimeException(var2);
            }
        }
    }
}
