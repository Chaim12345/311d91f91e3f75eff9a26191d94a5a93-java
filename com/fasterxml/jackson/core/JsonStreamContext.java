package com.fasterxml.jackson.core;

import com.fasterxml.jackson.core.io.CharTypes;
import kotlinx.serialization.json.internal.AbstractJsonLexerKt;
import org.slf4j.Logger;
/* loaded from: classes.dex */
public abstract class JsonStreamContext {

    /* renamed from: a  reason: collision with root package name */
    protected int f5057a;

    /* renamed from: b  reason: collision with root package name */
    protected int f5058b;

    public final int getCurrentIndex() {
        int i2 = this.f5058b;
        if (i2 < 0) {
            return 0;
        }
        return i2;
    }

    public abstract String getCurrentName();

    public Object getCurrentValue() {
        return null;
    }

    public final int getEntryCount() {
        return this.f5058b + 1;
    }

    public abstract JsonStreamContext getParent();

    public JsonLocation getStartLocation(Object obj) {
        return JsonLocation.NA;
    }

    @Deprecated
    public final String getTypeDesc() {
        int i2 = this.f5057a;
        return i2 != 0 ? i2 != 1 ? i2 != 2 ? "?" : "OBJECT" : "ARRAY" : Logger.ROOT_LOGGER_NAME;
    }

    public boolean hasCurrentIndex() {
        return this.f5058b >= 0;
    }

    public boolean hasCurrentName() {
        return getCurrentName() != null;
    }

    public boolean hasPathSegment() {
        int i2 = this.f5057a;
        if (i2 == 2) {
            return hasCurrentName();
        }
        if (i2 == 1) {
            return hasCurrentIndex();
        }
        return false;
    }

    public final boolean inArray() {
        return this.f5057a == 1;
    }

    public final boolean inObject() {
        return this.f5057a == 2;
    }

    public final boolean inRoot() {
        return this.f5057a == 0;
    }

    public JsonPointer pathAsPointer() {
        return JsonPointer.forPath(this, false);
    }

    public JsonPointer pathAsPointer(boolean z) {
        return JsonPointer.forPath(this, z);
    }

    public void setCurrentValue(Object obj) {
    }

    public String toString() {
        char c2;
        StringBuilder sb = new StringBuilder(64);
        int i2 = this.f5057a;
        if (i2 != 0) {
            if (i2 != 1) {
                sb.append(AbstractJsonLexerKt.BEGIN_OBJ);
                String currentName = getCurrentName();
                if (currentName != null) {
                    sb.append('\"');
                    CharTypes.appendQuoted(sb, currentName);
                    sb.append('\"');
                } else {
                    sb.append('?');
                }
                c2 = AbstractJsonLexerKt.END_OBJ;
            } else {
                sb.append(AbstractJsonLexerKt.BEGIN_LIST);
                sb.append(getCurrentIndex());
                c2 = AbstractJsonLexerKt.END_LIST;
            }
            sb.append(c2);
        } else {
            sb.append("/");
        }
        return sb.toString();
    }

    public String typeDesc() {
        int i2 = this.f5057a;
        return i2 != 0 ? i2 != 1 ? i2 != 2 ? "?" : "Object" : "Array" : "root";
    }
}
