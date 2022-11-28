package com.fasterxml.jackson.core;

import java.io.Serializable;
import java.nio.charset.Charset;
import kotlinx.serialization.json.internal.AbstractJsonLexerKt;
/* loaded from: classes.dex */
public class JsonLocation implements Serializable {
    public static final int MAX_CONTENT_SNIPPET = 500;
    public static final JsonLocation NA = new JsonLocation(null, -1, -1, -1, -1);
    private static final long serialVersionUID = 1;

    /* renamed from: a  reason: collision with root package name */
    protected final long f5043a;

    /* renamed from: b  reason: collision with root package name */
    protected final long f5044b;

    /* renamed from: c  reason: collision with root package name */
    protected final int f5045c;

    /* renamed from: d  reason: collision with root package name */
    protected final int f5046d;

    /* renamed from: e  reason: collision with root package name */
    final transient Object f5047e;

    public JsonLocation(Object obj, long j2, int i2, int i3) {
        this(obj, -1L, j2, i2, i3);
    }

    public JsonLocation(Object obj, long j2, long j3, int i2, int i3) {
        this.f5047e = obj;
        this.f5043a = j2;
        this.f5044b = j3;
        this.f5045c = i2;
        this.f5046d = i3;
    }

    private int _append(StringBuilder sb, String str) {
        sb.append('\"');
        sb.append(str);
        sb.append('\"');
        return str.length();
    }

    /* JADX WARN: Removed duplicated region for block: B:30:0x0098  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    protected StringBuilder a(StringBuilder sb) {
        int length;
        int _append;
        Object obj = this.f5047e;
        if (obj == null) {
            sb.append("UNKNOWN");
            return sb;
        }
        Class<?> cls = obj instanceof Class ? (Class) obj : obj.getClass();
        String name = cls.getName();
        if (name.startsWith("java.")) {
            name = cls.getSimpleName();
        } else if (obj instanceof byte[]) {
            name = "byte[]";
        } else if (obj instanceof char[]) {
            name = "char[]";
        }
        sb.append('(');
        sb.append(name);
        sb.append(')');
        int i2 = 0;
        String str = " chars";
        if (obj instanceof CharSequence) {
            CharSequence charSequence = (CharSequence) obj;
            length = charSequence.length();
            _append = _append(sb, charSequence.subSequence(0, Math.min(length, 500)).toString());
        } else if (!(obj instanceof char[])) {
            if (obj instanceof byte[]) {
                byte[] bArr = (byte[]) obj;
                int min = Math.min(bArr.length, 500);
                _append(sb, new String(bArr, 0, min, Charset.forName("UTF-8")));
                i2 = bArr.length - min;
                str = " bytes";
            }
            if (i2 > 0) {
                sb.append("[truncated ");
                sb.append(i2);
                sb.append(str);
                sb.append(AbstractJsonLexerKt.END_LIST);
            }
            return sb;
        } else {
            char[] cArr = (char[]) obj;
            length = cArr.length;
            _append = _append(sb, new String(cArr, 0, Math.min(length, 500)));
        }
        i2 = length - _append;
        if (i2 > 0) {
        }
        return sb;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj != null && (obj instanceof JsonLocation)) {
            JsonLocation jsonLocation = (JsonLocation) obj;
            Object obj2 = this.f5047e;
            if (obj2 == null) {
                if (jsonLocation.f5047e != null) {
                    return false;
                }
            } else if (!obj2.equals(jsonLocation.f5047e)) {
                return false;
            }
            return this.f5045c == jsonLocation.f5045c && this.f5046d == jsonLocation.f5046d && this.f5044b == jsonLocation.f5044b && getByteOffset() == jsonLocation.getByteOffset();
        }
        return false;
    }

    public long getByteOffset() {
        return this.f5043a;
    }

    public long getCharOffset() {
        return this.f5044b;
    }

    public int getColumnNr() {
        return this.f5046d;
    }

    public int getLineNr() {
        return this.f5045c;
    }

    public Object getSourceRef() {
        return this.f5047e;
    }

    public int hashCode() {
        Object obj = this.f5047e;
        return ((((obj == null ? 1 : obj.hashCode()) ^ this.f5045c) + this.f5046d) ^ ((int) this.f5044b)) + ((int) this.f5043a);
    }

    public String sourceDescription() {
        return a(new StringBuilder(100)).toString();
    }

    public String toString() {
        StringBuilder sb = new StringBuilder(80);
        sb.append("[Source: ");
        a(sb);
        sb.append("; line: ");
        sb.append(this.f5045c);
        sb.append(", column: ");
        sb.append(this.f5046d);
        sb.append(AbstractJsonLexerKt.END_LIST);
        return sb.toString();
    }
}
