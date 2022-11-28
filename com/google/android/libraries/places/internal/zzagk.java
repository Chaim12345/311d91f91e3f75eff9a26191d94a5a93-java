package com.google.android.libraries.places.internal;

import java.util.Objects;
/* loaded from: classes2.dex */
public final class zzagk {
    public static Object zza(Object obj) {
        Objects.requireNonNull(obj, "Cannot return null from a non-@Nullable @Provides method");
        return obj;
    }

    public static void zzb(Object obj, Class cls) {
        if (obj == null) {
            throw new IllegalStateException(String.valueOf(cls.getCanonicalName()).concat(" must be set"));
        }
    }
}
