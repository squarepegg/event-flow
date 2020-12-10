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
public class DepositPayment extends SpecificRecordBase implements SpecificRecord {
    private static final long serialVersionUID = -7190033995318116071L;
    public static final Schema SCHEMA$ = (new Parser()).parse("{\"type\":\"record\",\"name\":\"DepositPayment\",\"namespace\":\"org.randomidea.flow.utils.schemas\",\"doc\":\"Payment made towards the deposit\",\"fields\":[{\"name\":\"id\",\"type\":\"int\",\"doc\":\"The unique identifier of a bidder deposit\"},{\"name\":\"sourceSystem\",\"type\":[\"null\",{\"type\":\"string\",\"avro.java.string\":\"String\"}],\"doc\":\"Source system of the registration\",\"default\":null},{\"name\":\"paymentType\",\"type\":{\"type\":\"enum\",\"name\":\"PaymentType\",\"doc\":\"Type of the deposit payment\",\"symbols\":[\"AC\",\"BD\",\"CC\",\"CH\",\"CR\",\"CT\",\"DB\",\"MO\",\"RC\",\"TC\",\"WT\"]},\"doc\":\"Type of the deposit payment\"},{\"name\":\"depositAmount\",\"type\":{\"type\":\"string\",\"avro.java.string\":\"String\"},\"doc\":\"Amount of the deposit payment\"},{\"name\":\"currency\",\"type\":{\"type\":\"string\",\"avro.java.string\":\"String\"},\"doc\":\"3 digit ISO currency code of the deposit payment\"},{\"name\":\"creationDate\",\"type\":[\"null\",\"long\"],\"doc\":\"Date the payment was taken using Unix epoch in seconds\",\"default\":null},{\"name\":\"takenBy\",\"type\":[\"null\",{\"type\":\"string\",\"avro.java.string\":\"String\"}],\"doc\":\"Reference of who took the deposit payment\",\"default\":null},{\"name\":\"takenAt\",\"type\":[\"null\",{\"type\":\"string\",\"avro.java.string\":\"String\"}],\"doc\":\"Location where deposit payment was taken\",\"default\":null},{\"name\":\"voidFlag\",\"type\":\"boolean\",\"doc\":\"Indicator if the deposit payment as been voided\",\"default\":false},{\"name\":\"voidedBy\",\"type\":[\"null\",{\"type\":\"string\",\"avro.java.string\":\"String\"}],\"doc\":\"Reference of who voided the deposit payment\",\"default\":null},{\"name\":\"voidNotes\",\"type\":[\"null\",{\"type\":\"string\",\"avro.java.string\":\"String\"}],\"doc\":\"Details of why the deposit payment was voided\",\"default\":null}]}");
    /** @deprecated */
    @Deprecated
    public int id;
    /** @deprecated */
    @Deprecated
    public String sourceSystem;
    /** @deprecated */
    @Deprecated
    public PaymentType paymentType;
    /** @deprecated */
    @Deprecated
    public String depositAmount;
    /** @deprecated */
    @Deprecated
    public String currency;
    /** @deprecated */
    @Deprecated
    public Long creationDate;
    /** @deprecated */
    @Deprecated
    public String takenBy;
    /** @deprecated */
    @Deprecated
    public String takenAt;
    /** @deprecated */
    @Deprecated
    public boolean voidFlag;
    /** @deprecated */
    @Deprecated
    public String voidedBy;
    /** @deprecated */
    @Deprecated
    public String voidNotes;
    private static final DatumWriter WRITER$;
    private static final DatumReader READER$;

    public static Schema getClassSchema() {
        return SCHEMA$;
    }

    public DepositPayment() {
    }

    public DepositPayment(Integer id, String sourceSystem, PaymentType paymentType, String depositAmount, String currency, Long creationDate, String takenBy, String takenAt, Boolean voidFlag, String voidedBy, String voidNotes) {
        this.id = id;
        this.sourceSystem = sourceSystem;
        this.paymentType = paymentType;
        this.depositAmount = depositAmount;
        this.currency = currency;
        this.creationDate = creationDate;
        this.takenBy = takenBy;
        this.takenAt = takenAt;
        this.voidFlag = voidFlag;
        this.voidedBy = voidedBy;
        this.voidNotes = voidNotes;
    }

    public Schema getSchema() {
        return SCHEMA$;
    }

    public Object get(int field$) {
        switch(field$) {
            case 0:
                return this.id;
            case 1:
                return this.sourceSystem;
            case 2:
                return this.paymentType;
            case 3:
                return this.depositAmount;
            case 4:
                return this.currency;
            case 5:
                return this.creationDate;
            case 6:
                return this.takenBy;
            case 7:
                return this.takenAt;
            case 8:
                return this.voidFlag;
            case 9:
                return this.voidedBy;
            case 10:
                return this.voidNotes;
            default:
                throw new AvroRuntimeException("Bad index");
        }
    }

    public void put(int field$, Object value$) {
        switch(field$) {
            case 0:
                this.id = (Integer)value$;
                break;
            case 1:
                this.sourceSystem = (String)value$;
                break;
            case 2:
                this.paymentType = (PaymentType)value$;
                break;
            case 3:
                this.depositAmount = (String)value$;
                break;
            case 4:
                this.currency = (String)value$;
                break;
            case 5:
                this.creationDate = (Long)value$;
                break;
            case 6:
                this.takenBy = (String)value$;
                break;
            case 7:
                this.takenAt = (String)value$;
                break;
            case 8:
                this.voidFlag = (Boolean)value$;
                break;
            case 9:
                this.voidedBy = (String)value$;
                break;
            case 10:
                this.voidNotes = (String)value$;
                break;
            default:
                throw new AvroRuntimeException("Bad index");
        }

    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer value) {
        this.id = value;
    }

    public String getSourceSystem() {
        return this.sourceSystem;
    }

    public void setSourceSystem(String value) {
        this.sourceSystem = value;
    }

    public PaymentType getPaymentType() {
        return this.paymentType;
    }

    public void setPaymentType(PaymentType value) {
        this.paymentType = value;
    }

    public String getDepositAmount() {
        return this.depositAmount;
    }

    public void setDepositAmount(String value) {
        this.depositAmount = value;
    }

    public String getCurrency() {
        return this.currency;
    }

    public void setCurrency(String value) {
        this.currency = value;
    }

    public Long getCreationDate() {
        return this.creationDate;
    }

    public void setCreationDate(Long value) {
        this.creationDate = value;
    }

    public String getTakenBy() {
        return this.takenBy;
    }

    public void setTakenBy(String value) {
        this.takenBy = value;
    }

    public String getTakenAt() {
        return this.takenAt;
    }

    public void setTakenAt(String value) {
        this.takenAt = value;
    }

    public Boolean getVoidFlag() {
        return this.voidFlag;
    }

    public void setVoidFlag(Boolean value) {
        this.voidFlag = value;
    }

    public String getVoidedBy() {
        return this.voidedBy;
    }

    public void setVoidedBy(String value) {
        this.voidedBy = value;
    }

    public String getVoidNotes() {
        return this.voidNotes;
    }

    public void setVoidNotes(String value) {
        this.voidNotes = value;
    }

    public static DepositPayment.Builder newBuilder() {
        return new DepositPayment.Builder();
    }

    public static DepositPayment.Builder newBuilder(DepositPayment.Builder other) {
        return new DepositPayment.Builder(other);
    }

    public static DepositPayment.Builder newBuilder(DepositPayment other) {
        return new DepositPayment.Builder(other);
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

    public static class Builder extends SpecificRecordBuilderBase<DepositPayment> implements RecordBuilder<DepositPayment> {
        private int id;
        private String sourceSystem;
        private PaymentType paymentType;
        private String depositAmount;
        private String currency;
        private Long creationDate;
        private String takenBy;
        private String takenAt;
        private boolean voidFlag;
        private String voidedBy;
        private String voidNotes;

        private Builder() {
            super(DepositPayment.SCHEMA$);
        }

        private Builder(DepositPayment.Builder other) {
            super(other);
            if (isValidValue(this.fields()[0], other.id)) {
                this.id = (Integer)this.data().deepCopy(this.fields()[0].schema(), other.id);
                this.fieldSetFlags()[0] = true;
            }

            if (isValidValue(this.fields()[1], other.sourceSystem)) {
                this.sourceSystem = (String)this.data().deepCopy(this.fields()[1].schema(), other.sourceSystem);
                this.fieldSetFlags()[1] = true;
            }

            if (isValidValue(this.fields()[2], other.paymentType)) {
                this.paymentType = (PaymentType)this.data().deepCopy(this.fields()[2].schema(), other.paymentType);
                this.fieldSetFlags()[2] = true;
            }

            if (isValidValue(this.fields()[3], other.depositAmount)) {
                this.depositAmount = (String)this.data().deepCopy(this.fields()[3].schema(), other.depositAmount);
                this.fieldSetFlags()[3] = true;
            }

            if (isValidValue(this.fields()[4], other.currency)) {
                this.currency = (String)this.data().deepCopy(this.fields()[4].schema(), other.currency);
                this.fieldSetFlags()[4] = true;
            }

            if (isValidValue(this.fields()[5], other.creationDate)) {
                this.creationDate = (Long)this.data().deepCopy(this.fields()[5].schema(), other.creationDate);
                this.fieldSetFlags()[5] = true;
            }

            if (isValidValue(this.fields()[6], other.takenBy)) {
                this.takenBy = (String)this.data().deepCopy(this.fields()[6].schema(), other.takenBy);
                this.fieldSetFlags()[6] = true;
            }

            if (isValidValue(this.fields()[7], other.takenAt)) {
                this.takenAt = (String)this.data().deepCopy(this.fields()[7].schema(), other.takenAt);
                this.fieldSetFlags()[7] = true;
            }

            if (isValidValue(this.fields()[8], other.voidFlag)) {
                this.voidFlag = (Boolean)this.data().deepCopy(this.fields()[8].schema(), other.voidFlag);
                this.fieldSetFlags()[8] = true;
            }

            if (isValidValue(this.fields()[9], other.voidedBy)) {
                this.voidedBy = (String)this.data().deepCopy(this.fields()[9].schema(), other.voidedBy);
                this.fieldSetFlags()[9] = true;
            }

            if (isValidValue(this.fields()[10], other.voidNotes)) {
                this.voidNotes = (String)this.data().deepCopy(this.fields()[10].schema(), other.voidNotes);
                this.fieldSetFlags()[10] = true;
            }

        }

        private Builder(DepositPayment other) {
            super(DepositPayment.SCHEMA$);
            if (isValidValue(this.fields()[0], other.id)) {
                this.id = (Integer)this.data().deepCopy(this.fields()[0].schema(), other.id);
                this.fieldSetFlags()[0] = true;
            }

            if (isValidValue(this.fields()[1], other.sourceSystem)) {
                this.sourceSystem = (String)this.data().deepCopy(this.fields()[1].schema(), other.sourceSystem);
                this.fieldSetFlags()[1] = true;
            }

            if (isValidValue(this.fields()[2], other.paymentType)) {
                this.paymentType = (PaymentType)this.data().deepCopy(this.fields()[2].schema(), other.paymentType);
                this.fieldSetFlags()[2] = true;
            }

            if (isValidValue(this.fields()[3], other.depositAmount)) {
                this.depositAmount = (String)this.data().deepCopy(this.fields()[3].schema(), other.depositAmount);
                this.fieldSetFlags()[3] = true;
            }

            if (isValidValue(this.fields()[4], other.currency)) {
                this.currency = (String)this.data().deepCopy(this.fields()[4].schema(), other.currency);
                this.fieldSetFlags()[4] = true;
            }

            if (isValidValue(this.fields()[5], other.creationDate)) {
                this.creationDate = (Long)this.data().deepCopy(this.fields()[5].schema(), other.creationDate);
                this.fieldSetFlags()[5] = true;
            }

            if (isValidValue(this.fields()[6], other.takenBy)) {
                this.takenBy = (String)this.data().deepCopy(this.fields()[6].schema(), other.takenBy);
                this.fieldSetFlags()[6] = true;
            }

            if (isValidValue(this.fields()[7], other.takenAt)) {
                this.takenAt = (String)this.data().deepCopy(this.fields()[7].schema(), other.takenAt);
                this.fieldSetFlags()[7] = true;
            }

            if (isValidValue(this.fields()[8], other.voidFlag)) {
                this.voidFlag = (Boolean)this.data().deepCopy(this.fields()[8].schema(), other.voidFlag);
                this.fieldSetFlags()[8] = true;
            }

            if (isValidValue(this.fields()[9], other.voidedBy)) {
                this.voidedBy = (String)this.data().deepCopy(this.fields()[9].schema(), other.voidedBy);
                this.fieldSetFlags()[9] = true;
            }

            if (isValidValue(this.fields()[10], other.voidNotes)) {
                this.voidNotes = (String)this.data().deepCopy(this.fields()[10].schema(), other.voidNotes);
                this.fieldSetFlags()[10] = true;
            }

        }

        public Integer getId() {
            return this.id;
        }

        public DepositPayment.Builder setId(int value) {
            this.validate(this.fields()[0], value);
            this.id = value;
            this.fieldSetFlags()[0] = true;
            return this;
        }

        public boolean hasId() {
            return this.fieldSetFlags()[0];
        }

        public DepositPayment.Builder clearId() {
            this.fieldSetFlags()[0] = false;
            return this;
        }

        public String getSourceSystem() {
            return this.sourceSystem;
        }

        public DepositPayment.Builder setSourceSystem(String value) {
            this.validate(this.fields()[1], value);
            this.sourceSystem = value;
            this.fieldSetFlags()[1] = true;
            return this;
        }

        public boolean hasSourceSystem() {
            return this.fieldSetFlags()[1];
        }

        public DepositPayment.Builder clearSourceSystem() {
            this.sourceSystem = null;
            this.fieldSetFlags()[1] = false;
            return this;
        }

        public PaymentType getPaymentType() {
            return this.paymentType;
        }

        public DepositPayment.Builder setPaymentType(PaymentType value) {
            this.validate(this.fields()[2], value);
            this.paymentType = value;
            this.fieldSetFlags()[2] = true;
            return this;
        }

        public boolean hasPaymentType() {
            return this.fieldSetFlags()[2];
        }

        public DepositPayment.Builder clearPaymentType() {
            this.paymentType = null;
            this.fieldSetFlags()[2] = false;
            return this;
        }

        public String getDepositAmount() {
            return this.depositAmount;
        }

        public DepositPayment.Builder setDepositAmount(String value) {
            this.validate(this.fields()[3], value);
            this.depositAmount = value;
            this.fieldSetFlags()[3] = true;
            return this;
        }

        public boolean hasDepositAmount() {
            return this.fieldSetFlags()[3];
        }

        public DepositPayment.Builder clearDepositAmount() {
            this.depositAmount = null;
            this.fieldSetFlags()[3] = false;
            return this;
        }

        public String getCurrency() {
            return this.currency;
        }

        public DepositPayment.Builder setCurrency(String value) {
            this.validate(this.fields()[4], value);
            this.currency = value;
            this.fieldSetFlags()[4] = true;
            return this;
        }

        public boolean hasCurrency() {
            return this.fieldSetFlags()[4];
        }

        public DepositPayment.Builder clearCurrency() {
            this.currency = null;
            this.fieldSetFlags()[4] = false;
            return this;
        }

        public Long getCreationDate() {
            return this.creationDate;
        }

        public DepositPayment.Builder setCreationDate(Long value) {
            this.validate(this.fields()[5], value);
            this.creationDate = value;
            this.fieldSetFlags()[5] = true;
            return this;
        }

        public boolean hasCreationDate() {
            return this.fieldSetFlags()[5];
        }

        public DepositPayment.Builder clearCreationDate() {
            this.creationDate = null;
            this.fieldSetFlags()[5] = false;
            return this;
        }

        public String getTakenBy() {
            return this.takenBy;
        }

        public DepositPayment.Builder setTakenBy(String value) {
            this.validate(this.fields()[6], value);
            this.takenBy = value;
            this.fieldSetFlags()[6] = true;
            return this;
        }

        public boolean hasTakenBy() {
            return this.fieldSetFlags()[6];
        }

        public DepositPayment.Builder clearTakenBy() {
            this.takenBy = null;
            this.fieldSetFlags()[6] = false;
            return this;
        }

        public String getTakenAt() {
            return this.takenAt;
        }

        public DepositPayment.Builder setTakenAt(String value) {
            this.validate(this.fields()[7], value);
            this.takenAt = value;
            this.fieldSetFlags()[7] = true;
            return this;
        }

        public boolean hasTakenAt() {
            return this.fieldSetFlags()[7];
        }

        public DepositPayment.Builder clearTakenAt() {
            this.takenAt = null;
            this.fieldSetFlags()[7] = false;
            return this;
        }

        public Boolean getVoidFlag() {
            return this.voidFlag;
        }

        public DepositPayment.Builder setVoidFlag(boolean value) {
            this.validate(this.fields()[8], value);
            this.voidFlag = value;
            this.fieldSetFlags()[8] = true;
            return this;
        }

        public boolean hasVoidFlag() {
            return this.fieldSetFlags()[8];
        }

        public DepositPayment.Builder clearVoidFlag() {
            this.fieldSetFlags()[8] = false;
            return this;
        }

        public String getVoidedBy() {
            return this.voidedBy;
        }

        public DepositPayment.Builder setVoidedBy(String value) {
            this.validate(this.fields()[9], value);
            this.voidedBy = value;
            this.fieldSetFlags()[9] = true;
            return this;
        }

        public boolean hasVoidedBy() {
            return this.fieldSetFlags()[9];
        }

        public DepositPayment.Builder clearVoidedBy() {
            this.voidedBy = null;
            this.fieldSetFlags()[9] = false;
            return this;
        }

        public String getVoidNotes() {
            return this.voidNotes;
        }

        public DepositPayment.Builder setVoidNotes(String value) {
            this.validate(this.fields()[10], value);
            this.voidNotes = value;
            this.fieldSetFlags()[10] = true;
            return this;
        }

        public boolean hasVoidNotes() {
            return this.fieldSetFlags()[10];
        }

        public DepositPayment.Builder clearVoidNotes() {
            this.voidNotes = null;
            this.fieldSetFlags()[10] = false;
            return this;
        }

        public DepositPayment build() {
            try {
                DepositPayment record = new DepositPayment();
                record.id = this.fieldSetFlags()[0] ? this.id : (Integer)this.defaultValue(this.fields()[0]);
                record.sourceSystem = this.fieldSetFlags()[1] ? this.sourceSystem : (String)this.defaultValue(this.fields()[1]);
                record.paymentType = this.fieldSetFlags()[2] ? this.paymentType : (PaymentType)this.defaultValue(this.fields()[2]);
                record.depositAmount = this.fieldSetFlags()[3] ? this.depositAmount : (String)this.defaultValue(this.fields()[3]);
                record.currency = this.fieldSetFlags()[4] ? this.currency : (String)this.defaultValue(this.fields()[4]);
                record.creationDate = this.fieldSetFlags()[5] ? this.creationDate : (Long)this.defaultValue(this.fields()[5]);
                record.takenBy = this.fieldSetFlags()[6] ? this.takenBy : (String)this.defaultValue(this.fields()[6]);
                record.takenAt = this.fieldSetFlags()[7] ? this.takenAt : (String)this.defaultValue(this.fields()[7]);
                record.voidFlag = this.fieldSetFlags()[8] ? this.voidFlag : (Boolean)this.defaultValue(this.fields()[8]);
                record.voidedBy = this.fieldSetFlags()[9] ? this.voidedBy : (String)this.defaultValue(this.fields()[9]);
                record.voidNotes = this.fieldSetFlags()[10] ? this.voidNotes : (String)this.defaultValue(this.fields()[10]);
                return record;
            } catch (Exception var2) {
                throw new AvroRuntimeException(var2);
            }
        }
    }
}
