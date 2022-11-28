package com.fasterxml.jackson.core;
/* loaded from: classes.dex */
public enum JsonToken {
    NOT_AVAILABLE(null, -1),
    START_OBJECT("{", 1),
    END_OBJECT("}", 2),
    START_ARRAY("[", 3),
    END_ARRAY("]", 4),
    FIELD_NAME(null, 5),
    VALUE_EMBEDDED_OBJECT(null, 12),
    VALUE_STRING(null, 6),
    VALUE_NUMBER_INT(null, 7),
    VALUE_NUMBER_FLOAT(null, 8),
    VALUE_TRUE("true", 9),
    VALUE_FALSE("false", 10),
    VALUE_NULL("null", 11);
    

    /* renamed from: a  reason: collision with root package name */
    final String f5059a;

    /* renamed from: b  reason: collision with root package name */
    final char[] f5060b;

    /* renamed from: c  reason: collision with root package name */
    final byte[] f5061c;

    /* renamed from: d  reason: collision with root package name */
    final int f5062d;

    /* renamed from: e  reason: collision with root package name */
    final boolean f5063e;

    /* renamed from: f  reason: collision with root package name */
    final boolean f5064f;

    /* renamed from: g  reason: collision with root package name */
    final boolean f5065g;

    /* renamed from: h  reason: collision with root package name */
    final boolean f5066h;

    /* renamed from: i  reason: collision with root package name */
    final boolean f5067i;

    JsonToken(String str, int i2) {
        boolean z = false;
        if (str == null) {
            this.f5059a = null;
            this.f5060b = null;
            this.f5061c = null;
        } else {
            this.f5059a = str;
            char[] charArray = str.toCharArray();
            this.f5060b = charArray;
            int length = charArray.length;
            this.f5061c = new byte[length];
            for (int i3 = 0; i3 < length; i3++) {
                this.f5061c[i3] = (byte) this.f5060b[i3];
            }
        }
        this.f5062d = i2;
        this.f5066h = i2 == 10 || i2 == 9;
        this.f5065g = i2 == 7 || i2 == 8;
        boolean z2 = i2 == 1 || i2 == 3;
        this.f5063e = z2;
        boolean z3 = i2 == 2 || i2 == 4;
        this.f5064f = z3;
        if (!z2 && !z3 && i2 != 5 && i2 != -1) {
            z = true;
        }
        this.f5067i = z;
    }

    public final byte[] asByteArray() {
        return this.f5061c;
    }

    public final char[] asCharArray() {
        return this.f5060b;
    }

    public final String asString() {
        return this.f5059a;
    }

    public final int id() {
        return this.f5062d;
    }

    public final boolean isBoolean() {
        return this.f5066h;
    }

    public final boolean isNumeric() {
        return this.f5065g;
    }

    public final boolean isScalarValue() {
        return this.f5067i;
    }

    public final boolean isStructEnd() {
        return this.f5064f;
    }

    public final boolean isStructStart() {
        return this.f5063e;
    }
}
