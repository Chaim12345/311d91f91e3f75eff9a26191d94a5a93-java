package com.fasterxml.jackson.core;
/* loaded from: classes.dex */
public class JsonGenerationException extends JsonProcessingException {
    private static final long serialVersionUID = 123;

    /* renamed from: b  reason: collision with root package name */
    protected transient JsonGenerator f5040b;

    @Deprecated
    public JsonGenerationException(String str) {
        super(str, null);
    }

    public JsonGenerationException(String str, JsonGenerator jsonGenerator) {
        super(str, null);
        this.f5040b = jsonGenerator;
    }

    @Deprecated
    public JsonGenerationException(String str, Throwable th) {
        super(str, null, th);
    }

    public JsonGenerationException(String str, Throwable th, JsonGenerator jsonGenerator) {
        super(str, null, th);
        this.f5040b = jsonGenerator;
    }

    @Deprecated
    public JsonGenerationException(Throwable th) {
        super(th);
    }

    public JsonGenerationException(Throwable th, JsonGenerator jsonGenerator) {
        super(th);
        this.f5040b = jsonGenerator;
    }

    @Override // com.fasterxml.jackson.core.JsonProcessingException
    public JsonGenerator getProcessor() {
        return this.f5040b;
    }

    public JsonGenerationException withGenerator(JsonGenerator jsonGenerator) {
        this.f5040b = jsonGenerator;
        return this;
    }
}
