package com.google.api.client.util;
/* loaded from: classes2.dex */
public final class Preconditions {
    private Preconditions() {
    }

    public static void checkArgument(boolean z) {
        com.google.common.base.Preconditions.checkArgument(z);
    }

    public static void checkArgument(boolean z, Object obj) {
        com.google.common.base.Preconditions.checkArgument(z, obj);
    }

    public static void checkArgument(boolean z, String str, Object... objArr) {
        com.google.common.base.Preconditions.checkArgument(z, str, objArr);
    }

    public static <T> T checkNotNull(T t2) {
        return (T) com.google.common.base.Preconditions.checkNotNull(t2);
    }

    public static <T> T checkNotNull(T t2, Object obj) {
        return (T) com.google.common.base.Preconditions.checkNotNull(t2, obj);
    }

    public static <T> T checkNotNull(T t2, String str, Object... objArr) {
        return (T) com.google.common.base.Preconditions.checkNotNull((Object) t2, str, objArr);
    }

    public static void checkState(boolean z) {
        com.google.common.base.Preconditions.checkState(z);
    }

    public static void checkState(boolean z, Object obj) {
        com.google.common.base.Preconditions.checkState(z, obj);
    }

    public static void checkState(boolean z, String str, Object... objArr) {
        com.google.common.base.Preconditions.checkState(z, str, objArr);
    }
}
