package com.google.common.reflect;

import com.google.common.annotations.Beta;
import java.lang.reflect.TypeVariable;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;
@Beta
/* loaded from: classes2.dex */
public abstract class TypeParameter<T> extends TypeCapture<T> {

    /* renamed from: a  reason: collision with root package name */
    final TypeVariable f9385a;

    public final boolean equals(@NullableDecl Object obj) {
        if (obj instanceof TypeParameter) {
            return this.f9385a.equals(((TypeParameter) obj).f9385a);
        }
        return false;
    }

    public final int hashCode() {
        return this.f9385a.hashCode();
    }

    public String toString() {
        return this.f9385a.toString();
    }
}
