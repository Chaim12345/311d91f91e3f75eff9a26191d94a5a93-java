package com.fasterxml.jackson.core;

import com.fasterxml.jackson.core.io.NumberInput;
/* loaded from: classes.dex */
public class JsonPointer {
    public static final char SEPARATOR = '/';

    /* renamed from: f  reason: collision with root package name */
    protected static final JsonPointer f5050f = new JsonPointer();

    /* renamed from: a  reason: collision with root package name */
    protected final JsonPointer f5051a;

    /* renamed from: b  reason: collision with root package name */
    protected volatile JsonPointer f5052b;

    /* renamed from: c  reason: collision with root package name */
    protected final String f5053c;

    /* renamed from: d  reason: collision with root package name */
    protected final String f5054d;

    /* renamed from: e  reason: collision with root package name */
    protected final int f5055e;

    protected JsonPointer() {
        this.f5051a = null;
        this.f5054d = "";
        this.f5055e = -1;
        this.f5053c = "";
    }

    protected JsonPointer(String str, String str2, int i2, JsonPointer jsonPointer) {
        this.f5053c = str;
        this.f5051a = jsonPointer;
        this.f5054d = str2;
        this.f5055e = i2;
    }

    protected JsonPointer(String str, String str2, JsonPointer jsonPointer) {
        this.f5053c = str;
        this.f5051a = jsonPointer;
        this.f5054d = str2;
        this.f5055e = _parseIndex(str2);
    }

    private static void _appendEscape(StringBuilder sb, char c2) {
        if (c2 == '0') {
            c2 = '~';
        } else if (c2 == '1') {
            c2 = SEPARATOR;
        } else {
            sb.append('~');
        }
        sb.append(c2);
    }

    private static void _appendEscaped(StringBuilder sb, String str) {
        String str2;
        int length = str.length();
        for (int i2 = 0; i2 < length; i2++) {
            char charAt = str.charAt(i2);
            if (charAt == '/') {
                str2 = "~1";
            } else if (charAt == '~') {
                str2 = "~0";
            } else {
                sb.append(charAt);
            }
            sb.append(str2);
        }
    }

    private static String _fullPath(JsonPointer jsonPointer, String str) {
        if (jsonPointer == null) {
            StringBuilder sb = new StringBuilder(str.length() + 1);
            sb.append(SEPARATOR);
            _appendEscaped(sb, str);
            return sb.toString();
        }
        String str2 = jsonPointer.f5053c;
        StringBuilder sb2 = new StringBuilder(str.length() + 1 + str2.length());
        sb2.append(SEPARATOR);
        _appendEscaped(sb2, str);
        sb2.append(str2);
        return sb2.toString();
    }

    private static final int _parseIndex(String str) {
        int length = str.length();
        if (length == 0 || length > 10) {
            return -1;
        }
        char charAt = str.charAt(0);
        if (charAt <= '0') {
            return (length == 1 && charAt == '0') ? 0 : -1;
        } else if (charAt > '9') {
            return -1;
        } else {
            for (int i2 = 1; i2 < length; i2++) {
                char charAt2 = str.charAt(i2);
                if (charAt2 > '9' || charAt2 < '0') {
                    return -1;
                }
            }
            if (length != 10 || NumberInput.parseLong(str) <= 2147483647L) {
                return NumberInput.parseInt(str);
            }
            return -1;
        }
    }

    protected static JsonPointer c(String str, int i2) {
        int length = str.length();
        StringBuilder sb = new StringBuilder(Math.max(16, length));
        if (i2 > 2) {
            sb.append((CharSequence) str, 1, i2 - 1);
        }
        int i3 = i2 + 1;
        _appendEscape(sb, str.charAt(i2));
        while (i3 < length) {
            char charAt = str.charAt(i3);
            if (charAt == '/') {
                return new JsonPointer(str, sb.toString(), d(str.substring(i3)));
            }
            i3++;
            if (charAt != '~' || i3 >= length) {
                sb.append(charAt);
            } else {
                _appendEscape(sb, str.charAt(i3));
                i3++;
            }
        }
        return new JsonPointer(str, sb.toString(), f5050f);
    }

    public static JsonPointer compile(String str) {
        if (str == null || str.length() == 0) {
            return f5050f;
        }
        if (str.charAt(0) == '/') {
            return d(str);
        }
        throw new IllegalArgumentException("Invalid input: JSON Pointer expression must start with '/': \"" + str + "\"");
    }

    protected static JsonPointer d(String str) {
        int length = str.length();
        int i2 = 1;
        while (i2 < length) {
            char charAt = str.charAt(i2);
            if (charAt == '/') {
                return new JsonPointer(str, str.substring(1, i2), d(str.substring(i2)));
            }
            i2++;
            if (charAt == '~' && i2 < length) {
                return c(str, i2);
            }
        }
        return new JsonPointer(str, str.substring(1), f5050f);
    }

    public static JsonPointer empty() {
        return f5050f;
    }

    public static JsonPointer forPath(JsonStreamContext jsonStreamContext, boolean z) {
        if (jsonStreamContext == null) {
            return f5050f;
        }
        if (!jsonStreamContext.hasPathSegment() && (!z || !jsonStreamContext.inRoot() || !jsonStreamContext.hasCurrentIndex())) {
            jsonStreamContext = jsonStreamContext.getParent();
        }
        JsonPointer jsonPointer = null;
        while (jsonStreamContext != null) {
            if (jsonStreamContext.inObject()) {
                String currentName = jsonStreamContext.getCurrentName();
                if (currentName == null) {
                    currentName = "";
                }
                jsonPointer = new JsonPointer(_fullPath(jsonPointer, currentName), currentName, jsonPointer);
            } else if (jsonStreamContext.inArray() || z) {
                int currentIndex = jsonStreamContext.getCurrentIndex();
                String valueOf = String.valueOf(currentIndex);
                jsonPointer = new JsonPointer(_fullPath(jsonPointer, valueOf), valueOf, currentIndex, jsonPointer);
            }
            jsonStreamContext = jsonStreamContext.getParent();
        }
        return jsonPointer == null ? f5050f : jsonPointer;
    }

    public static JsonPointer valueOf(String str) {
        return compile(str);
    }

    protected JsonPointer a() {
        JsonPointer last = last();
        if (last == this) {
            return f5050f;
        }
        int length = last.f5053c.length();
        JsonPointer jsonPointer = this.f5051a;
        String str = this.f5053c;
        return new JsonPointer(str.substring(0, str.length() - length), this.f5054d, this.f5055e, jsonPointer.b(length, last));
    }

    public JsonPointer append(JsonPointer jsonPointer) {
        JsonPointer jsonPointer2 = f5050f;
        if (this == jsonPointer2) {
            return jsonPointer;
        }
        if (jsonPointer == jsonPointer2) {
            return this;
        }
        String str = this.f5053c;
        if (str.endsWith("/")) {
            str = str.substring(0, str.length() - 1);
        }
        return compile(str + jsonPointer.f5053c);
    }

    protected JsonPointer b(int i2, JsonPointer jsonPointer) {
        if (this == jsonPointer) {
            return f5050f;
        }
        JsonPointer jsonPointer2 = this.f5051a;
        String str = this.f5053c;
        return new JsonPointer(str.substring(0, str.length() - i2), this.f5054d, this.f5055e, jsonPointer2.b(i2, jsonPointer));
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj != null && (obj instanceof JsonPointer)) {
            return this.f5053c.equals(((JsonPointer) obj).f5053c);
        }
        return false;
    }

    public int getMatchingIndex() {
        return this.f5055e;
    }

    public String getMatchingProperty() {
        return this.f5054d;
    }

    public int hashCode() {
        return this.f5053c.hashCode();
    }

    public JsonPointer head() {
        JsonPointer jsonPointer = this.f5052b;
        if (jsonPointer == null) {
            if (this != f5050f) {
                jsonPointer = a();
            }
            this.f5052b = jsonPointer;
        }
        return jsonPointer;
    }

    public JsonPointer last() {
        if (this == f5050f) {
            return null;
        }
        JsonPointer jsonPointer = this;
        while (true) {
            JsonPointer jsonPointer2 = jsonPointer.f5051a;
            if (jsonPointer2 == f5050f) {
                return jsonPointer;
            }
            jsonPointer = jsonPointer2;
        }
    }

    public JsonPointer matchElement(int i2) {
        if (i2 != this.f5055e || i2 < 0) {
            return null;
        }
        return this.f5051a;
    }

    public JsonPointer matchProperty(String str) {
        if (this.f5051a == null || !this.f5054d.equals(str)) {
            return null;
        }
        return this.f5051a;
    }

    public boolean matches() {
        return this.f5051a == null;
    }

    public boolean matchesElement(int i2) {
        return i2 == this.f5055e && i2 >= 0;
    }

    public boolean matchesProperty(String str) {
        return this.f5051a != null && this.f5054d.equals(str);
    }

    public boolean mayMatchElement() {
        return this.f5055e >= 0;
    }

    public boolean mayMatchProperty() {
        return this.f5054d != null;
    }

    public JsonPointer tail() {
        return this.f5051a;
    }

    public String toString() {
        return this.f5053c;
    }
}
