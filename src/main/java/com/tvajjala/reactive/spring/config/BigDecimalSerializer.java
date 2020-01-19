package com.tvajjala.reactive.spring.config;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;
import java.math.BigDecimal;

/**
 * @author ThirupathiReddy Vajjala
 */
public class BigDecimalSerializer extends JsonSerializer<BigDecimal> {

    public static final BigDecimalSerializer INSTANCE=new BigDecimalSerializer();


    @Override
    public void serialize(BigDecimal bigDecimal, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {

        jsonGenerator.writeString(bigDecimal.setScale(2, BigDecimal.ROUND_HALF_EVEN).toString());
    }

    @Override
    public Class<BigDecimal> handledType() {
        return BigDecimal.class;
    }
}
