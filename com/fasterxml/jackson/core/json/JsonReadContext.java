package com.fasterxml.jackson.core.json;

import com.fasterxml.jackson.core.JsonLocation;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonStreamContext;
/* loaded from: classes.dex */
public final class JsonReadContext extends JsonStreamContext {

    /* renamed from: c  reason: collision with root package name */
    protected final JsonReadContext f5191c;

    /* renamed from: d  reason: collision with root package name */
    protected DupDetector f5192d;

    /* renamed from: e  reason: collision with root package name */
    protected JsonReadContext f5193e;

    /* renamed from: f  reason: collision with root package name */
    protected String f5194f;

    /* renamed from: g  reason: collision with root package name */
    protected Object f5195g;

    /* renamed from: h  reason: collision with root package name */
    protected int f5196h;

    /* renamed from: i  reason: collision with root package name */
    protected int f5197i;

    public JsonReadContext(JsonReadContext jsonReadContext, DupDetector dupDetector, int i2, int i3, int i4) {
        this.f5191c = jsonReadContext;
        this.f5192d = dupDetector;
        this.f5057a = i2;
        this.f5196h = i3;
        this.f5197i = i4;
        this.f5058b = -1;
    }

    private void _checkDup(DupDetector dupDetector, String str) {
        if (dupDetector.isDup(str)) {
            Object source = dupDetector.getSource();
            JsonParser jsonParser = source instanceof JsonParser ? (JsonParser) source : null;
            throw new JsonParseException(jsonParser, "Duplicate field '" + str + "'");
        }
    }

    public static JsonReadContext createRootContext(int i2, int i3, DupDetector dupDetector) {
        return new JsonReadContext(null, dupDetector, 0, i2, i3);
    }

    public static JsonReadContext createRootContext(DupDetector dupDetector) {
        return new JsonReadContext(null, dupDetector, 0, 1, 0);
    }

    protected void a(int i2, int i3, int i4) {
        this.f5057a = i2;
        this.f5058b = -1;
        this.f5196h = i3;
        this.f5197i = i4;
        this.f5194f = null;
        this.f5195g = null;
        DupDetector dupDetector = this.f5192d;
        if (dupDetector != null) {
            dupDetector.reset();
        }
    }

    public JsonReadContext clearAndGetParent() {
        this.f5195g = null;
        return this.f5191c;
    }

    public JsonReadContext createChildArrayContext(int i2, int i3) {
        JsonReadContext jsonReadContext = this.f5193e;
        if (jsonReadContext == null) {
            DupDetector dupDetector = this.f5192d;
            jsonReadContext = new JsonReadContext(this, dupDetector == null ? null : dupDetector.child(), 1, i2, i3);
            this.f5193e = jsonReadContext;
        } else {
            jsonReadContext.a(1, i2, i3);
        }
        return jsonReadContext;
    }

    public JsonReadContext createChildObjectContext(int i2, int i3) {
        JsonReadContext jsonReadContext = this.f5193e;
        if (jsonReadContext != null) {
            jsonReadContext.a(2, i2, i3);
            return jsonReadContext;
        }
        DupDetector dupDetector = this.f5192d;
        JsonReadContext jsonReadContext2 = new JsonReadContext(this, dupDetector == null ? null : dupDetector.child(), 2, i2, i3);
        this.f5193e = jsonReadContext2;
        return jsonReadContext2;
    }

    public boolean expectComma() {
        int i2 = this.f5058b + 1;
        this.f5058b = i2;
        return this.f5057a != 0 && i2 > 0;
    }

    @Override // com.fasterxml.jackson.core.JsonStreamContext
    public String getCurrentName() {
        return this.f5194f;
    }

    @Override // com.fasterxml.jackson.core.JsonStreamContext
    public Object getCurrentValue() {
        return this.f5195g;
    }

    public DupDetector getDupDetector() {
        return this.f5192d;
    }

    @Override // com.fasterxml.jackson.core.JsonStreamContext
    public JsonReadContext getParent() {
        return this.f5191c;
    }

    @Override // com.fasterxml.jackson.core.JsonStreamContext
    public JsonLocation getStartLocation(Object obj) {
        return new JsonLocation(obj, -1L, this.f5196h, this.f5197i);
    }

    @Override // com.fasterxml.jackson.core.JsonStreamContext
    public boolean hasCurrentName() {
        return this.f5194f != null;
    }

    public void setCurrentName(String str) {
        this.f5194f = str;
        DupDetector dupDetector = this.f5192d;
        if (dupDetector != null) {
            _checkDup(dupDetector, str);
        }
    }

    @Override // com.fasterxml.jackson.core.JsonStreamContext
    public void setCurrentValue(Object obj) {
        this.f5195g = obj;
    }

    public JsonReadContext withDupDetector(DupDetector dupDetector) {
        this.f5192d = dupDetector;
        return this;
    }
}
