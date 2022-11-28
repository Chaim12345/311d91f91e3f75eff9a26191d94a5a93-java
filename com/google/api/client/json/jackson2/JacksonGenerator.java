package com.google.api.client.json.jackson2;

import com.google.api.client.json.JsonGenerator;
import java.math.BigDecimal;
import java.math.BigInteger;
/* loaded from: classes2.dex */
final class JacksonGenerator extends JsonGenerator {
    private final JacksonFactory factory;
    private final com.fasterxml.jackson.core.JsonGenerator generator;

    /* JADX INFO: Access modifiers changed from: package-private */
    public JacksonGenerator(JacksonFactory jacksonFactory, com.fasterxml.jackson.core.JsonGenerator jsonGenerator) {
        this.factory = jacksonFactory;
        this.generator = jsonGenerator;
    }

    @Override // com.google.api.client.json.JsonGenerator, java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        this.generator.close();
    }

    @Override // com.google.api.client.json.JsonGenerator
    public void enablePrettyPrint() {
        this.generator.useDefaultPrettyPrinter();
    }

    @Override // com.google.api.client.json.JsonGenerator, java.io.Flushable
    public void flush() {
        this.generator.flush();
    }

    @Override // com.google.api.client.json.JsonGenerator
    public JacksonFactory getFactory() {
        return this.factory;
    }

    @Override // com.google.api.client.json.JsonGenerator
    public void writeBoolean(boolean z) {
        this.generator.writeBoolean(z);
    }

    @Override // com.google.api.client.json.JsonGenerator
    public void writeEndArray() {
        this.generator.writeEndArray();
    }

    @Override // com.google.api.client.json.JsonGenerator
    public void writeEndObject() {
        this.generator.writeEndObject();
    }

    @Override // com.google.api.client.json.JsonGenerator
    public void writeFieldName(String str) {
        this.generator.writeFieldName(str);
    }

    @Override // com.google.api.client.json.JsonGenerator
    public void writeNull() {
        this.generator.writeNull();
    }

    @Override // com.google.api.client.json.JsonGenerator
    public void writeNumber(double d2) {
        this.generator.writeNumber(d2);
    }

    @Override // com.google.api.client.json.JsonGenerator
    public void writeNumber(float f2) {
        this.generator.writeNumber(f2);
    }

    @Override // com.google.api.client.json.JsonGenerator
    public void writeNumber(int i2) {
        this.generator.writeNumber(i2);
    }

    @Override // com.google.api.client.json.JsonGenerator
    public void writeNumber(long j2) {
        this.generator.writeNumber(j2);
    }

    @Override // com.google.api.client.json.JsonGenerator
    public void writeNumber(String str) {
        this.generator.writeNumber(str);
    }

    @Override // com.google.api.client.json.JsonGenerator
    public void writeNumber(BigDecimal bigDecimal) {
        this.generator.writeNumber(bigDecimal);
    }

    @Override // com.google.api.client.json.JsonGenerator
    public void writeNumber(BigInteger bigInteger) {
        this.generator.writeNumber(bigInteger);
    }

    @Override // com.google.api.client.json.JsonGenerator
    public void writeStartArray() {
        this.generator.writeStartArray();
    }

    @Override // com.google.api.client.json.JsonGenerator
    public void writeStartObject() {
        this.generator.writeStartObject();
    }

    @Override // com.google.api.client.json.JsonGenerator
    public void writeString(String str) {
        this.generator.writeString(str);
    }
}
