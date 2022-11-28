package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import java.io.Serializable;
@GwtCompatible(serializable = true)
/* loaded from: classes2.dex */
final class UsingToStringOrdering extends Ordering<Object> implements Serializable {

    /* renamed from: a  reason: collision with root package name */
    static final UsingToStringOrdering f9118a = new UsingToStringOrdering();
    private static final long serialVersionUID = 0;

    private UsingToStringOrdering() {
    }

    private Object readResolve() {
        return f9118a;
    }

    @Override // com.google.common.collect.Ordering, java.util.Comparator
    public int compare(Object obj, Object obj2) {
        return obj.toString().compareTo(obj2.toString());
    }

    public String toString() {
        return "Ordering.usingToString()";
    }
}
