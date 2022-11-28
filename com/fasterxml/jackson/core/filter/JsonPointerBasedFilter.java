package com.fasterxml.jackson.core.filter;

import com.fasterxml.jackson.core.JsonPointer;
/* loaded from: classes.dex */
public class JsonPointerBasedFilter extends TokenFilter {

    /* renamed from: a  reason: collision with root package name */
    protected final JsonPointer f5127a;

    public JsonPointerBasedFilter(JsonPointer jsonPointer) {
        this.f5127a = jsonPointer;
    }

    public JsonPointerBasedFilter(String str) {
        this(JsonPointer.compile(str));
    }

    @Override // com.fasterxml.jackson.core.filter.TokenFilter
    protected boolean a() {
        return this.f5127a.matches();
    }

    @Override // com.fasterxml.jackson.core.filter.TokenFilter
    public TokenFilter filterStartArray() {
        return this;
    }

    @Override // com.fasterxml.jackson.core.filter.TokenFilter
    public TokenFilter filterStartObject() {
        return this;
    }

    @Override // com.fasterxml.jackson.core.filter.TokenFilter
    public TokenFilter includeElement(int i2) {
        JsonPointer matchElement = this.f5127a.matchElement(i2);
        if (matchElement == null) {
            return null;
        }
        return matchElement.matches() ? TokenFilter.INCLUDE_ALL : new JsonPointerBasedFilter(matchElement);
    }

    @Override // com.fasterxml.jackson.core.filter.TokenFilter
    public TokenFilter includeProperty(String str) {
        JsonPointer matchProperty = this.f5127a.matchProperty(str);
        if (matchProperty == null) {
            return null;
        }
        return matchProperty.matches() ? TokenFilter.INCLUDE_ALL : new JsonPointerBasedFilter(matchProperty);
    }

    @Override // com.fasterxml.jackson.core.filter.TokenFilter
    public String toString() {
        return "[JsonPointerFilter at: " + this.f5127a + "]";
    }
}
