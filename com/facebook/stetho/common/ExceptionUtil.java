package com.facebook.stetho.common;
/* loaded from: classes.dex */
public class ExceptionUtil {
    public static RuntimeException propagate(Throwable th) {
        propagateIfInstanceOf(th, Error.class);
        propagateIfInstanceOf(th, RuntimeException.class);
        throw new RuntimeException(th);
    }

    public static <T extends Throwable> void propagateIfInstanceOf(Throwable th, Class<T> cls) {
        if (cls.isInstance(th)) {
            throw th;
        }
    }

    public static <T extends Throwable> void sneakyThrow(Throwable th) {
        throw th;
    }
}
