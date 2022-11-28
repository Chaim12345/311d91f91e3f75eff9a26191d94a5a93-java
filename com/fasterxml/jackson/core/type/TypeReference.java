package com.fasterxml.jackson.core.type;

import java.lang.reflect.Type;
/* loaded from: classes.dex */
public abstract class TypeReference<T> implements Comparable<TypeReference<T>> {

    /* renamed from: a  reason: collision with root package name */
    protected final Type f5224a;

    public int compareTo(TypeReference<T> typeReference) {
        return 0;
    }

    @Override // java.lang.Comparable
    public /* bridge */ /* synthetic */ int compareTo(Object obj) {
        return compareTo((TypeReference) ((TypeReference) obj));
    }

    public Type getType() {
        return this.f5224a;
    }
}
