package com.fasterxml.jackson.core.json;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonStreamContext;
/* loaded from: classes.dex */
public class JsonWriteContext extends JsonStreamContext {
    public static final int STATUS_EXPECT_NAME = 5;
    public static final int STATUS_EXPECT_VALUE = 4;
    public static final int STATUS_OK_AFTER_COLON = 2;
    public static final int STATUS_OK_AFTER_COMMA = 1;
    public static final int STATUS_OK_AFTER_SPACE = 3;
    public static final int STATUS_OK_AS_IS = 0;

    /* renamed from: c  reason: collision with root package name */
    protected final JsonWriteContext f5198c;

    /* renamed from: d  reason: collision with root package name */
    protected DupDetector f5199d;

    /* renamed from: e  reason: collision with root package name */
    protected JsonWriteContext f5200e;

    /* renamed from: f  reason: collision with root package name */
    protected String f5201f;

    /* renamed from: g  reason: collision with root package name */
    protected Object f5202g;

    /* renamed from: h  reason: collision with root package name */
    protected boolean f5203h;

    protected JsonWriteContext(int i2, JsonWriteContext jsonWriteContext, DupDetector dupDetector) {
        this.f5057a = i2;
        this.f5198c = jsonWriteContext;
        this.f5199d = dupDetector;
        this.f5058b = -1;
    }

    protected JsonWriteContext(int i2, JsonWriteContext jsonWriteContext, DupDetector dupDetector, Object obj) {
        this.f5057a = i2;
        this.f5198c = jsonWriteContext;
        this.f5199d = dupDetector;
        this.f5058b = -1;
        this.f5202g = obj;
    }

    private final void _checkDup(DupDetector dupDetector, String str) {
        if (dupDetector.isDup(str)) {
            Object source = dupDetector.getSource();
            throw new JsonGenerationException("Duplicate field '" + str + "'", source instanceof JsonGenerator ? (JsonGenerator) source : null);
        }
    }

    @Deprecated
    public static JsonWriteContext createRootContext() {
        return createRootContext(null);
    }

    public static JsonWriteContext createRootContext(DupDetector dupDetector) {
        return new JsonWriteContext(0, null, dupDetector);
    }

    protected JsonWriteContext a(int i2) {
        this.f5057a = i2;
        this.f5058b = -1;
        this.f5201f = null;
        this.f5203h = false;
        this.f5202g = null;
        DupDetector dupDetector = this.f5199d;
        if (dupDetector != null) {
            dupDetector.reset();
        }
        return this;
    }

    protected JsonWriteContext b(int i2, Object obj) {
        this.f5057a = i2;
        this.f5058b = -1;
        this.f5201f = null;
        this.f5203h = false;
        this.f5202g = obj;
        DupDetector dupDetector = this.f5199d;
        if (dupDetector != null) {
            dupDetector.reset();
        }
        return this;
    }

    public JsonWriteContext clearAndGetParent() {
        this.f5202g = null;
        return this.f5198c;
    }

    public JsonWriteContext createChildArrayContext() {
        JsonWriteContext jsonWriteContext = this.f5200e;
        if (jsonWriteContext == null) {
            DupDetector dupDetector = this.f5199d;
            JsonWriteContext jsonWriteContext2 = new JsonWriteContext(1, this, dupDetector == null ? null : dupDetector.child());
            this.f5200e = jsonWriteContext2;
            return jsonWriteContext2;
        }
        return jsonWriteContext.a(1);
    }

    public JsonWriteContext createChildArrayContext(Object obj) {
        JsonWriteContext jsonWriteContext = this.f5200e;
        if (jsonWriteContext == null) {
            DupDetector dupDetector = this.f5199d;
            JsonWriteContext jsonWriteContext2 = new JsonWriteContext(1, this, dupDetector == null ? null : dupDetector.child(), obj);
            this.f5200e = jsonWriteContext2;
            return jsonWriteContext2;
        }
        return jsonWriteContext.b(1, obj);
    }

    public JsonWriteContext createChildObjectContext() {
        JsonWriteContext jsonWriteContext = this.f5200e;
        if (jsonWriteContext == null) {
            DupDetector dupDetector = this.f5199d;
            JsonWriteContext jsonWriteContext2 = new JsonWriteContext(2, this, dupDetector == null ? null : dupDetector.child());
            this.f5200e = jsonWriteContext2;
            return jsonWriteContext2;
        }
        return jsonWriteContext.a(2);
    }

    public JsonWriteContext createChildObjectContext(Object obj) {
        JsonWriteContext jsonWriteContext = this.f5200e;
        if (jsonWriteContext == null) {
            DupDetector dupDetector = this.f5199d;
            JsonWriteContext jsonWriteContext2 = new JsonWriteContext(2, this, dupDetector == null ? null : dupDetector.child(), obj);
            this.f5200e = jsonWriteContext2;
            return jsonWriteContext2;
        }
        return jsonWriteContext.b(2, obj);
    }

    @Override // com.fasterxml.jackson.core.JsonStreamContext
    public final String getCurrentName() {
        return this.f5201f;
    }

    @Override // com.fasterxml.jackson.core.JsonStreamContext
    public Object getCurrentValue() {
        return this.f5202g;
    }

    public DupDetector getDupDetector() {
        return this.f5199d;
    }

    @Override // com.fasterxml.jackson.core.JsonStreamContext
    public final JsonWriteContext getParent() {
        return this.f5198c;
    }

    @Override // com.fasterxml.jackson.core.JsonStreamContext
    public boolean hasCurrentName() {
        return this.f5201f != null;
    }

    @Override // com.fasterxml.jackson.core.JsonStreamContext
    public void setCurrentValue(Object obj) {
        this.f5202g = obj;
    }

    public JsonWriteContext withDupDetector(DupDetector dupDetector) {
        this.f5199d = dupDetector;
        return this;
    }

    public int writeFieldName(String str) {
        if (this.f5057a != 2 || this.f5203h) {
            return 4;
        }
        this.f5203h = true;
        this.f5201f = str;
        DupDetector dupDetector = this.f5199d;
        if (dupDetector != null) {
            _checkDup(dupDetector, str);
        }
        return this.f5058b < 0 ? 0 : 1;
    }

    public int writeValue() {
        int i2 = this.f5057a;
        if (i2 == 2) {
            if (this.f5203h) {
                this.f5203h = false;
                this.f5058b++;
                return 2;
            }
            return 5;
        } else if (i2 == 1) {
            int i3 = this.f5058b;
            this.f5058b = i3 + 1;
            return i3 < 0 ? 0 : 1;
        } else {
            int i4 = this.f5058b + 1;
            this.f5058b = i4;
            return i4 == 0 ? 0 : 3;
        }
    }
}
