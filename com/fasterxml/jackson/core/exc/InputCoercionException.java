package com.fasterxml.jackson.core.exc;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.core.util.RequestPayload;
/* loaded from: classes.dex */
public class InputCoercionException extends StreamReadException {
    private static final long serialVersionUID = 1;

    /* renamed from: d  reason: collision with root package name */
    protected final JsonToken f5106d;

    /* renamed from: e  reason: collision with root package name */
    protected final Class f5107e;

    public InputCoercionException(JsonParser jsonParser, String str, JsonToken jsonToken, Class<?> cls) {
        super(jsonParser, str);
        this.f5106d = jsonToken;
        this.f5107e = cls;
    }

    public JsonToken getInputType() {
        return this.f5106d;
    }

    public Class<?> getTargetType() {
        return this.f5107e;
    }

    @Override // com.fasterxml.jackson.core.exc.StreamReadException
    public InputCoercionException withParser(JsonParser jsonParser) {
        this.f5108b = jsonParser;
        return this;
    }

    @Override // com.fasterxml.jackson.core.exc.StreamReadException
    public InputCoercionException withRequestPayload(RequestPayload requestPayload) {
        this.f5109c = requestPayload;
        return this;
    }
}
