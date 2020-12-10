package org.randomidea.flow.utils.schemas;

import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.util.List;
import org.apache.avro.AvroRuntimeException;
import org.apache.avro.Schema;
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
public class Deposit extends SpecificRecordBase implements SpecificRecord {
    private static final long serialVersionUID = 3007807808434215216L;
    public static final Schema SCHEMA$ = (new Schema.Parser()).parse("{\"type\":\"record\",\"name\":\"Deposit\",\"namespace\":\"org.randomidea.flow.utils.schemas\",\"doc\":\"A deposit for an RB onsite auction\",\"fields\":[{\"name\":\"id\",\"type\":{\"type\":\"string\",\"avro.java.string\":\"String\"},\"doc\":\"The unique identifier of a bidder deposit\"},{\"name\":\"sourceSystem\",\"type\":[\"null\",{\"type\":\"string\",\"avro.java.string\":\"String\"}],\"doc\":\"Source system of the Deposit\",\"default\":null},{\"name\":\"siteId\",\"type\":[\"null\",\"int\"],\"doc\":\"The unique identifier of the site where the asset is located.\",\"default\":null},{\"name\":\"auctionEventGUID\",\"type\":{\"type\":\"string\",\"avro.java.string\":\"String\"},\"doc\":\"The unique identifier of the auction event\"},{\"name\":\"saleNumber\",\"type\":[\"null\",\"int\"],\"doc\":\"Public facing identifier for the auction. RB - auction sale number\",\"default\":null},{\"name\":\"bidderNumber\",\"type\":\"int\",\"doc\":\"Bidder number\"},{\"name\":\"sourceSystemCustomerId\",\"type\":\"int\",\"doc\":\"Id of the customer in the source system\"},{\"name\":\"creationDate\",\"type\":[\"null\",\"long\"],\"doc\":\"Date the deposit was created using Unix epoch in seconds\",\"default\":null},{\"name\":\"form8300Completed\",\"type\":[\"null\",\"boolean\"],\"doc\":\"Flag to indicate a form 8300 has been completed by the bidder\",\"default\":null},{\"name\":\"notes\",\"type\":[\"null\",{\"type\":\"array\",\"items\":{\"type\":\"record\",\"name\":\"DepositNote\",\"doc\":\"Note related to the deposit\",\"fields\":[{\"name\":\"creationDate\",\"type\":\"long\",\"doc\":\"Date the deposit note was created using Unix epoch in seconds\"},{\"name\":\"note\",\"type\":{\"type\":\"string\",\"avro.java.string\":\"String\"},\"doc\":\"Note associated to the deposit\"}]}}],\"doc\":\"Notes related to the deposit\",\"default\":null},{\"name\":\"depositPayments\",\"type\":[\"null\",{\"type\":\"array\",\"items\":{\"type\":\"record\",\"name\":\"DepositPayment\",\"doc\":\"Payment made towards the deposit\",\"fields\":[{\"name\":\"id\",\"type\":\"int\",\"doc\":\"The unique identifier of a bidder deposit\"},{\"name\":\"sourceSystem\",\"type\":[\"null\",{\"type\":\"string\",\"avro.java.string\":\"String\"}],\"doc\":\"Source system of the registration\",\"default\":null},{\"name\":\"paymentType\",\"type\":{\"type\":\"enum\",\"name\":\"PaymentType\",\"doc\":\"Type of the deposit payment\",\"symbols\":[\"AC\",\"BD\",\"CC\",\"CH\",\"CR\",\"CT\",\"DB\",\"MO\",\"RC\",\"TC\",\"WT\"]},\"doc\":\"Type of the deposit payment\"},{\"name\":\"depositAmount\",\"type\":{\"type\":\"string\",\"avro.java.string\":\"String\"},\"doc\":\"Amount of the deposit payment\"},{\"name\":\"currency\",\"type\":{\"type\":\"string\",\"avro.java.string\":\"String\"},\"doc\":\"3 digit ISO currency code of the deposit payment\"},{\"name\":\"creationDate\",\"type\":[\"null\",\"long\"],\"doc\":\"Date the payment was taken using Unix epoch in seconds\",\"default\":null},{\"name\":\"takenBy\",\"type\":[\"null\",{\"type\":\"string\",\"avro.java.string\":\"String\"}],\"doc\":\"Reference of who took the deposit payment\",\"default\":null},{\"name\":\"takenAt\",\"type\":[\"null\",{\"type\":\"string\",\"avro.java.string\":\"String\"}],\"doc\":\"Location where deposit payment was taken\",\"default\":null},{\"name\":\"voidFlag\",\"type\":\"boolean\",\"doc\":\"Indicator if the deposit payment as been voided\",\"default\":false},{\"name\":\"voidedBy\",\"type\":[\"null\",{\"type\":\"string\",\"avro.java.string\":\"String\"}],\"doc\":\"Reference of who voided the deposit payment\",\"default\":null},{\"name\":\"voidNotes\",\"type\":[\"null\",{\"type\":\"string\",\"avro.java.string\":\"String\"}],\"doc\":\"Details of why the deposit payment was voided\",\"default\":null}]}}],\"doc\":\"Payments made towards the deposit\",\"default\":null}]}");
    /** @deprecated */
    @Deprecated
    public String id;
    /** @deprecated */
    @Deprecated
    public String sourceSystem;
    /** @deprecated */
    @Deprecated
    public Integer siteId;
    /** @deprecated */
    @Deprecated
    public String auctionEventGUID;
    /** @deprecated */
    @Deprecated
    public Integer saleNumber;
    /** @deprecated */
    @Deprecated
    public int bidderNumber;
    /** @deprecated */
    @Deprecated
    public int sourceSystemCustomerId;
    /** @deprecated */
    @Deprecated
    public Long creationDate;
    /** @deprecated */
    @Deprecated
    public Boolean form8300Completed;
    /** @deprecated */
    @Deprecated
    public List<DepositNote> notes;
    /** @deprecated */
    @Deprecated
    public List<DepositPayment> depositPayments;
    private static final DatumWriter WRITER$;
    private static final DatumReader READER$;

    public static Schema getClassSchema() {
        return SCHEMA$;
    }

    public Deposit() {
    }

    public Deposit(String id, String sourceSystem, Integer siteId, String auctionEventGUID, Integer saleNumber, Integer bidderNumber, Integer sourceSystemCustomerId, Long creationDate, Boolean form8300Completed, List<DepositNote> notes, List<DepositPayment> depositPayments) {
        this.id = id;
        this.sourceSystem = sourceSystem;
        this.siteId = siteId;
        this.auctionEventGUID = auctionEventGUID;
        this.saleNumber = saleNumber;
        this.bidderNumber = bidderNumber;
        this.sourceSystemCustomerId = sourceSystemCustomerId;
        this.creationDate = creationDate;
        this.form8300Completed = form8300Completed;
        this.notes = notes;
        this.depositPayments = depositPayments;
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
                return this.siteId;
            case 3:
                return this.auctionEventGUID;
            case 4:
                return this.saleNumber;
            case 5:
                return this.bidderNumber;
            case 6:
                return this.sourceSystemCustomerId;
            case 7:
                return this.creationDate;
            case 8:
                return this.form8300Completed;
            case 9:
                return this.notes;
            case 10:
                return this.depositPayments;
            default:
                throw new AvroRuntimeException("Bad index");
        }
    }

    public void put(int field$, Object value$) {
        switch(field$) {
            case 0:
                this.id = (String)value$;
                break;
            case 1:
                this.sourceSystem = (String)value$;
                break;
            case 2:
                this.siteId = (Integer)value$;
                break;
            case 3:
                this.auctionEventGUID = (String)value$;
                break;
            case 4:
                this.saleNumber = (Integer)value$;
                break;
            case 5:
                this.bidderNumber = (Integer)value$;
                break;
            case 6:
                this.sourceSystemCustomerId = (Integer)value$;
                break;
            case 7:
                this.creationDate = (Long)value$;
                break;
            case 8:
                this.form8300Completed = (Boolean)value$;
                break;
            case 9:
                this.notes = (List)value$;
                break;
            case 10:
                this.depositPayments = (List)value$;
                break;
            default:
                throw new AvroRuntimeException("Bad index");
        }

    }

    public String getId() {
        return this.id;
    }

    public void setId(String value) {
        this.id = value;
    }

    public String getSourceSystem() {
        return this.sourceSystem;
    }

    public void setSourceSystem(String value) {
        this.sourceSystem = value;
    }

    public Integer getSiteId() {
        return this.siteId;
    }

    public void setSiteId(Integer value) {
        this.siteId = value;
    }

    public String getAuctionEventGUID() {
        return this.auctionEventGUID;
    }

    public void setAuctionEventGUID(String value) {
        this.auctionEventGUID = value;
    }

    public Integer getSaleNumber() {
        return this.saleNumber;
    }

    public void setSaleNumber(Integer value) {
        this.saleNumber = value;
    }

    public Integer getBidderNumber() {
        return this.bidderNumber;
    }

    public void setBidderNumber(Integer value) {
        this.bidderNumber = value;
    }

    public Integer getSourceSystemCustomerId() {
        return this.sourceSystemCustomerId;
    }

    public void setSourceSystemCustomerId(Integer value) {
        this.sourceSystemCustomerId = value;
    }

    public Long getCreationDate() {
        return this.creationDate;
    }

    public void setCreationDate(Long value) {
        this.creationDate = value;
    }

    public Boolean getForm8300Completed() {
        return this.form8300Completed;
    }

    public void setForm8300Completed(Boolean value) {
        this.form8300Completed = value;
    }

    public List<DepositNote> getNotes() {
        return this.notes;
    }

    public void setNotes(List<DepositNote> value) {
        this.notes = value;
    }

    public List<DepositPayment> getDepositPayments() {
        return this.depositPayments;
    }

    public void setDepositPayments(List<DepositPayment> value) {
        this.depositPayments = value;
    }

    public static Deposit.Builder newBuilder() {
        return new Deposit.Builder();
    }

    public static Deposit.Builder newBuilder(Deposit.Builder other) {
        return new Deposit.Builder(other);
    }

    public static Deposit.Builder newBuilder(Deposit other) {
        return new Deposit.Builder(other);
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

    public static class Builder extends SpecificRecordBuilderBase<Deposit> implements RecordBuilder<Deposit> {
        private String id;
        private String sourceSystem;
        private Integer siteId;
        private String auctionEventGUID;
        private Integer saleNumber;
        private int bidderNumber;
        private int sourceSystemCustomerId;
        private Long creationDate;
        private Boolean form8300Completed;
        private List<DepositNote> notes;
        private List<DepositPayment> depositPayments;

        private Builder() {
            super(Deposit.SCHEMA$);
        }

        private Builder(Deposit.Builder other) {
            super(other);
            if (isValidValue(this.fields()[0], other.id)) {
                this.id = (String)this.data().deepCopy(this.fields()[0].schema(), other.id);
                this.fieldSetFlags()[0] = true;
            }

            if (isValidValue(this.fields()[1], other.sourceSystem)) {
                this.sourceSystem = (String)this.data().deepCopy(this.fields()[1].schema(), other.sourceSystem);
                this.fieldSetFlags()[1] = true;
            }

            if (isValidValue(this.fields()[2], other.siteId)) {
                this.siteId = (Integer)this.data().deepCopy(this.fields()[2].schema(), other.siteId);
                this.fieldSetFlags()[2] = true;
            }

            if (isValidValue(this.fields()[3], other.auctionEventGUID)) {
                this.auctionEventGUID = (String)this.data().deepCopy(this.fields()[3].schema(), other.auctionEventGUID);
                this.fieldSetFlags()[3] = true;
            }

            if (isValidValue(this.fields()[4], other.saleNumber)) {
                this.saleNumber = (Integer)this.data().deepCopy(this.fields()[4].schema(), other.saleNumber);
                this.fieldSetFlags()[4] = true;
            }

            if (isValidValue(this.fields()[5], other.bidderNumber)) {
                this.bidderNumber = (Integer)this.data().deepCopy(this.fields()[5].schema(), other.bidderNumber);
                this.fieldSetFlags()[5] = true;
            }

            if (isValidValue(this.fields()[6], other.sourceSystemCustomerId)) {
                this.sourceSystemCustomerId = (Integer)this.data().deepCopy(this.fields()[6].schema(), other.sourceSystemCustomerId);
                this.fieldSetFlags()[6] = true;
            }

            if (isValidValue(this.fields()[7], other.creationDate)) {
                this.creationDate = (Long)this.data().deepCopy(this.fields()[7].schema(), other.creationDate);
                this.fieldSetFlags()[7] = true;
            }

            if (isValidValue(this.fields()[8], other.form8300Completed)) {
                this.form8300Completed = (Boolean)this.data().deepCopy(this.fields()[8].schema(), other.form8300Completed);
                this.fieldSetFlags()[8] = true;
            }

            if (isValidValue(this.fields()[9], other.notes)) {
                this.notes = (List)this.data().deepCopy(this.fields()[9].schema(), other.notes);
                this.fieldSetFlags()[9] = true;
            }

            if (isValidValue(this.fields()[10], other.depositPayments)) {
                this.depositPayments = (List)this.data().deepCopy(this.fields()[10].schema(), other.depositPayments);
                this.fieldSetFlags()[10] = true;
            }

        }

        private Builder(Deposit other) {
            super(Deposit.SCHEMA$);
            if (isValidValue(this.fields()[0], other.id)) {
                this.id = (String)this.data().deepCopy(this.fields()[0].schema(), other.id);
                this.fieldSetFlags()[0] = true;
            }

            if (isValidValue(this.fields()[1], other.sourceSystem)) {
                this.sourceSystem = (String)this.data().deepCopy(this.fields()[1].schema(), other.sourceSystem);
                this.fieldSetFlags()[1] = true;
            }

            if (isValidValue(this.fields()[2], other.siteId)) {
                this.siteId = (Integer)this.data().deepCopy(this.fields()[2].schema(), other.siteId);
                this.fieldSetFlags()[2] = true;
            }

            if (isValidValue(this.fields()[3], other.auctionEventGUID)) {
                this.auctionEventGUID = (String)this.data().deepCopy(this.fields()[3].schema(), other.auctionEventGUID);
                this.fieldSetFlags()[3] = true;
            }

            if (isValidValue(this.fields()[4], other.saleNumber)) {
                this.saleNumber = (Integer)this.data().deepCopy(this.fields()[4].schema(), other.saleNumber);
                this.fieldSetFlags()[4] = true;
            }

            if (isValidValue(this.fields()[5], other.bidderNumber)) {
                this.bidderNumber = (Integer)this.data().deepCopy(this.fields()[5].schema(), other.bidderNumber);
                this.fieldSetFlags()[5] = true;
            }

            if (isValidValue(this.fields()[6], other.sourceSystemCustomerId)) {
                this.sourceSystemCustomerId = (Integer)this.data().deepCopy(this.fields()[6].schema(), other.sourceSystemCustomerId);
                this.fieldSetFlags()[6] = true;
            }

            if (isValidValue(this.fields()[7], other.creationDate)) {
                this.creationDate = (Long)this.data().deepCopy(this.fields()[7].schema(), other.creationDate);
                this.fieldSetFlags()[7] = true;
            }

            if (isValidValue(this.fields()[8], other.form8300Completed)) {
                this.form8300Completed = (Boolean)this.data().deepCopy(this.fields()[8].schema(), other.form8300Completed);
                this.fieldSetFlags()[8] = true;
            }

            if (isValidValue(this.fields()[9], other.notes)) {
                this.notes = (List)this.data().deepCopy(this.fields()[9].schema(), other.notes);
                this.fieldSetFlags()[9] = true;
            }

            if (isValidValue(this.fields()[10], other.depositPayments)) {
                this.depositPayments = (List)this.data().deepCopy(this.fields()[10].schema(), other.depositPayments);
                this.fieldSetFlags()[10] = true;
            }

        }

        public String getId() {
            return this.id;
        }

        public Deposit.Builder setId(String value) {
            this.validate(this.fields()[0], value);
            this.id = value;
            this.fieldSetFlags()[0] = true;
            return this;
        }

        public boolean hasId() {
            return this.fieldSetFlags()[0];
        }

        public Deposit.Builder clearId() {
            this.id = null;
            this.fieldSetFlags()[0] = false;
            return this;
        }

        public String getSourceSystem() {
            return this.sourceSystem;
        }

        public Deposit.Builder setSourceSystem(String value) {
            this.validate(this.fields()[1], value);
            this.sourceSystem = value;
            this.fieldSetFlags()[1] = true;
            return this;
        }

        public boolean hasSourceSystem() {
            return this.fieldSetFlags()[1];
        }

        public Deposit.Builder clearSourceSystem() {
            this.sourceSystem = null;
            this.fieldSetFlags()[1] = false;
            return this;
        }

        public Integer getSiteId() {
            return this.siteId;
        }

        public Deposit.Builder setSiteId(Integer value) {
            this.validate(this.fields()[2], value);
            this.siteId = value;
            this.fieldSetFlags()[2] = true;
            return this;
        }

        public boolean hasSiteId() {
            return this.fieldSetFlags()[2];
        }

        public Deposit.Builder clearSiteId() {
            this.siteId = null;
            this.fieldSetFlags()[2] = false;
            return this;
        }

        public String getAuctionEventGUID() {
            return this.auctionEventGUID;
        }

        public Deposit.Builder setAuctionEventGUID(String value) {
            this.validate(this.fields()[3], value);
            this.auctionEventGUID = value;
            this.fieldSetFlags()[3] = true;
            return this;
        }

        public boolean hasAuctionEventGUID() {
            return this.fieldSetFlags()[3];
        }

        public Deposit.Builder clearAuctionEventGUID() {
            this.auctionEventGUID = null;
            this.fieldSetFlags()[3] = false;
            return this;
        }

        public Integer getSaleNumber() {
            return this.saleNumber;
        }

        public Deposit.Builder setSaleNumber(Integer value) {
            this.validate(this.fields()[4], value);
            this.saleNumber = value;
            this.fieldSetFlags()[4] = true;
            return this;
        }

        public boolean hasSaleNumber() {
            return this.fieldSetFlags()[4];
        }

        public Deposit.Builder clearSaleNumber() {
            this.saleNumber = null;
            this.fieldSetFlags()[4] = false;
            return this;
        }

        public Integer getBidderNumber() {
            return this.bidderNumber;
        }

        public Deposit.Builder setBidderNumber(int value) {
            this.validate(this.fields()[5], value);
            this.bidderNumber = value;
            this.fieldSetFlags()[5] = true;
            return this;
        }

        public boolean hasBidderNumber() {
            return this.fieldSetFlags()[5];
        }

        public Deposit.Builder clearBidderNumber() {
            this.fieldSetFlags()[5] = false;
            return this;
        }

        public Integer getSourceSystemCustomerId() {
            return this.sourceSystemCustomerId;
        }

        public Deposit.Builder setSourceSystemCustomerId(int value) {
            this.validate(this.fields()[6], value);
            this.sourceSystemCustomerId = value;
            this.fieldSetFlags()[6] = true;
            return this;
        }

        public boolean hasSourceSystemCustomerId() {
            return this.fieldSetFlags()[6];
        }

        public Deposit.Builder clearSourceSystemCustomerId() {
            this.fieldSetFlags()[6] = false;
            return this;
        }

        public Long getCreationDate() {
            return this.creationDate;
        }

        public Deposit.Builder setCreationDate(Long value) {
            this.validate(this.fields()[7], value);
            this.creationDate = value;
            this.fieldSetFlags()[7] = true;
            return this;
        }

        public boolean hasCreationDate() {
            return this.fieldSetFlags()[7];
        }

        public Deposit.Builder clearCreationDate() {
            this.creationDate = null;
            this.fieldSetFlags()[7] = false;
            return this;
        }

        public Boolean getForm8300Completed() {
            return this.form8300Completed;
        }

        public Deposit.Builder setForm8300Completed(Boolean value) {
            this.validate(this.fields()[8], value);
            this.form8300Completed = value;
            this.fieldSetFlags()[8] = true;
            return this;
        }

        public boolean hasForm8300Completed() {
            return this.fieldSetFlags()[8];
        }

        public Deposit.Builder clearForm8300Completed() {
            this.form8300Completed = null;
            this.fieldSetFlags()[8] = false;
            return this;
        }

        public List<DepositNote> getNotes() {
            return this.notes;
        }

        public Deposit.Builder setNotes(List<DepositNote> value) {
            this.validate(this.fields()[9], value);
            this.notes = value;
            this.fieldSetFlags()[9] = true;
            return this;
        }

        public boolean hasNotes() {
            return this.fieldSetFlags()[9];
        }

        public Deposit.Builder clearNotes() {
            this.notes = null;
            this.fieldSetFlags()[9] = false;
            return this;
        }

        public List<DepositPayment> getDepositPayments() {
            return this.depositPayments;
        }

        public Deposit.Builder setDepositPayments(List<DepositPayment> value) {
            this.validate(this.fields()[10], value);
            this.depositPayments = value;
            this.fieldSetFlags()[10] = true;
            return this;
        }

        public boolean hasDepositPayments() {
            return this.fieldSetFlags()[10];
        }

        public Deposit.Builder clearDepositPayments() {
            this.depositPayments = null;
            this.fieldSetFlags()[10] = false;
            return this;
        }

        public Deposit build() {
            try {
                Deposit record = new Deposit();
                record.id = this.fieldSetFlags()[0] ? this.id : (String)this.defaultValue(this.fields()[0]);
                record.sourceSystem = this.fieldSetFlags()[1] ? this.sourceSystem : (String)this.defaultValue(this.fields()[1]);
                record.siteId = this.fieldSetFlags()[2] ? this.siteId : (Integer)this.defaultValue(this.fields()[2]);
                record.auctionEventGUID = this.fieldSetFlags()[3] ? this.auctionEventGUID : (String)this.defaultValue(this.fields()[3]);
                record.saleNumber = this.fieldSetFlags()[4] ? this.saleNumber : (Integer)this.defaultValue(this.fields()[4]);
                record.bidderNumber = this.fieldSetFlags()[5] ? this.bidderNumber : (Integer)this.defaultValue(this.fields()[5]);
                record.sourceSystemCustomerId = this.fieldSetFlags()[6] ? this.sourceSystemCustomerId : (Integer)this.defaultValue(this.fields()[6]);
                record.creationDate = this.fieldSetFlags()[7] ? this.creationDate : (Long)this.defaultValue(this.fields()[7]);
                record.form8300Completed = this.fieldSetFlags()[8] ? this.form8300Completed : (Boolean)this.defaultValue(this.fields()[8]);
                record.notes = this.fieldSetFlags()[9] ? this.notes : (List)this.defaultValue(this.fields()[9]);
                record.depositPayments = this.fieldSetFlags()[10] ? this.depositPayments : (List)this.defaultValue(this.fields()[10]);
                return record;
            } catch (Exception var2) {
                throw new AvroRuntimeException(var2);
            }
        }
    }
}
