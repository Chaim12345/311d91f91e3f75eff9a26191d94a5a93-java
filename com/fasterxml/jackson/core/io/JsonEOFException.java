package com.fasterxml.jackson.core.io;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
/* loaded from: classes.dex */
public class JsonEOFException extends JsonParseException {
    private static final long serialVersionUID = 1;

    /* renamed from: d  reason: collision with root package name */
    protected final JsonToken f5160d;

    public JsonEOFException(JsonParser jsonParser, JsonToken jsonToken, String str) {
        super(jsonParser, str);
        this.f5160d = jsonToken;
    }

    public JsonToken getTokenBeingDecoded() {
        return this.f5160d;
    }
}
