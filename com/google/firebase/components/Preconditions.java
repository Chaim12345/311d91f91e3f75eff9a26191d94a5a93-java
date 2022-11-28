package com.google.firebase.components;

import java.util.Objects;
/* loaded from: classes2.dex */
public final class Preconditions {
    public static void checkArgument(boolean z, String str) {
        if (!z) {
            throw new IllegalArgumentException(str);
        }
    }

    public static <T> T checkNotNull(T t2) {
        Objects.requireNonNull(t2);
        return t2;
    }

    public static <T> T checkNotNull(T t2, String str) {
        Objects.requireNonNull(t2, str);
        return t2;
    }

    public static void checkState(boolean z, String str) {
        if (!z) {
            throw new IllegalStateException(str);
        }
    }
}
