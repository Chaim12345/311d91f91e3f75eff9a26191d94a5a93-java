package com.airbnb.lottie.model;

import androidx.annotation.Nullable;
import androidx.annotation.RestrictTo;
import androidx.core.util.Pair;
@RestrictTo({RestrictTo.Scope.LIBRARY})
/* loaded from: classes.dex */
public class MutablePair<T> {
    @Nullable

    /* renamed from: a  reason: collision with root package name */
    Object f4428a;
    @Nullable

    /* renamed from: b  reason: collision with root package name */
    Object f4429b;

    private static boolean objectsEqual(Object obj, Object obj2) {
        return obj == obj2 || (obj != null && obj.equals(obj2));
    }

    public boolean equals(Object obj) {
        if (obj instanceof Pair) {
            Pair pair = (Pair) obj;
            return objectsEqual(pair.first, this.f4428a) && objectsEqual(pair.second, this.f4429b);
        }
        return false;
    }

    public int hashCode() {
        Object obj = this.f4428a;
        int hashCode = obj == null ? 0 : obj.hashCode();
        Object obj2 = this.f4429b;
        return hashCode ^ (obj2 != null ? obj2.hashCode() : 0);
    }

    public void set(T t2, T t3) {
        this.f4428a = t2;
        this.f4429b = t3;
    }

    public String toString() {
        return "Pair{" + String.valueOf(this.f4428a) + " " + String.valueOf(this.f4429b) + "}";
    }
}
