package com.appmattus.certificatetransparency.internal.utils;

import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt__StringsJVMKt;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes.dex */
public final class LimitedSizeInputStreamKt {
    public static final boolean isTooBigException(@NotNull Exception exc) {
        boolean startsWith$default;
        Intrinsics.checkNotNullParameter(exc, "<this>");
        String message = exc.getMessage();
        if (message != null) {
            startsWith$default = StringsKt__StringsJVMKt.startsWith$default(message, "InputStream exceeded maximum size", false, 2, null);
            if (startsWith$default) {
                return true;
            }
        }
        return false;
    }
}
