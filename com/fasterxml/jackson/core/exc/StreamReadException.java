package com.fasterxml.jackson.core.exc;

import com.fasterxml.jackson.core.JsonLocation;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.util.RequestPayload;
/* loaded from: classes.dex */
public abstract class StreamReadException extends JsonProcessingException {

    /* renamed from: b  reason: collision with root package name */
    protected transient JsonParser f5108b;

    /* renamed from: c  reason: collision with root package name */
    protected RequestPayload f5109c;

    public StreamReadException(JsonParser jsonParser, String str) {
        super(str, jsonParser == null ? null : jsonParser.getCurrentLocation());
        this.f5108b = jsonParser;
    }

    public StreamReadException(JsonParser jsonParser, String str, JsonLocation jsonLocation) {
        super(str, jsonLocation, null);
        this.f5108b = jsonParser;
    }

    public StreamReadException(JsonParser jsonParser, String str, Throwable th) {
        super(str, jsonParser == null ? null : jsonParser.getCurrentLocation(), th);
        this.f5108b = jsonParser;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public StreamReadException(String str, JsonLocation jsonLocation, Throwable th) {
        super(str);
        if (th != null) {
            initCause(th);
        }
        this.f5056a = jsonLocation;
    }

    @Override // com.fasterxml.jackson.core.JsonProcessingException, java.lang.Throwable
    public String getMessage() {
        String message = super.getMessage();
        if (this.f5109c != null) {
            return message + "\nRequest payload : " + this.f5109c.toString();
        }
        return message;
    }

    @Override // com.fasterxml.jackson.core.JsonProcessingException
    public JsonParser getProcessor() {
        return this.f5108b;
    }

    public RequestPayload getRequestPayload() {
        return this.f5109c;
    }

    public String getRequestPayloadAsString() {
        RequestPayload requestPayload = this.f5109c;
        if (requestPayload != null) {
            return requestPayload.toString();
        }
        return null;
    }

    public abstract StreamReadException withParser(JsonParser jsonParser);

    public abstract StreamReadException withRequestPayload(RequestPayload requestPayload);
}
