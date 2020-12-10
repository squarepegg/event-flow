package org.randomidea.flow.utils.schemas;

import org.apache.avro.Schema;
import org.apache.avro.Schema.Parser;
import org.apache.avro.specific.AvroGenerated;

@AvroGenerated
public enum PaymentType {
    AC,
    BD,
    CC,
    CH,
    CR,
    CT,
    DB,
    MO,
    RC,
    TC,
    WT;

    public static final Schema SCHEMA$ = (new Parser()).parse("{\"type\":\"enum\",\"name\":\"PaymentType\",\"namespace\":\"org.randomidea.flow.utils.schemas\",\"doc\":\"Type of the deposit payment\",\"symbols\":[\"AC\",\"BD\",\"CC\",\"CH\",\"CR\",\"CT\",\"DB\",\"MO\",\"RC\",\"TC\",\"WT\"]}");

    private PaymentType() {
    }

    public static Schema getClassSchema() {
        return SCHEMA$;
    }
}
