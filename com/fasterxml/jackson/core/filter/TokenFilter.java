package com.fasterxml.jackson.core.filter;

import com.fasterxml.jackson.core.JsonParser;
import java.io.Reader;
import java.math.BigDecimal;
import java.math.BigInteger;
/* loaded from: classes.dex */
public class TokenFilter {
    public static final TokenFilter INCLUDE_ALL = new TokenFilter();

    protected boolean a() {
        return true;
    }

    public void filterFinishArray() {
    }

    public void filterFinishObject() {
    }

    public TokenFilter filterStartArray() {
        return this;
    }

    public TokenFilter filterStartObject() {
        return this;
    }

    public boolean includeBinary() {
        return a();
    }

    public boolean includeBoolean(boolean z) {
        return a();
    }

    public TokenFilter includeElement(int i2) {
        return this;
    }

    public boolean includeEmbeddedValue(Object obj) {
        return a();
    }

    public boolean includeNull() {
        return a();
    }

    public boolean includeNumber(double d2) {
        return a();
    }

    public boolean includeNumber(float f2) {
        return a();
    }

    public boolean includeNumber(int i2) {
        return a();
    }

    public boolean includeNumber(long j2) {
        return a();
    }

    public boolean includeNumber(BigDecimal bigDecimal) {
        return a();
    }

    public boolean includeNumber(BigInteger bigInteger) {
        return a();
    }

    public TokenFilter includeProperty(String str) {
        return this;
    }

    public boolean includeRawValue() {
        return a();
    }

    public TokenFilter includeRootValue(int i2) {
        return this;
    }

    public boolean includeString(Reader reader, int i2) {
        return a();
    }

    public boolean includeString(String str) {
        return a();
    }

    public boolean includeValue(JsonParser jsonParser) {
        return a();
    }

    public String toString() {
        return this == INCLUDE_ALL ? "TokenFilter.INCLUDE_ALL" : super.toString();
    }
}
